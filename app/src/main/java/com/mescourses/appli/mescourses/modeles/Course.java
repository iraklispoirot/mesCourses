package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class Course implements Parcelable{

    private    long    _ID ;
    private    String  nom ;

    public Course(long _ID, String nom) {
        this._ID = _ID;
        this.nom = nom;
    }

    public Course(String nom) {
        this._ID = - 1 ;
        this.nom = nom;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "_ID:" + _ID +
                "/nom:" + nom
                ;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
      dest.writeLong(_ID);
      dest.writeString(nom);
     }

    //region CREATOR
    public Course(Parcel in) {
        this._ID         = in.readLong() ;
        this.nom         = in.readString();
    }

    static Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel parcel) {
            return new Course(parcel);
        }

        @Override
        public Course[] newArray(int i) {
            return new Course[i];
        }
    };
    //endregion


}
