package dell.ead.oficina_aula1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mButtonAction;
    private ListView mListView;
    private LinearLayout areaProgressBar;
    private ProgressBar mProgressBar;
    ArrayList<String> listaPedidos = new ArrayList<>();
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonAction = findViewById(R.id.buttonAction);
        areaProgressBar = findViewById(R.id.area_progressbar);
        mProgressBar = findViewById(R.id.barra_progresso);
        setButtonUpdateList();
    }

    public void setListViewTask(){
        Random random = new Random();
        int number = random.nextInt(12);

        PedidosAsyncTask asyncTask = new PedidosAsyncTask();
        asyncTask.execute(number);
    }

    public void setButtonUpdateList(){
        setButtonMessage(R.string.atualizar_pedidos);
        mButtonAction.setOnClickListener(view -> setListViewTask());
    }

    //TODO Fazer botão de limpar lista

    public void setButtonMessage(int message){
        mButtonAction.setText(message);
    }


    class PedidosAsyncTask extends AsyncTask<Integer, Integer, ArrayList<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            areaProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<String> doInBackground(Integer... integers) {

            int quantidadePedidos = integers[0];


            for (int i = 1; i < quantidadePedidos; i++) {
                listaPedidos.add("Pedido "+i);
                publishProgress(i);
            }
            //TODO ajustar para quando a lista for vazia
            if (quantidadePedidos<=1)
                listaPedidos.add("Nenhum pedido para atualizar");

            try {
                Thread.sleep(quantidadePedidos*2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return listaPedidos;
        }

        @Override
        //TODO Configurar barra de progresso ou spinner
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<String> s) {
            super.onPostExecute(s);
            areaProgressBar.setVisibility(View.INVISIBLE);
            mListView = findViewById(R.id.listViewOrder);
            mAdapter = new ArrayAdapter<>(
                    getApplicationContext(),
                    R.layout.lista_pedidos,
                    R.id.order,
                    listaPedidos
            );
            mListView.setAdapter(mAdapter);
        }
    }
}