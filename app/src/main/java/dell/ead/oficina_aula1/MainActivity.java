package dell.ead.oficina_aula1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button mButtonAction;
    private ListView mListView;
    private TextView mTextViewOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonAction = findViewById(R.id.buttonAction);
        mTextViewOrder= findViewById(R.id.order);
        setButtonUpdateList();
    }

    public void setListView(){
        Random random = new Random();
        int number = random.nextInt(12);
        ArrayList<Object[]> pedidos = new ArrayList<>();
        for (int i = 1; i < number; i++) {
            pedidos.add(new Object[] { "Pedido "+i, 2000 });
        }
        if (number<=1)
            pedidos.add(new Object[] { "Nenhum pedido para atualizar ", 0 });

        mListView = findViewById(R.id.listViewOrder);
        mListView.setAdapter(new ListaPedidosAdapter(this, pedidos));
    }

    public void setButtonUpdateList(){
        setButtonMessage(R.string.atualizar_pedidos);
        mButtonAction.setOnClickListener(view -> setListView());
    }

    public void setButtonMessage(int message){
        mButtonAction.setText(message);
    }
}