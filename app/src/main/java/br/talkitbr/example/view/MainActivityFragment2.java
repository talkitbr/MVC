package br.talkitbr.example.view;

import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.talkitbr.example.R;
import br.talkitbr.example.model.bean.Bean;
import br.talkitbr.example.model.business.BO;
import br.talkitbr.example.viewmodel.Adapter2;
import br.talkitbr.example.viewmodel.ViewModel;
import br.talkitbr.example.viewmodel.bean.ViewBean;

/**
 * Fragment que implementa com dataBinding.
 */
public class MainActivityFragment2 extends Fragment {
    /** Adapter para atualizar a lista. */
    private final Adapter2 mAdapter;
    /** UI do fragment. */
    private UI mUI;
    /** Async task para executar as regras de negócio. */
    private MyAsyncTask mAt;

    /**
     * Construtor.
     */
    public MainActivityFragment2() {
        mAdapter = new Adapter2();
        mAdapter.setListener(new MyClickListener());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View parentView = inflater.inflate(R.layout.fragment_main2, container, false);
        mUI = new UI(parentView);
        mUI.toLoadingState();
        return parentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onPause() {
        mAt.cancel(true);
        super.onPause();

    }

    /**
     * Método que inicia a busca dos dados.
     */
    private void loadData() {
        mAt = new MyAsyncTask();
        mAt.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Async task para executar a busca dos dados do app.
     */
    private class MyAsyncTask extends AsyncTask<Void, Void, List<Bean>> {
        @Override
        protected List<Bean> doInBackground(Void... voids) {
            BO bo = new BO();
            return bo.getData();
        }

        @Override
        protected void onPostExecute(List<Bean> beans) {
            mAdapter.setItems(ViewModel.convert(beans));
            mUI.toNormalState();
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Classe do listener para tratar o clique no item da lista.
     */
    private class MyClickListener implements Adapter2.ItemClickListener {
        @Override
        public void onItemClick(ViewBean item) {
            Toast.makeText(getActivity(), "Click no item (Activity DataBinding) " + item.text,
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * UI do fragment.
     */
    protected class UI {
        /** Referência da RecyclerView. */
        private final RecyclerView mRecyclerView;
        /** Referência da progress bar. */
        private final ProgressBar mProgress;
        /** Referência do botão. */
        private final Button mButton;

        /**
         * Construtor.
         *
         * @param parentView
         *            View do fragment.
         */
        UI(View parentView) {
            mRecyclerView = (RecyclerView) parentView.findViewById(R.id.my_recycler_view);
            mProgress = (ProgressBar) parentView.findViewById(R.id.my_progress);
            mButton = (Button) parentView.findViewById(R.id.my_button);
            setupUI();
        }

        /**
         * Configura a UI.
         */
        void setupUI() {
            mRecyclerView.setHasFixedSize(true);

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            mRecyclerView.setLayoutManager(layoutManager);

            mRecyclerView.setAdapter(mAdapter);

            mButton.setOnClickListener(new MyButtonClickListener());
        }

        /**
         * Atualiza a tela para exibir o loading.
         */
        void toLoadingState() {
            mRecyclerView.setVisibility(View.GONE);
            mProgress.setVisibility(View.VISIBLE);
        }

        /**
         * Atualiza a tela para exibir os dados.
         */
        void toNormalState() {
            mRecyclerView.setVisibility(View.VISIBLE);
            mProgress.setVisibility(View.GONE);
        }

        /**
         * Listener para tratar o clique no botão, para abrir a próxima activity.
         */
        private class MyButtonClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        }
    }
}
