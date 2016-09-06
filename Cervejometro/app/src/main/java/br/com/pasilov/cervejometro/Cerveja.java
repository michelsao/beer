package br.com.pasilov.cervejometro;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;

public class Cerveja implements Serializable{

    private String nome;
    private String marca;

    private boolean considerar = false;

    private double volume;
    private double preço = 0;

    private static final long serialVersionUID = 7;

    private int foto;


    //construtor pra quando tiverem marcas diferentes
    public Cerveja(String m,String nome, double vol, int pic){

        volume = vol;
        this.nome = nome;
        this.marca = m;
        this.foto = pic;

    }

    //construtor sem marca, pra fast calc
    public Cerveja(String nome, double vol,int pic){

        this.nome = nome;
        this.volume = vol;
        this.foto = pic;


    }

    public void setPreco(double p){

        preço = p;

        if(preço!=0) {
            considerar = true;
        }

    }

    public double getPreço(){
        return preço;
    }

    public double getPrecolitro() {
        return (1000 * preço) / volume;
    }

    public String getNome() {
        return nome;
    }

    public String getMarca() {
        return marca;
    }

    public String getVol(){
        return Integer.toString((int) volume);
    }

    public boolean incluido(){
        return considerar;
    }

    public int getFoto(){
        return foto;
    }

    //Pra quando o edit text estiver em branco após uma conta realizada previamente
    public void desconsiderar(){
        considerar = false;
    }
}



