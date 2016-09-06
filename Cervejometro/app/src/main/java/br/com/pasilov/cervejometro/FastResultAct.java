package br.com.pasilov.cervejometro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.content.*;
import android.app.Activity;
import org.w3c.dom.Text;
import java.math.*;
import java.text.DecimalFormat;
import java.util.*;


public class FastResultAct extends AppCompatActivity implements View.OnClickListener{

    private TextView Titulo;
    private TextView Relatorio;

    private ImageView Foto;

    private ImageButton Voltar;


    private Cerveja barata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fast_result);

        barata = (Cerveja) getIntent().getSerializableExtra("BARATA");

        Titulo = (TextView) findViewById(R.id.txt_FastTitulo);
        Relatorio = (TextView) findViewById(R.id.txt_FastRel);
        Foto = (ImageView) findViewById(R.id.img_FastPic);
        Foto.setImageResource(barata.getFoto());
        Voltar = (ImageButton) findViewById(R.id.btn_back);

        int opçao = getIntent().getIntExtra("QTDE",0);

        if(opçao==1){
            Relatorio.setText(barata.getNome() + " está saindo por R$" +formatar(barata.getPrecolitro(),2)+ " o litro");
        }else if(opçao==2){
            Relatorio.setText(barata.getNome() + " por R$" + formatar(barata.getPreço(),2).replace(".",",")
                    + " é o que mais compensa comprar. \n\n" + "Custando R$" + formatar(barata.getPrecolitro(),2) + " por litro");
        }else{
            finish();
        }

        Voltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Voltar pra activity anterior, não há necessidade de criar outra Activity FastCalc e fechar a antiga, é só voltar.
        finish();
    }


//Arredondar os doubles e formatar para imprimir no Formato R$XX,XX
    public static String formatar(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);

        DecimalFormat df = new DecimalFormat("#.00");

        return df.format(bd.doubleValue());
    }
}
