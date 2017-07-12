package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Course;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;
import com.mescourses.appli.mescourses.modeles.Utilisateur;

/**
 * Created by student on 10-07-17.
 */

public class CourseDAO {

//region TABLE DESCRIPTION and SQL STATEMENTS

    public static final String TABLE_COURSE = "course";

    public static final String  COLUMN_ID        =  "_id" ;
    public static final String  COLUMN_NOM       =  "nom" ;
    public static final String  COLUMN_UTILISATEUR_REFID =  "refid_utilisateur";

    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_COURSE + " ( "
                    + COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NOM     + " TEXT NOT NULL,"
                    + COLUMN_UTILISATEUR_REFID + " INTEGER NOT NULL "
                    + " );";
    public static final String DELETE_REQUEST =
            "DROP TABLE IF EXISTS " + TABLE_COURSE+ ";";
//endregion

    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public CourseDAO(Context context) {
        this.context = context;
    }

    public CourseDAO openReadable()
    {
        dbHelper = new DbHelper(context);
        db = dbHelper.getReadableDatabase();
        return this;
    }

    public CourseDAO openWritable() {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        db.close();
        dbHelper.close();
    }

    private ContentValues CourseToContentValues(Course c) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, c.get_ID()) ;
        cv.put(COLUMN_NOM, c.getNom()) ;
        cv.put(COLUMN_UTILISATEUR_REFID,c.getUnUtilisateur().get_ID()) ;
        return cv;
    }

    public Course insert(Course u)
    {

        ContentValues cv = CourseToContentValues(u) ;

        long id = db.insert(TABLE_COURSE,null, cv) ;
        u.set_ID((int) id);

        Course check = (id != -1) ? u : null;
        return check;

    }

    public void delete(Course u) {

        // panierDAO
        String whereClause = PanierDAO.COLUMN_COURSE_REFID + " = " + u.get_ID();
        db.delete(PanierDAO.TABLE_PANIER, whereClause, null);

        whereClause = COLUMN_ID + " = " + u.get_ID();
        db.delete(TABLE_COURSE, whereClause, null);
        
    }



    public long update(Course u) {
        ContentValues cv = CourseToContentValues(u);

        String whereClause = COLUMN_ID + " = " + u.get_ID();
        long number = db.update(TABLE_COURSE, cv, whereClause,null);
        return  number;
    }


    private Course cursorToCourse(Cursor c) {
        int id     = c.getInt(c.getColumnIndex(COLUMN_ID));
        String nom = c.getString(c.getColumnIndex(COLUMN_NOM));
        int refid  = c.getInt(c.getColumnIndex(COLUMN_UTILISATEUR_REFID)) ;

        UtilisateurDAO dao = new UtilisateurDAO(context);
        dao.openReadable();
        Utilisateur unUtilisateur = dao.getUserById(refid);
        dao.close();


        return new Course(id, nom, unUtilisateur );
    }

    public Course[] getAll(){

        Cursor c = db.query(TABLE_COURSE,null, null, null, null, null, null);

        int count = c.getCount();

        if(count > 0) {
            Course[] courses = new Course[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                courses[i] = cursorToCourse(c);
            }
            return courses;
        }
        return null;
    }

    public Course[] getCoursesByUserId(int userid)
    {
        String where = COLUMN_UTILISATEUR_REFID + " = " + userid;

        Cursor c = db.query(TABLE_COURSE,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0) {
            Course[] courses = new Course[count];

            for(int i = 0; i < count; i++) {
                c.moveToPosition(i);
                courses[i] = cursorToCourse(c);
            }
            return courses;
        }
        return null;
    }

    public Course getById(int id)
    {
        String where = COLUMN_ID + " = " + id;

        Cursor c = db.query(TABLE_COURSE,null,where,null,null,null,null );

        int count = c.getCount();
        if(count > 0){
            c.moveToFirst();
            Course m = cursorToCourse(c);
            return m;
        }
        return null;
    }
}
