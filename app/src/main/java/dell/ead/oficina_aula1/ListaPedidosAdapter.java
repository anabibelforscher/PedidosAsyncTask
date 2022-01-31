package dell.ead.oficina_aula1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaPedidosAdapter extends ArrayAdapter<Object[]> {

    private final Context context;
    private final ArrayList<Object[]> values;

    public ListaPedidosAdapter(Context context, ArrayList<Object[]> values) {
        super(context, R.layout.lista_pedidos, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.lista_pedidos, parent, false);
        TextView textView =  rowView.findViewById(R.id.order);
        textView.setText(values.get(position)[0].toString());

        return rowView;
    }
}
