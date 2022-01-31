package dell.ead.oficina_aula1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button mButtonAction;
    private ListView mListView;
    private String[] mOrders = {
            "Teste", "teste"
    };
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonAction = findViewById(R.id.buttonAction);
        mListView = findViewById(R.id.listViewOrder);
        setButton("Atualizar Pedidos");

       mAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                R.layout.lista_pedidos,
                R.id.order,
                mOrders
        );
    }

    public void setButton (String message){
      mButtonAction.setText(message);
      mButtonAction.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              mListView.setAdapter(mAdapter);
          }
      });
    }

}