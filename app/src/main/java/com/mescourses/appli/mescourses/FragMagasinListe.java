package com.mescourses.appli.mescourses;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mescourses.appli.mescourses.DAO.MagasinDAO;
import com.mescourses.appli.mescourses.modeles.Magasin;

/**
 * Created by student on 14-07-17.
 */

public class FragMagasinListe extends Fragment{

    public FragMagasinListe() {
        // Required empty public constructor
    }

    //region Singleton
    private static FragMagasinListe instance;

    public static FragMagasinListe getInstance() {
        if (instance == null) {
            instance = new FragMagasinListe();
        }
        return instance;
    }
    //endregion

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.magasin_list,container,false);
        v = initView(v) ;

        return v ;
    }

    private View initView(View v)
    {
        MagasinListeCustomAdapter adapter;

        ListView lv =  v.findViewById(R.id.lv_produit) ;
        adapter=new MagasinListeCustomAdapter(getContext(),getData());
        lv.setAdapter(adapter);
        return v ;
    }

    private Magasin[] getData() {

        MagasinDAO aDao = new MagasinDAO(getContext()) ;
        aDao = aDao.openReadable();
        Magasin[] mesMagasins = aDao.getAll() ;
        return mesMagasins ;

    }

}
