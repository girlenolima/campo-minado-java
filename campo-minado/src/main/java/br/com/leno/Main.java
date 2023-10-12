package br.com.leno;


import br.com.leno.model.Tabuleiro;
import br.com.leno.view.TabuleiroConsole;

public class Main {
    public static void main(String[] args) {


        Tabuleiro tabuleiro = new Tabuleiro(6,6,6);
        new TabuleiroConsole(tabuleiro);


    }
}