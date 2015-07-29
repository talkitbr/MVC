package br.talkitbr.example.viewmodel;

import java.util.ArrayList;
import java.util.List;

import br.talkitbr.example.model.bean.Bean;
import br.talkitbr.example.viewmodel.bean.ViewBean;

/**
 * Classe que converte os dados recebidos do Model, para preparar para a view utilizar.
 */
public class ViewModel {
    /**
     * Método que recebe os dados do Model e converte para a view utilizar.
     * 
     * @param beanList
     *            Dados do Model.
     * @return Dados preparados para a view.
     */
    public static List<ViewBean> convert(List<Bean> beanList) {
        List<ViewBean> viewBeanList = new ArrayList<>();

        for (final Bean bean : beanList) {
            viewBeanList.add(new ViewBean(bean.getText()));
        }

        return viewBeanList;
    }
}
