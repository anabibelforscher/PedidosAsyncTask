package dell.ead.oficina_aula1;

import android.app.AlertDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mButtonAction;
    private Button mButtonClean;
    private ListView mListView;
    private TextView progressMessage;
    private LinearLayout areaProgressBar;
    private ProgressBar mProgressBar;
    ArrayList<String> listaPedidos = new ArrayList<>();
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonAction = findViewById(R.id.buttonAction);
        mButtonClean = findViewById(R.id.buttonClean);
        areaProgressBar = findViewById(R.id.area_progressbar);
        mProgressBar = findViewById(R.id.progressBar);
        progressMessage = findViewById(R.id.progressMessage);

        setButtonUpdateList();

    }

    public void setListViewTask(){
        Random random = new Random();
        int number = random.nextInt(12);

        PedidosAsyncTask asyncTask = new PedidosAsyncTask();
        asyncTask.execute(number);
    }

    public void setButtonUpdateList(){
        mButtonAction.setText(R.string.atualizar_pedidos);
        mButtonAction.setVisibility(View.VISIBLE);
        mButtonAction.setOnClickListener(view -> setListViewTask());
    }

    public void disableUpdateButton(){
        mButtonAction.setVisibility(View.GONE);
    }


    public void setButtonDeleteList(){
        mButtonClean.setText(R.string.limpar_pedidos);
        mButtonClean.setVisibility(View.VISIBLE);
        mButtonClean.setOnClickListener(view -> setConfimationAlert());
    }

    public void disableCleanButton(){
        mButtonClean.setVisibility(View.GONE);
    }

    public void cleanList (){
        listaPedidos.removeAll(listaPedidos);
        mListView.setVisibility(View.GONE);
    }

    public void setConfimationAlert(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.atention)
                .setMessage(R.string.alertMessage)
                .setCancelable(false)
                .setPositiveButton(R.string.confirm, (dialog, which) -> {
                    cleanList();
                    disableCleanButton();
                    setButtonUpdateList();
                })
                .setNegativeButton(R.string.cancel, (dialog, which) -> {

                })
                .show();
    }


    class PedidosAsyncTask extends AsyncTask<Integer, Integer, ArrayList<String>> {

        private DecimalFormat decimalFormat;

        public PedidosAsyncTask(){
            decimalFormat = new DecimalFormat("#");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            areaProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<String> doInBackground(Integer... integers) {

            int quantidadePedidos = integers[0];
            mProgressBar.setMax(quantidadePedidos);

            /* A criação da lista de pedidos leva um tempo, também aleatório,
            que pode ser imediato (quando a lista criada não possui pedidos) ou até cerca de 20 segundos
            (no pior caso, com 10 pedidos, custando 2 segundos cada para ser criado)*/

            for (int i = 1; i < quantidadePedidos; i++) {
                listaPedidos.add("Pedido "+i);
                try {
                    publishProgress(i);
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (quantidadePedidos<=1)
                listaPedidos.add("Nenhum pedido para atualizar");

            return listaPedidos;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            disableUpdateButton();
            mProgressBar.setProgress(values[0]);
            float progresso = (float) mProgressBar.getProgress()/mProgressBar.getMax();
            progressMessage.setText("Atualizando lista, por favor aguarde... " + decimalFormat.format(progresso*100)+ "%");
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            mProgressBar.getMax();
            areaProgressBar.setVisibility(View.INVISIBLE);
            mListView = findViewById(R.id.listViewOrder);
            mListView.setVisibility(View.VISIBLE);
            mAdapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    R.layout.lista_pedidos,
                    R.id.order,
                    listaPedidos
            );
            mListView.setAdapter(mAdapter);
            disableUpdateButton();
            setButtonDeleteList();
        }
    }
}