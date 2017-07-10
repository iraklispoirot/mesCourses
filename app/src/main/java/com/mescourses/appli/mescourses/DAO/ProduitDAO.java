package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Produit;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;


/**
 * Created by student on 10-07-17.
 */

public class ProduitDAO {

//region  TABLE DESCRIPTION and SQL STATEMENTS

    public static final String  TABLE_PRODUIT = "produit";

    public static final String  COLUMN_ID                =  "_id" ;
    public static final String  COLUMN_UTILISATEUR_REFID =  "refid_utilisateur";
    public static final String  COLUMN_NOM               =  "nom";
    public static final String  COLUMN_MARQUE            =  "marque" ;
    public static final String  COLUMN_DESCRIPTION       =  "description";
    public static final String  COLUMN_TYPE              =  "type";
    public static final String  COLUMN_PRIX              =  "prix";
    public static final String  COLUMN_POIDS             =  "poids";
    public static final String  COLUMN_LIENPHOTO         =  "lienPhoto";
    public static final String  COLUMN_MANQUE            =  "manque";


    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PRODUIT + " ( "
                    + COLUMN_ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_UTILISATEUR_REFID    + " INTEGER NOT NULL, "
                    + COLUMN_NOM                  + " TEXT NOT NULL, "
                    + COLUMN_MARQUE               + " TEXT NOT NULL"
                    + COLUMN_DESCRIPTION          + " TEXT, "
                    + COLUMN_TYPE                 + " TEXT, "
                    + COLUMN_PRIX                 + " FLOAT, "
                    + COLUMN_POIDS                + " FLOAT, "
                    + COLUMN_LIENPHOTO            + " TEXT, "
                    + COLUMN_MANQUE               + " INTEGER"
                    + " );";

    public static final String DELETE_REQUEST =
            "DROP TABLE IF EXISTS " + TABLE_PRODUIT + ";";
//endregion

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public ProduitDAO(Context context) {
        this.context = context;
    }

    public ProduitDAO openReadable()
    {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public ProduitDAO openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
        dbHelper.close();
    }

    private ContentValues ProduitToContentValues(Produit p) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID,p.get_ID()) ;
        cv.put(COLUMN_UTILISATEUR_REFID,p.getUnUtilisateur().get_ID()) ;
        cv.put(COLUMN_NOM,p.getNom());
        cv.put(COLUMN_MARQUE,p.getMarque());
        cv.put(COLUMN_DESCRIPTION,p.getDescription());
        cv.put(COLUMN_TYPE,p.getDescription());
        cv.put(COLUMN_PRIX,p.getPrix());
        cv.put(COLUMN_POIDS,p.getPoids());
        cv.put(COLUMN_LIENPHOTO,p.getLienPhoto());
        cv.put(COLUMN_MANQUE,p.getManque());

        return cv;
    }

    public Produit insert (Produit p)
    {
        ContentValues cv = ProduitToContentValues(p) ;

        long id = db.insert(TABLE_PRODUIT,null, cv) ;
        p.set_ID((int) id); ;

        Produit check = (id != -1) ? p : null;
        return check;
    }

    public void delete(Produit p) {
        String whereClause = COLUMN_ID + " = " + p.get_ID();
        db.delete(TABLE_PRODUIT, whereClause, null);
    }

    public long update(Produit p) {
        ContentValues cv = ProduitToContentValues(p);

        String whereClause = COLUMN_ID + " = " + p.get_ID();
        long number = db.update(TABLE_PRODUIT, cv, whereClause,null);
        return  number;
    }

}
