package dell.ead.oficina_aula1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button mButtonAction;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonAction = findViewById(R.id.buttonAction);
    }

    public void setButton (String message){
      mButtonAction.setText(message);
    }

    public void setImageViewUpdate (int imageView){
        mImageView = findViewById(R.id.imageOrder);
        mImageView.setImageResource(imageView);
    }
}