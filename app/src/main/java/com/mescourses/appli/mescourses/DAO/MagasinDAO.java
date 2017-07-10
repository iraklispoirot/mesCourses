package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Magasin;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;

/**
 * Created by student on 10-07-17.
 */

public class MagasinDAO {

    //region  TABLE DESCRIPTION and SQL STATEMENTS

    public static final String  TABLE_MAGASIN = "magasin";

    public static final String  COLUMN_ID                =  "_id" ;
    public static final String  COLUMN_UTILISATEUR_REFID =  "refid_utilisateur";
    public static final String  COLUMN_NOM               =  "nom";
    public static final String  COLUMN_ADRESSE           =  "adresse" ;
    public static final String  COLUMN_DESCRIPTION       =  "description";
    public static final String  COLUMN_POSLATITUDE       =  "pos_latitude";
    public static final String  COLUMN_POSLONGITUDE      =  "pos_longitude";



    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_MAGASIN + " ( "
                    + COLUMN_ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_UTILISATEUR_REFID    + " INTEGER NOT NULL, "
                    + COLUMN_NOM                  + " TEXT NOT NULL, "
                    + COLUMN_ADRESSE              + " TEXT NOT NULL, "
                    + COLUMN_DESCRIPTION          + " TEXT, "
                    + COLUMN_POSLATITUDE          + " FLOAT, "
                    + COLUMN_POSLONGITUDE         + " FLOAT "
                    + " );";

    public static final String DELETE_REQUEST =
            "DROP TABLE IF EXISTS " + TABLE_MAGASIN + ";";
//endregion
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public MagasinDAO(Context context) {
        this.context = context;
    }

    public MagasinDAO openReadable()
    {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public MagasinDAO openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
        dbHelper.close();
    }
    private ContentValues MagasinToContentValues(Magasin m) {
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID,m.get_ID()) ;
        cv.put(COLUMN_UTILISATEUR_REFID,m.getUnUtilisateur().get_ID()) ;
        cv.put(COLUMN_NOM,m.getNom());
        cv.put(COLUMN_ADRESSE,m.getAdresse());
        cv.put(COLUMN_DESCRIPTION,m.getDescription());


        return cv;
    }

    public Magasin insert (Magasin m)
    {
        ContentValues cv = MagasinToContentValues(m) ;

        long id = db.insert(TABLE_MAGASIN,null, cv) ;
        m.set_ID((int) id);

        Magasin check = (id != -1) ? m : null;
        return check;
    }

    public void delete(Magasin m) {
        String whereClause = COLUMN_ID + " = " + m.get_ID();
        db.delete(TABLE_MAGASIN, whereClause, null);
    }

    public long update(Magasin m) {
        ContentValues cv = MagasinToContentValues(m);

        String whereClause = COLUMN_ID + " = " + m.get_ID();
        long number = db.update(TABLE_MAGASIN, cv, whereClause,null);
        return  number;
    }
}
