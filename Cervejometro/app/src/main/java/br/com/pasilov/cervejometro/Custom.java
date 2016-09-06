package br.com.pasilov.cervejometro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;

public class Custom extends AppCompatActivity {

    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_custom);

        foto = (ImageView)findViewById(R.id.kakaka);

    }
}
