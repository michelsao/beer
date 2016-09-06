package br.com.pasilov.cervejometro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.app.Activity;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    static ArrayList<Cerveja> Cervejas = new ArrayList<Cerveja>();

    private TextView FastCalc;
    private TextView FullCalc;
    private TextView Personalizados;
    private TextView Instruc;
    private TextView Sobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        FastCalc        = (TextView) findViewById(R.id.sel_FastCalc);
        FastCalc.setOnClickListener(this);

        FullCalc        = (TextView) findViewById(R.id.sel_fullCalc);
        FullCalc.setOnClickListener(this);

        Personalizados  =(TextView) findViewById(R.id.sel_custom);
        Personalizados.setOnClickListener(this);

        Instruc         = (TextView) findViewById(R.id.sel_Instr);
        Instruc.setOnClickListener(this);

        Sobre           = (TextView) findViewById(R.id.sel_Sobre);
        Sobre.setOnClickListener(this);

        Cerveja latao   = new Cerveja("Latão",473,R.drawable.latao);
        Cervejas.add(latao);
        Cerveja casco   = new Cerveja("Casco",600,R.drawable.casco);
        Cervejas.add(casco);
        Cerveja litrao  = new Cerveja("Litrão",1000,R.drawable.litrao);
        Cervejas.add(litrao);
        Cerveja neck = new Cerveja("Long Neck",355,R.drawable.longneck);
        Cervejas.add(neck);
        Cerveja latinha = new Cerveja ("Lata",350,R.drawable.latinha);
        Cervejas.add(latinha);
        Cerveja latafina = new Cerveja ("Mini-Lata",267,R.drawable.minilata);
        Cervejas.add(latafina);
        Cerveja mega = new Cerveja("Mega-latão",550,R.drawable.megalatao);
        Cervejas.add(mega);
        Cerveja barriga = new Cerveja("Barrigudinha",300,R.drawable.barrigudinha);
        Cervejas.add(barriga);

    }

    @Override
    public void onClick(View v)    {

        switch(v.getId()){
            case R.id.sel_FastCalc:
                Intent it1 = new Intent(this, FastCalc.class);
                it1.putExtra("CERV",Cervejas);
                startActivity(it1);
                break;
            case R.id.sel_fullCalc:
                break;
            case R.id.sel_custom:
                Intent it3 = new Intent(this, Custom.class);
                startActivity(it3);
                break;
            case R.id.sel_Instr:
                //Instrucoes
                break;
            case R.id.sel_Sobre:
                Intent it6 = new Intent(this, SobreAct.class);
                startActivity(it6);
                break;
            default:
                break;
        }
    }




}
