package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Utilisateur;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;

/**
 * Created by student on 10-07-17.
 */

public class UtilisateurDAO {

//region TABLE DESCRIPTION and SQL STATEMENTS

    public static final String TABLE_UTILISATEUR = "utilisateur";

    public static final String  COLUMN_ID        =  "_id" ;
    public static final String  COLUMN_LOGIN     =  "login";
    public static final String  COLUMN_NOM        =  "nom" ;

    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_UTILISATEUR + " ( "
            + COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_LOGIN    + " TEXT NOT NULL, "
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

    private ContentValues UtilisateurToContentValues(Utilisateur u) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, u.get_ID()) ;
        cv.put(COLUMN_LOGIN, u.getLogin()) ;
        cv.put(COLUMN_NOM, u.getNom()) ;
        return cv;
    }

    public Utilisateur insert(Utilisateur u)
    {

        ContentValues cv = UtilisateurToContentValues(u) ;

        long id = db.insert(TABLE_UTILISATEUR,null, cv) ;
        u.set_ID((int) id);

        Utilisateur check = (id != -1) ? u : null;
        return check;

    }

    public void delete(Utilisateur u) {
        String whereClause = COLUMN_ID + " = " + u.get_ID();
        db.delete(TABLE_UTILISATEUR, whereClause, null);
    }



    public long update(Utilisateur u) {
        ContentValues cv = UtilisateurToContentValues(u);

        String whereClause = COLUMN_ID + " = " + u.get_ID();
        long number = db.update(TABLE_UTILISATEUR, cv, whereClause,null);
        return  number;
    }


    private Utilisateur cursorToUtilisateur(Cursor c) {
        int id = c.getInt(c.getColumnIndex(COLUMN_ID));
        String login  = c.getString(c.getColumnIndex(COLUMN_LOGIN));
        String nom = c.getString(c.getColumnIndex(COLUMN_NOM));
         return new Utilisateur(id, login, nom);
    }

    public Utilisateur getUserById(int id)
    {
        String where = COLUMN_ID + " = " + id;

        Cursor c = db.query(TABLE_UTILISATEUR,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0){
            c.moveToFirst();
            Utilisateur u = cursorToUtilisateur(c);
            return u;
        }
        return null;
    }

    public Utilisateur getUserByLogin(String login)
    {
        String where = COLUMN_LOGIN + " LIKE " + login;

        Cursor c = db.query(TABLE_UTILISATEUR,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0){
            c.moveToFirst();
            Utilisateur u = cursorToUtilisateur(c);
            return u;
        }
        return null;
    }

    public Utilisateur[] getAll(){

        Cursor c = db.query(TABLE_UTILISATEUR,null, null, null, null, null, null);

        int count = c.getCount();

        if(count > 0) {
            Utilisateur[] utilisateurs = new Utilisateur[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                utilisateurs[i] = cursorToUtilisateur(c);
            }
            return utilisateurs;
        }
        return null;
    }

}





