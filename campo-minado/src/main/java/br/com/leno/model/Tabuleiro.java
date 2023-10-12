package br.com.leno.model;

import br.com.leno.exception.ExplosaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Tabuleiro {

    private int linhas;
    private int colunas;
    private int minas;
    private final List<Campo> campos = new ArrayList<>();

    public Tabuleiro(int linhas, int colunas, int minas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.minas = minas;

        gerarCampos();
        associarVizinhos();
        sortearMinas();

    }

    private void gerarCampos() {
        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                campos.add(new Campo(linha, coluna));
            }
        }
    }

    private void associarVizinhos() {
        for (Campo c1 : campos) {
            for (Campo c2 : campos) {
                c1.adcionarVizinho(c2);
            }
        }
    }

    private void sortearMinas() {
        long minasArmadas = 0;
        Predicate<Campo> minado = c -> c.isMinado();

        do {
            int aleatorio = (int) (Math.random() * campos.size());
            campos.get(aleatorio).minar();
            minasArmadas = campos.stream().filter(minado).count();

        } while (minasArmadas < minas);


    }

    public boolean objetivoAlcancado() {
        return campos.stream().allMatch(c -> c.objetivoAlcancado());
    }

    public void reiniciar() {
        campos.stream().forEach(c -> c.reiniciar());
        sortearMinas();


    }

    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("  ");
        for (int i = 0; i <colunas ; i++) {
            builder.append(" ");
            builder.append(i);
            builder.append(" ");

        }
        builder.append("\n");

        int z = 0;
        for (int i = 0; i < linhas; i++) {
            builder.append(i);
            builder.append(" ");
            for (int j = 0; j < colunas; j++) {

                builder.append(" ");
                builder.append(campos.get(z));
                builder.append(" ");
                z++;
            }

            builder.append("\n");
        }

        return builder.toString();
    }


    public void abrirCampo(int linha, int coluna) {
        try {
            campos.parallelStream()
                    .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                    .findFirst()
                    .ifPresent(c -> c.abrir());

        } catch (ExplosaoException e) {

            campos.forEach(c -> c.setAberto(true));
            throw e;

        }

    }

    public void alternarMarcacao(int linha, int coluna) {
        campos.parallelStream()
                .filter(c -> c.getLinha() == linha && c.getColuna() == coluna)
                .findFirst()
                .ifPresent(c -> c.alternarMarcacao());

    }


}
