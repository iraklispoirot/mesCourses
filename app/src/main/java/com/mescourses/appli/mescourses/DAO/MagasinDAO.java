package com.mescourses.appli.mescourses.DAO;

import android.app.Activity;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Magasin;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;
import com.mescourses.appli.mescourses.modeles.Utilisateur;

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

        // prodmag
        String whereClause = ProdMagDAO.COLUMN_MAGASIN_REFID + " = " + m.get_ID() ;
        db.delete(ProdMagDAO.TABLE_PRODMAG, whereClause, null);

        // panier
        whereClause = PanierDAO.COLUMN_MAGASIN_REFID + " = " + m.get_ID() ;
        db.delete(ProdMagDAO.TABLE_PRODMAG, whereClause, null);

        // magasin
        whereClause = COLUMN_ID + " = " + m.get_ID();
        db.delete(TABLE_MAGASIN, whereClause, null);
    }

    public long update(Magasin m) {
        ContentValues cv = MagasinToContentValues(m);

        String whereClause = COLUMN_ID + " = " + m.get_ID();
        long number = db.update(TABLE_MAGASIN, cv, whereClause,null);
        return  number;
    }

    private Magasin cursorToMagasin(Cursor c) {
        int id         = c.getInt(c.getColumnIndex(COLUMN_ID));
        int refid      = c.getInt(c.getColumnIndex(COLUMN_UTILISATEUR_REFID)) ;

        UtilisateurDAO dao = new UtilisateurDAO(context);
        dao.openReadable();
        Utilisateur unUtilisateur = dao.getUserById(refid);
        dao.close();

        String name    = c.getString(c.getColumnIndex(COLUMN_NOM));
        String address = c.getString(c.getColumnIndex(COLUMN_ADRESSE));
        String desc    = c.getString(c.getColumnIndex(COLUMN_DESCRIPTION));
        float  poslat  = c.getFloat(c.getColumnIndex(COLUMN_POSLATITUDE)) ;
        float  poslong = c.getFloat(c.getColumnIndex(COLUMN_POSLONGITUDE)) ;
        return new Magasin(id, unUtilisateur, name, address, desc,poslat,poslong);
    }


    public Magasin getById(int id)
    {
        String where = COLUMN_ID + " = " + id;

        Cursor c = db.query(TABLE_MAGASIN,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0){
            c.moveToFirst();
            Magasin m = cursorToMagasin(c);
            return m;
        }
        return null;
    }

    public Magasin[] getAll(){

        Cursor c = db.query(TABLE_MAGASIN,null, null, null, null, null, null);

        int count = c.getCount();

        if(count > 0) {
            Magasin[] magasins = new Magasin[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                magasins[i] = cursorToMagasin(c);
            }
            return magasins;
        }
        return null;
    }

    public Magasin[] getMagasinsByUserID(int userid)
    {
        String where = COLUMN_UTILISATEUR_REFID + " = " + userid;

        Cursor c = db.query(TABLE_MAGASIN,null,where,null,null,null,null );
        int count = c.getCount();
        if(count > 0) {
            Magasin[] magasins = new Magasin[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                magasins[i] = cursorToMagasin(c);
            }
            return magasins;
        }
        return null;
    }

}
