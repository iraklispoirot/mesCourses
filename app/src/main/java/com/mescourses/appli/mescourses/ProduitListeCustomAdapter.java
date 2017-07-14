package com.mescourses.appli.mescourses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mescourses.appli.mescourses.modeles.Produit;

import java.util.ArrayList;

/**
 * Created by omar on 14/07/2017.
 */

public class ProduitListeCustomAdapter extends BaseAdapter {
    Context context ;
    Produit[] mesProduits ;

    public ProduitListeCustomAdapter(Context context, Produit[] mesProduits) {
        this.context = context;
        this.mesProduits = mesProduits;
    }
    @Override
    public int getCount() {

        if (mesProduits != null)
          return mesProduits.length;
        else return 0 ;
    }

    @Override
    public Object getItem(int i) {
        return mesProduits[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.produit_list_content,viewGroup,false);
        }
        final Produit unProduit = (Produit) this.getItem(i);
        TextView nom= (TextView) view.findViewById(R.id.tv_nomProduit);
        TextView marque= (TextView) view.findViewById(R.id.tv_marqueProduit);
        TextView prix= (TextView) view.findViewById(R.id.tv_prixProduit);
        TextView manque= (TextView) view.findViewById(R.id.tv_manqueProduit);
        //// TODO: 14/07/2017 load image and add imageview


        nom.setText(unProduit.getNom());
        marque.setText(unProduit.getMarque());
        prix.setText(Float.toString(unProduit.getPrix()) );
        manque.setText(unProduit.getManque());
        //// TODO: 14/07/2017 add imageview
        //   img.setImageResource(s.getImage());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, unProduit.getNom(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
