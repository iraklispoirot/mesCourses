package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Magasin;
import com.mescourses.appli.mescourses.modeles.ProdMag;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;
import com.mescourses.appli.mescourses.modeles.Produit;
import com.mescourses.appli.mescourses.modeles.Utilisateur;

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
    public static final String  COLUMN_UTILISATEUR_REFID =  "refid_utilisateur";
    public static final String  COLUMN_FAVORI            =  "favori";

    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PRODMAG + " ( "
                    + COLUMN_ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NOM                  + " TEXT NOT NULL, "
                    + COLUMN_PRODUIT_REFID        + " INTEGER NOT NULL, "
                    + COLUMN_MAGASIN_REFID        + " INTEGER NOT NULL,  "
                    + COLUMN_UTILISATEUR_REFID    + " INTEGER NOT NULL,  "
                    + COLUMN_FAVORI               + " INTEGER"
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
        cv.put(COLUMN_UTILISATEUR_REFID,m.getUnMagasin().get_ID()) ;
        cv.put(COLUMN_FAVORI,m.getFavori()) ;
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


    private ProdMag cursorToProdMag(Cursor c) {
        int id     = c.getInt(c.getColumnIndex(COLUMN_ID));
        String nom = c.getString(c.getColumnIndex(COLUMN_NOM));

        int refid  = c.getInt(c.getColumnIndex(COLUMN_PRODUIT_REFID)) ;
        ProduitDAO dao_produit = new ProduitDAO(context);
        dao_produit.openReadable();
        Produit unProduit = dao_produit.getProduitById(refid) ;
        dao_produit.close();

        refid  = c.getInt(c.getColumnIndex(COLUMN_MAGASIN_REFID)) ;
        MagasinDAO dao_magasin = new MagasinDAO(context);
        dao_magasin.openReadable();
        Magasin unMagasin = dao_magasin.getById(refid);
        dao_magasin.close();

        UtilisateurDAO dao = new UtilisateurDAO(context);
        dao.openReadable();
        Utilisateur unUtilisateur = dao.getUserById(refid);
        dao.close();

        int favori = c.getInt(c.getColumnIndex(COLUMN_FAVORI));

        return new ProdMag(id, nom, unProduit, unMagasin, unUtilisateur, favori );
    }

    public ProdMag[] getAll(){

        Cursor c = db.query(TABLE_PRODMAG,null, null, null, null, null, null);

        int count = c.getCount();

        if(count > 0) {
            ProdMag[] prodmags = new ProdMag[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                prodmags[i] = cursorToProdMag(c);
            }
            return prodmags;
        }
        return null;
    }

    public ProdMag[] getByUserId(int userid)
    {
        String where = COLUMN_UTILISATEUR_REFID + " = " + userid;

        Cursor c = db.query(TABLE_PRODMAG,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0) {
            ProdMag[] prodmags = new ProdMag[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                prodmags[i] = cursorToProdMag(c);
            }
            return prodmags;
        }
        return null;
    }

    public ProdMag getById(int id)
    {
        String where = COLUMN_ID + " = " + id;

        Cursor c = db.query(TABLE_PRODMAG,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0){
            c.moveToFirst();
            ProdMag m = cursorToProdMag(c);
            return m;
        }
        return null;
    }

    public ProdMag[] getByUserIdAndMagasinID(int userid, int magasinid)
    {
        String where = COLUMN_UTILISATEUR_REFID + " = " + userid + " AND " + COLUMN_MAGASIN_REFID + " = " + magasinid;

        Cursor c = db.query(TABLE_PRODMAG,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0) {
            ProdMag[] prodmags = new ProdMag[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                prodmags[i] = cursorToProdMag(c);
            }
            return prodmags;
        }
        return null;
    }
    public ProdMag[] getByUserIdAndProduitID(int userid, int produitid)
    {
        String where = COLUMN_UTILISATEUR_REFID + " = " + userid + " AND " + COLUMN_MAGASIN_REFID + " = " + produitid;

        Cursor c = db.query(TABLE_PRODMAG,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0) {
            ProdMag[] prodmags = new ProdMag[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                prodmags[i] = cursorToProdMag(c);
            }
            return prodmags;
        }
        return null;
    }

}
