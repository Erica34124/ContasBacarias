package com.bradesco.banco.aulasListasAlura;

import com.bradesco.banco.domain.Conta;
import com.bradesco.banco.domain.Corrente;
import com.bradesco.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class TestandoListas {
    @Autowired
    ContaRepository contaRepository;
    public static void main(String[] args) {

        String aula1 = "Conhecendo Listas";
        String aula2 = "Conhecendo trabalhando listas";
        String aula3 = "Conhecendo  adicionando Listas";
        String aula4 = "Conhecendo deletando Listas";
        String aula5 = "Conhecendo atualizando  Listas";

        ArrayList<String> aulas = new ArrayList<>();
        aulas.add(aula1);
        aulas.add(aula2);
        aulas.add(aula4);
        aulas.add(aula3);
        aulas.add(aula5);
        System.out.println(aulas);

        aulas.remove(2);
        System.out.println(aulas);

        for (String a : aulas) {
            System.out.println(a);
        }
        for (int i = 0; i < aulas.size(); i++) {
            System.out.println("aula : " + aulas.get(i));
        }
        aulas.forEach(aula -> {
            System.out.println("Com forEach : " + aula);
        });
        System.out.println(" -----------------------------------------------------------------------------");

    }
}
