package com.mescourses.appli.mescourses.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mescourses.appli.mescourses.DAO.CourseDAO;
import com.mescourses.appli.mescourses.DAO.MagasinDAO;
import com.mescourses.appli.mescourses.DAO.PanierDAO;
import com.mescourses.appli.mescourses.DAO.ProdMagDAO;
import com.mescourses.appli.mescourses.DAO.ProduitDAO;
import com.mescourses.appli.mescourses.DAO.UtilisateurDAO;

/**
 * Created by omar on 11/07/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "com.mescourses.appli.sqlite.database";
    public static final int DB_VERSION = 1;

    public DbHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase theDB) {
        theDB.execSQL(UtilisateurDAO.CREATE_REQUEST) ;
        theDB.execSQL(CourseDAO.CREATE_REQUEST) ;
        theDB.execSQL(ProduitDAO.CREATE_REQUEST) ;
        theDB.execSQL(MagasinDAO.CREATE_REQUEST) ;
        theDB.execSQL(ProdMagDAO.CREATE_REQUEST) ;
        theDB.execSQL(PanierDAO.CREATE_REQUEST) ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase theDB, int i, int i1) {
        theDB.execSQL(UtilisateurDAO.DELETE_REQUEST) ;
        theDB.execSQL(CourseDAO.DELETE_REQUEST) ;
        theDB.execSQL(ProduitDAO.DELETE_REQUEST) ;
        theDB.execSQL(MagasinDAO.DELETE_REQUEST) ;
        theDB.execSQL(ProdMagDAO.DELETE_REQUEST) ;
        theDB.execSQL(PanierDAO.DELETE_REQUEST) ;

        theDB.execSQL(UtilisateurDAO.CREATE_REQUEST) ;
        theDB.execSQL(CourseDAO.CREATE_REQUEST) ;
        theDB.execSQL(ProduitDAO.CREATE_REQUEST) ;
        theDB.execSQL(MagasinDAO.CREATE_REQUEST) ;
        theDB.execSQL(ProdMagDAO.CREATE_REQUEST) ;
        theDB.execSQL(PanierDAO.CREATE_REQUEST) ;

    }
}
