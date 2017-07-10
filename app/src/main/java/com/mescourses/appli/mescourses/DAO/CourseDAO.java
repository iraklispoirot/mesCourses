package com.mescourses.appli.mescourses.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mescourses.appli.mescourses.modeles.Course;
import com.mescourses.appli.mescourses.DBHelper.DbHelper ;

/**
 * Created by student on 10-07-17.
 */

public class CourseDAO {

//region TABLE DESCRIPTION and SQL STATEMENTS

    public static final String TABLE_COURSE = "course";

    public static final String  COLUMN_ID        =  "_id" ;
    public static final String  COLUMN_NOM       =  "nom" ;

    public static final String CREATE_REQUEST =
            "CREATE TABLE IF NOT EXISTS " + TABLE_COURSE + " ( "
                    + COLUMN_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_NOM     + " TEXT NOT NULL"
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
        return cv;
    }

    public Course insert(Course u)
    {

        ContentValues cv = CourseToContentValues(u) ;

        long id = db.insert(TABLE_COURSE,null, cv) ;
        u.set_ID((int) id); ;

        Course check = (id != -1) ? u : null;
        return check;

    }

    public void delete(Course u) {
        String whereClause = COLUMN_ID + " = " + u.get_ID();
        db.delete(TABLE_COURSE, whereClause, null);
    }



    public long update(Course u) {
        ContentValues cv = CourseToContentValues(u);

        String whereClause = COLUMN_ID + " = " + u.get_ID();
        long number = db.update(TABLE_COURSE, cv, whereClause,null);
        return  number;
    }
}
