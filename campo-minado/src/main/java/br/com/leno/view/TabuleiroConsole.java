package br.com.leno.view;

import br.com.leno.exception.ExplosaoException;
import br.com.leno.exception.SairException;
import br.com.leno.model.Tabuleiro;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;
    private Scanner entrada = new Scanner(System.in);

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        executarJogo();
    }

    private void executarJogo() {
        try {

            boolean continuar = true;
            while (continuar) {
                loopDoJogo();
                System.out.println("Nova partida ? (S/n)");

                String resposta = entrada.nextLine();

                if ("n".equalsIgnoreCase(resposta)) {
                    continuar = false;

                } else {
                    tabuleiro.reiniciar();
                }

            }


        } catch (SairException e) {
            System.out.println("SAINDO");
        } finally {
            entrada.close();
        }
    }

    private void loopDoJogo() {
        try {

            while (!tabuleiro.objetivoAlcancado()) {
                System.out.println(tabuleiro.toString());

                String digitado = capturarValorDigitado("Digite X e Y : ");

                Iterator<Integer> xy = Arrays.stream(digitado.split(","))
                        .map(e -> Integer.parseInt(e.trim()))
                        .iterator();
                digitado = capturarValorDigitado("1 - para abrir ou 2 - para (des)marcar : ");


                if("1".equals(digitado)){
                  tabuleiro.abrirCampo(xy.next(),xy.next());

                }else if("2".equals(digitado)){
                    tabuleiro.alternarMarcacao(xy.next(),xy.next());
                }

            }
            System.out.println(tabuleiro);
            System.out.println("Voce ganhou!!");
        } catch (ExplosaoException e) {

            System.out.println(tabuleiro);
            System.out.println("Voce perdeu!!");

        }

    }


    private String capturarValorDigitado(String texto) {
        System.out.print(texto);
        String digitado = entrada.nextLine();

        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }

        return digitado;
    }


}
