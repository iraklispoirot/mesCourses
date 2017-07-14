package com.mescourses.appli.mescourses;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class FragContainerNiv1Activity extends AppCompatActivity {
    private Toolbar mActionBarToolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_container_niv1);
        mActionBarToolbar = (Toolbar) findViewById(R.id.tb_fragment_container_niv1);
        setSupportActionBar(mActionBarToolbar);

        String myExtra = this.getIntent().getStringExtra(global.FRAGMENTNIV1) ;
        if (myExtra != null) {
            switch (myExtra) {
                case global.PRODUITLISTEID:
                    ShowProduitListeFragment();
                    break;
                case global.PRODMAGLISTEID:
                    break;
                case global.MAGASINLISTEID:
                    ShowMagasinListeFragment();
                    break;
                case global.COURSEID:
                    break;
                default:
                    break;
            }
        }
    }

    void ShowProduitListeFragment()
    {
        getSupportActionBar().setTitle(R.string.tb_liste_produit);

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        FragProduitListe fragment = FragProduitListe.getInstance();
        // fragment.setCallback(this);

        transaction.add(R.id.fragment_container_niv1, fragment);
        transaction.commit();
    }
    void ShowMagasinListeFragment()
    {
        getSupportActionBar().setTitle(R.string.tb_liste_magasin);

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        FragMagasinListe fragment = FragMagasinListe.getInstance();
        // fragment.setCallback(this);

        transaction.add(R.id.fragment_container_niv1, fragment);
        transaction.commit();
    }
}
