package com.bradesco.banco.Ibm;

import java.util.LinkedList;
import java.util.List;

import static com.bradesco.banco.Ibm.TestIBM.gerarLista;
import static com.bradesco.banco.Ibm.TestIBM.separarLista;

public class TestIBMTest {
static List<Integer> primeiraLista;
List<Integer> segundaLista;
    public static void main(String[] args) {
        TestIBM testIBM = new TestIBM();
        primeiraLista = (gerarLista(10));
        System.out.println("Primeira " + primeiraLista);



    }
}
