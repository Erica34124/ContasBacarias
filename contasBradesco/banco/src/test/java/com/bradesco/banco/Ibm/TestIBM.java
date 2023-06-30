package com.bradesco.banco.Ibm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestIBM {
    private static List<Integer> inteiros;
    private static List<Integer> a;
    private static List<Integer> b;

    public static List<Integer> gerarLista(int qtd) {
        Random aleatorio = new Random();
        inteiros = new ArrayList<>(qtd);
        for (int i = 0; i< qtd; i++){
            inteiros.add(aleatorio.nextInt(qtd));
        }
        return inteiros;
    }

    public static List<Integer[]> separarLista(List<Integer[]> l){
       Integer[] lista = {0, 6, 6, 2, 7, 2, 0, 8, 8, 8};
        int maior = 0;
        for(int i = 0 ; i < lista.length; i++){
            for(int j = 0 ; j < lista.length; j++){
                if (lista[i] > lista[j]){
                    maior = lista[i];
                    lista[i] = lista[j];
                    lista[j] = maior;
                }
            }

        }
        l.add(lista);
        return l;
    }
}