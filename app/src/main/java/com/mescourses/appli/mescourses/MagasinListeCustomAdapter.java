package com.mescourses.appli.mescourses;

/**
 * Created by student on 14-07-17.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mescourses.appli.mescourses.modeles.Magasin;

public class MagasinListeCustomAdapter extends BaseAdapter{

    Context context ;
    Magasin[] mesMagasins ;

    public MagasinListeCustomAdapter(Context context, Magasin[] mesMagasins) {
        this.context = context;
        this.mesMagasins = mesMagasins;
    }

    @Override
    public int getCount() {

        if (mesMagasins != null)
            return mesMagasins.length;
        else return 0 ;
    }

    @Override
    public Object getItem(int i) {
        return mesMagasins[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(context).inflate(R.layout.magasin_list_content,viewGroup,false);
        }
        final Magasin unMagasin = (Magasin) this.getItem(i);

        TextView nom         = (TextView) view.findViewById(R.id.tv_nomMagasin);
        TextView adresse     = (TextView) view.findViewById(R.id.tv_adresseMagasin);
        TextView description = (TextView) view.findViewById(R.id.tv_descriptionMagasin);
        TextView pos         = (TextView) view.findViewById(R.id.tv_posMagasin);
        ImageView img        = (ImageView)view.findViewById(R.id.img_magasin);

        nom.setText(unMagasin.getNom());
        adresse.setText(unMagasin.getAdresse());
        description.setText(unMagasin.getDescription());
        pos.setText("Lat:" + unMagasin.getPos_latitude() + "  Long:" + unMagasin.getPos_longitude());

        //// TODO: 14/07/2017 add imageview
        //   img.setImageResource(s.getImage());
        img.setImageResource(R.mipmap._shops);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, unMagasin.getNom(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
