package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class Course implements Parcelable{

    private    long         _ID ;
    private    String       nom ;
    private    Utilisateur  unUtilisateur ;

    public Course(long _ID, String nom, Utilisateur unUtilisateur) {
        this._ID = _ID;
        this.nom = nom;
        this.unUtilisateur = unUtilisateur ;
    }

    public Course(String nom, Utilisateur unUtilisateur) {
        this._ID = - 1 ;
        this.nom = nom;
        this.unUtilisateur = unUtilisateur ;
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

    public Utilisateur getUnUtilisateur() {
        return unUtilisateur;
    }

    public void setUnUtilisateur(Utilisateur unUtilisateur) {
        this.unUtilisateur = unUtilisateur;
    }

    @Override
    public String toString() {
        return "_ID:" + _ID +
                "/nom:" + nom
                ;
    }


    @Override
    public int describeContents() {
        return 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
      dest.writeLong(_ID);
      dest.writeString(nom);
      dest.writeParcelable(unUtilisateur,1);
     }

    //region CREATOR
    public Course(Parcel in) {
        this._ID            = in.readLong() ;
        this.nom            = in.readString();
        this.unUtilisateur = in.readParcelable(Utilisateur.class.getClassLoader()) ;
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
