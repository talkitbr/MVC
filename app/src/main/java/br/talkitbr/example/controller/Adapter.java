package br.talkitbr.example.controller;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import br.talkitbr.example.model.bean.Bean;
import br.talkitbr.example.view.RecyclerViewItem;

/**
 * Adapter do método tradicional (com findViewById e sem dataBinding).
 */
public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    /** Lista de itens do adapter. */
    private final List<Bean> mItems;
    /** Listener para tratar o clique em um item da lista. */
    private ItemClickListener mListener;

    /**
     * Construtor.
     */
    public Adapter() {
        mItems = new ArrayList<>();
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // instanciamos a view do item da lista
        View v = new RecyclerViewItem(parent.getContext());
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // atualizamos o item com os dados da posição
        holder.updateItem(mItems.get(position), mListener);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * @param items
     *            Itens da lista.
     */
    public void setItems(List<Bean> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    /**
     * @param listener
     *            Listener para tratar o clique de um item da lista
     */
    public void setListener(ItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * Interface para receber o clique em um item da lista.
     */
    public interface ItemClickListener {
        /**
         * Método chamado quando um item da lista for clicado.
         * 
         * @param item
         *            Item clicado.
         */
        void onItemClick(Bean item);
    }

    /**
     * Classe para o ViewHolder do RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** View que vai exibir os dados. */
        public RecyclerViewItem mItemView;

        /**
         * Construtor.
         * 
         * @param v
         *            View onde o conteúdo será exibido.
         */
        public ViewHolder(View v) {
            super(v);
            mItemView = (RecyclerViewItem) v;
        }

        /**
         * Atualiza os dados exibidos.
         * 
         * @param bean
         *            Dados a serem exibidos.
         * @param listener
         *            Listener para tratar o click no item.
         */
        public void updateItem(Bean bean, ItemClickListener listener) {
            mItemView.updateUI(bean);
            mItemView.setListener(listener);
        }
    }
}
