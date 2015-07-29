package br.talkitbr.example.viewmodel;

import java.util.ArrayList;
import java.util.List;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.talkitbr.example.R;
import br.talkitbr.example.databinding.ListItemBinding;
import br.talkitbr.example.viewmodel.bean.ViewBean;

/**
 * Adapter do método dataBinding (sem findViewById e com dataBinding).
 */
public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder> {
    /** Lista de itens do adapter. */
    private final List<ViewBean> mItems;
    /** Listener para tratar o clique em um item da lista. */
    private ItemClickListener mListener;

    /**
     * Construtor
     */
    public Adapter2() {
        mItems = new ArrayList<>();
    }

    @Override
    public Adapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item,
                parent, false);

        return new ViewHolder(binding.getRoot(), binding, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /**
     * @param items
     *            Itens da lista.
     */
    public void setItems(List<ViewBean> items) {
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
        void onItemClick(ViewBean item);
    }

    /**
     * Classe para o ViewHolder do RecyclerView.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** Objeto para executar o binding para a UI. */
        private ListItemBinding mBinding;
        /** Listener para tratar o clique no item. */
        private ItemClickListener mListener;

        /**
         * Construtor.
         * 
         * @param v
         *            View onde o conteúdo será exibido.
         * @param binding
         *            Objeto que fará o bind dos dados na view.
         * @param listener
         *            Listener para tratar o clique no item.
         */
        public ViewHolder(View v, ListItemBinding binding, final ItemClickListener listener) {
            super(v);
            mBinding = binding;
            mListener = listener;
        }

        /**
         * Atualiza a view do item.
         */
        public void bind(ViewBean s) {
            mBinding.setBean(s);
            mBinding.cardView.setOnClickListener(new MyClickListener());
        }

        /**
         * Listener para receber o clique no item.
         */
        private class MyClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                mListener.onItemClick(mBinding.getBean());
            }
        }
    }
}
