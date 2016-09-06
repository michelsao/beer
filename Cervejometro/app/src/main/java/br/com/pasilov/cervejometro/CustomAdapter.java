package br.com.pasilov.cervejometro;

import android.content.Context;
import android.media.Image;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<Cerveja> {

    public CustomAdapter(Context context, ArrayList<Cerveja> brejas) {
        super(context,R.layout.customlist_img_txt_txt_edt, brejas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lista_inflater =  LayoutInflater.from(getContext());
        View customView = convertView;
        if(customView == null){
            customView = lista_inflater.inflate(R.layout.customlist_img_txt_txt_edt, parent, false);
        }

        Cerveja singleItem       = getItem(position);
        TextView Nome            = (TextView)customView.findViewById(R.id.txt_nome);
        TextView Volume          = (TextView)customView.findViewById(R.id.txt_vol);
        EditText Preço           = (EditText)customView.findViewById(R.id.edt_price);
        ImageView Foto           = (ImageView)customView.findViewById(R.id.img_foto_list);
        ImageView cifrao         = (ImageView)customView.findViewById(R.id.img_cifrao2);


        Nome.setText(singleItem.getNome());
        Volume.setText(singleItem.getVol() +"ml");
        Foto.setImageResource(singleItem.getFoto());

        //Setar o preço em cada item do arraylist, passar de editable pra string, remover as virgulas e passar pra double.
        if(!Preço.getText().toString().isEmpty()) {
            singleItem.setPreco(Double.parseDouble(Preço.getText().toString().replace(",", ".")));
        }


        return customView;
    }


}
