package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.ProdMag;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;

/**
 * Created by student on 10-07-17.
 */

public class ProdMagDAO {

    //region  TABLE DESCRIPTION and SQL STATEMENTS

    public static final String  TABLE_PRODMAG = "prodmag";

    public static final String  COLUMN_ID                =  "_id" ;
    public static final String  COLUMN_NOM               =  "nom";
    public static final String  COLUMN_PRODUIT_REFID     =  "refid_produit";
    public static final String  COLUMN_MAGASIN_REFID     =  "refid_magasin";

    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PRODMAG + " ( "
                    + COLUMN_ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NOM                  + " TEXT NOT NULL, "
                    + COLUMN_PRODUIT_REFID        + " INTEGER NOT NULL, "
                    + COLUMN_MAGASIN_REFID        + " INTEGER NOT NULL  "
                    + " );";

    public static final String DELETE_REQUEST =
            "DROP TABLE IF EXISTS " + TABLE_PRODMAG + ";";
//endregion

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public ProdMagDAO(Context context) {
        this.context = context;
    }

    public ProdMagDAO openReadable()
    {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public ProdMagDAO openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
        dbHelper.close();
    }


    private ContentValues ProdMagToContentValues(ProdMag m) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID,m.get_ID()) ;
        cv.put(COLUMN_NOM,m.getNom());
        cv.put(COLUMN_PRODUIT_REFID,m.getUnProduit().get_ID()) ;
        cv.put(COLUMN_MAGASIN_REFID,m.getUnMagasin().get_ID()) ;


        return cv;
    }

    public ProdMag insert (ProdMag m)
    {
        ContentValues cv = ProdMagToContentValues(m) ;

        long id = db.insert(TABLE_PRODMAG,null, cv) ;
        m.set_ID((int) id);

        ProdMag check = (id != -1) ? m : null;
        return check;
    }

    public void delete(ProdMag m) {
        String whereClause = COLUMN_ID + " = " + m.get_ID();
        db.delete(TABLE_PRODMAG, whereClause, null);
    }

    public long update(ProdMag m) {
        ContentValues cv = ProdMagToContentValues(m);

        String whereClause = COLUMN_ID + " = " + m.get_ID();
        long number = db.update(TABLE_PRODMAG, cv, whereClause,null);
        return  number;
    }

}
