package com.mescourses.appli.mescourses;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mescourses.appli.mescourses.DAO.ProduitDAO;
import com.mescourses.appli.mescourses.modeles.Produit;

import java.util.ArrayList;

/**
 * Created by omar on 14/07/2017.
 */

public class FragProduitListe extends Fragment{

    public FragProduitListe() {
        // Required empty public constructor
    }

    //region Singleton
    private static FragProduitListe instance;

    public static FragProduitListe getInstance() {
        if (instance == null) {
            instance = new FragProduitListe();
        }
        return instance;
    }
    //endregion


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.produit_list,container,false);

        v = initView(v) ;

        return v ;
    }

    private View initView(View v)
    {
        CustomAdapter adapter;

        ListView lv =  v.findViewById(R.id.lv_produit) ;
        adapter=new CustomAdapter(getContext(),getData());
        lv.setAdapter(adapter);
        return v ;
    }

    private Produit[] getData() {

        ProduitDAO aDao = new ProduitDAO(getContext()) ;
        aDao = aDao.openReadable();
        Produit[] mesProduits = aDao.getAll() ;
        return mesProduits ;

    }

}
