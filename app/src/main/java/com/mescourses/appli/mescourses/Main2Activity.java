package com.mescourses.appli.mescourses;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.mescourses.appli.mescourses.R;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageButton imgb_mag = (ImageButton) findViewById(R.id.imgb_mag) ;
        ImageButton imgb_prod = (ImageButton) findViewById(R.id.imgb_prod) ;
        ImageButton imgb_prodmag = (ImageButton) findViewById(R.id.img_prodmag) ;
        ImageButton imgb_courses = (ImageButton) findViewById(R.id.imgb_courses) ;

        imgb_mag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowShopList() ;
            }
        });

        imgb_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowProductList() ;
            }
        });

        imgb_prodmag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowProductShopList() ;
            }
        });

        imgb_courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowShopping() ;
            }
        });
    }

    private void ShowProductList()
    {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        FragProduitListe fragment = FragProduitListe.getInstance();
        // fragment.setCallback(this);

        transaction.add(R.id.frag_activity, fragment);
        transaction.commit();
    } ;

    private void ShowShopList() {} ;

    private void ShowProductShopList(){} ;

    private void ShowShopping(){} ;


}
