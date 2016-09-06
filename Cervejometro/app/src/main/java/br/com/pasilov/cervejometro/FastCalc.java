package br.com.pasilov.cervejometro;

import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import android.content.*;
import android.app.Activity;
import org.w3c.dom.Text;
import java.util.*;


public class FastCalc extends AppCompatActivity implements View.OnClickListener {

    private Button Calcular;

    private TextView txt_orcamento;

    private EditText edt_orcamento;
    private ListView lista;
    private ImageView cifrao;

    //declarar arraylist Cervejas que vai receber as cervejas do main.
    ArrayList<Cerveja> Cervejas = new ArrayList<Cerveja>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Pegar o arraylist da main activity
        Cervejas = (ArrayList<Cerveja>) getIntent().getSerializableExtra("CERV");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fast);

        cifrao = (ImageView) findViewById(R.id.img_cifrao);
        txt_orcamento = (TextView) findViewById(R.id.txt_orcamento);


        edt_orcamento = (EditText) findViewById(R.id.edt_orc);

        ListAdapter adapta = new CustomAdapter(this,Cervejas);
        lista = (ListView)findViewById(R.id.list_recipes);
        lista.setAdapter(adapta);

        Calcular = (Button) findViewById(R.id.btn_calc);
        Calcular.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        double menorPreçoPorLitro =-1;

        EsconderTeclado();

        //Mudar o foco pro botao com o click
        lista.clearFocus();
        Calcular.requestFocus();

        switch (v.getId()) {

            case R.id.btn_calc:

                //Recuperar as entradas nos edit texts
                View listView;
                EditText et;
                for (int i = 0; i < lista.getChildCount(); i++) {
                    listView = lista.getChildAt(i);
                    et = (EditText) listView.findViewById(R.id.edt_price);
                    Log.d("TAG",et.getText().toString()+" Fora do if");
                    if((!et.getText().toString().isEmpty()) && (Double.parseDouble(et.getText().toString().replaceAll(",","."))!=0)) {
                        Cervejas.get(i).setPreco(Double.parseDouble(et.getText().toString().replaceAll(",", ".")));

                        Log.d("TAG",et.getText().toString()+" Dentro do if");

                    }else if(et.getText().toString().isEmpty()){
                        Cervejas.get(i).desconsiderar();
                        Cervejas.get(i).setPreco(0);
                    }

                }

                //orçamento

                boolean b_orçamento = false;

                if((!edt_orcamento.getText().toString().isEmpty())
                        && (Double.parseDouble(edt_orcamento.getText().toString().replaceAll(",","."))!=0)){
                    b_orçamento = true;
                }

                //Menor preço por litro, igual ao primeiro elemento do array list que contem um valor diferente de 0
                int preenchidas = 0;
                int primeiro = 0;

                menorPreçoPorLitro = Cervejas.get(0).getPrecolitro();
                Cerveja maisbarata = Cervejas.get(0);

                for(Cerveja c: Cervejas){
                    if(c.getPrecolitro()!=0 && c.incluido()) {
                        preenchidas++;
                        if(preenchidas==1) {
                            menorPreçoPorLitro = c.getPrecolitro();
                            maisbarata = c;
                        }
                    }
                }

                //Pra cada cerveja no arraylist cervejas, compara de o precolitro de cada é menor que o atual
                //*******Encontrar menor preço por litro etc.******

                for (Cerveja c : Cervejas) {
                    if(c.getPrecolitro()< menorPreçoPorLitro && c.getPrecolitro()!=0){
                        menorPreçoPorLitro = c.getPrecolitro();
                        maisbarata = c;
                    }else if((c.getPrecolitro() == menorPreçoPorLitro)&&c.getPrecolitro()!=0){
                        //implementar depois casos de preços por litro igual.
                    }
                }

                //Tratamento pra caso não sejam colocadas nenhuma, ou apenas uma entrada, primeiro avalia se tem ou não orçamento
                //depois olha quantas entradas


                //3 opções de resultado caso o usuario não entre com orçamento
                if(!b_orçamento){
                    if(preenchidas>=2) {
                        //Aqui exibir relatorio, comparando, etc
                        Intent it1 = new Intent(this, FastResultAct.class);
                        it1.putExtra("BARATA",maisbarata);
                        it1.putExtra("CERV",Cervejas);
                        it1.putExtra("QTDE",(int)2);
                        startActivity(it1);
                    }else if(preenchidas==1){
                        //Aqui exibir apenas quanto por litro está a cerveja e pronto
                        Intent it2 = new Intent(this, FastResultAct.class);
                        it2.putExtra("BARATA",maisbarata);
                        it2.putExtra("CERV",Cervejas);
                        it2.putExtra("QTDE",(int)1);
                        startActivity(it2);
                    }else if(preenchidas==0){
                        //Aqui pedir pro usuario entrar com pelo menos uma entrada

                        EsconderTeclado();

                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("Favor inserir ao menos uma entrada");
                        dlgAlert.setTitle("Erro");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();

                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        //nada acontece após o ok
                                    }
                            });

                        EsconderTeclado();
                    }
                }else if(b_orçamento){


                }
                break;

        }


    }


    public void EsconderTeclado() {

        lista.clearFocus();
        Calcular.requestFocus();

        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

    }
}

