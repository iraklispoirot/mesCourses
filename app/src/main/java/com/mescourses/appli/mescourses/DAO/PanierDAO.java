package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Panier;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;
/**
 * Created by student on 10-07-17.
 */

public class PanierDAO {

    //region  TABLE DESCRIPTION and SQL STATEMENTS

    public static final String  TABLE_PANIER = "panier";

    public static final String  COLUMN_ID                =  "_id" ;
    public static final String  COLUMN_COURSE_REFID      =  "refid_course";
    public static final String  COLUMN_PRODUIT_REFID     =  "refid_produit";
    public static final String  COLUMN_MAGASIN_REFID     =  "refid_magasin";
    public static final String  COLUMN_QUANTITE          =  "quantite";
    public static final String  COLUMN_TROUVE            =  "trouve";

    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PANIER + " ( "
                    + COLUMN_ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_COURSE_REFID         + " INTEGER NOT NULL, "
                    + COLUMN_PRODUIT_REFID        + " INTEGER NOT NULL, "
                    + COLUMN_MAGASIN_REFID        + " INTEGER NOT NULL, "
                    + COLUMN_QUANTITE             + " INTEGER, "
                    + COLUMN_TROUVE               + " INTEGER "

                    + " );";

    public static final String DELETE_REQUEST =
            "DROP TABLE IF EXISTS " + TABLE_PANIER + ";";
//endregion

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public PanierDAO(Context context) {
        this.context = context;
    }

    public PanierDAO openReadable()
    {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public PanierDAO openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
        dbHelper.close();
    }


    private ContentValues PanierToContentValues(Panier p) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID,p.get_ID()) ;
        cv.put(COLUMN_COURSE_REFID,p.getUneCourse().get_ID()) ;
        cv.put(COLUMN_PRODUIT_REFID,p.getUnProduit().get_ID()) ;
        cv.put(COLUMN_MAGASIN_REFID,p.getUnMagasin().get_ID()) ;
        cv.put(COLUMN_QUANTITE, p.getQuantite());
        cv.put(COLUMN_TROUVE,p.getTrouve()) ;
        return cv;
    }

    public Panier insert (Panier m)
    {
        ContentValues cv = PanierToContentValues(m) ;

        long id = db.insert(TABLE_PANIER,null, cv) ;
        m.set_ID((int) id);

        Panier check = (id != -1) ? m : null;
        return check;
    }

    public void delete(Panier m) {
        String whereClause = COLUMN_ID + " = " + m.get_ID();
        db.delete(TABLE_PANIER, whereClause, null);
    }

    public long update(Panier m) {
        ContentValues cv = PanierToContentValues(m);

        String whereClause = COLUMN_ID + " = " + m.get_ID();
        long number = db.update(TABLE_PANIER, cv, whereClause,null);
        return  number;
    }

}
