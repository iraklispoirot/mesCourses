package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Utilisateur;

/**
 * Created by student on 10-07-17.
 */

public class UtilisateurDAO {

//region TABLE DESCRIPTION and SQL STATEMENTS

    public static final String TABLE_UTILISATEUR = "utilisateur";

    public static final String  COLUMN_ID        =  "_id" ;
    public static final String  COLUMN_LOGIN     =  "login";
    public static final String  COLUMN_DATABASE  =  "database";
    public static final String  COLUMN_NOM      =  "nom" ;

    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_UTILISATEUR + " ( "
            + COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LOGIN    + " TEXT NOT NULL, "
            + COLUMN_DATABASE + " TEXT NOT NULL, "
            + COLUMN_NOM     + " TEXT"
            + " );";
    public static final String DELETE_REQUEST =
            "DROP TABLE IF EXISTS " + TABLE_UTILISATEUR + ";";
//endregion

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public UtilisateurDAO(Context context) {
        this.context = context;
    }

    public UtilisateurDAO openReadable()
    {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public UtilisateurDAO openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
        dbHelper.close();
    }

    private ContentValues UtilisateurToContentValues(Utilisateur p) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, p.get_ID()) ;
        cv.put(COLUMN_DATABASE, p.getDatabase()) ;
        cv.put(COLUMN_LOGIN, p.getLogin()) ;
        cv.put(COLUMN_NOM, p.getNom()) ;
        return cv;
    }

    public Utilisateur insert(Utilisateur u)
    {

        ContentValues cv = UtilisateurToContentValues(u) ;

        long id = db.insert(TABLE_UTILISATEUR,null, cv) ;
        u.setId((int) id) ;

        Utilisateur check = (id != -1) ? u : null;
        return check;

    }

    public void delete(Utilisateur u) {
        String whereClause = COLUMN_ID + " = " + u.getId();
        db.delete(TABLE_UTILISATEUR, whereClause, null);
    }



    public long update(Utilisateur u) {
        ContentValues cv = UtilisateurToContentValues(u);

        String whereClause = COLUMN_ID + " = " + u.getId();
        long number = db.update(TABLE_UTILISATEUR, cv, whereClause,null);
        return  number;
    }



}





