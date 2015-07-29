package br.talkitbr.example.model.business;

import java.util.ArrayList;
import java.util.List;

import br.talkitbr.example.model.bean.Bean;

/**
 * Classe que contém as regras de negócio do app.
 */
public class BO {
    /**
     * Método que retorna os dados do app, aplicando as regras de negócio.
     * 
     * @return Lista de dados do app.
     */
    public List<Bean> getData() {
        // aqui serão implementadas as regras de negócio do app: requisição, busca em banco de
        // dados, processamento de dados, etc.
        List<Bean> list = new ArrayList<>();

        list.add(new Bean("Primeiro item"));
        list.add(new Bean("Segundo item"));
        list.add(new Bean("Terceiro item"));

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
        }
        return list;
    }
}
