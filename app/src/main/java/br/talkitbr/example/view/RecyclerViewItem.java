package br.talkitbr.example.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import br.talkitbr.example.R;
import br.talkitbr.example.controller.Adapter;
import br.talkitbr.example.model.bean.Bean;

/**
 * View que exibe os dados na tela.
 */
public class RecyclerViewItem extends CardView {
    /** UI do item. */
    private UI mUI;
    /** Dados do item. */
    private Bean mItem;
    /** Listener para tratar o clique no item. */
    private Adapter.ItemClickListener mListener;

    /**
     * Construtor.
     *
     * @param context
     *            contexto.
     * @param attrs
     *            atributos do item.
     * @param defStyle
     *            estilo do item.
     */
    public RecyclerViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init();
    }

    /**
     * Construtor.
     *
     * @param context
     *            contexto.
     * @param attrs
     *            atributos do item.
     */
    public RecyclerViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    /**
     * Construtor.
     *
     * @param context
     *            contexto.
     */
    public RecyclerViewItem(Context context) {
        super(context);

        init();
    }

    /**
     * Método que inicializa a view do item.
     */
    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View container = inflater.inflate(R.layout.fragment_main_recycler_view_item, this, false);

        mUI = new UI(container);

        addView(container);
    }

    /**
     * Método que atualiza a view do item.
     * 
     * @param item
     *            Dados do item.
     */
    public void updateUI(Bean item) {
        mItem = item;

        mUI.updateUI();
    }

    /**
     * @param listener
     *            Listener para tratar o clique no item.
     */
    public void setListener(Adapter.ItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * UI do item.
     */
    private class UI {
        /** Referência para o item da UI. */
        private final TextView mText;

        /**
         * Construtor.
         * 
         * @param parentView
         *            View do item.
         */
        UI(View parentView) {
            mText = (TextView) parentView.findViewById(R.id.fragment_main_recycler_view_item_text);

            View view = parentView.findViewById(R.id.card_view);
            view.setOnClickListener(new ItemClickListener());
        }

        /**
         * Atualiza a view do item.
         */
        private void updateUI() {
            if (null != mItem) {
                mText.setText(mItem.getText());
            }
        }

        /**
         * Listener para receber o clique no item.
         */
        private class ItemClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(mItem);
                }
            }
        }
    }
}
