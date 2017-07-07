package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class Utilisateur implements Parcelable {

    private long      _ID      ;
    private String    login    ;

    private String    database ;
    private String    nom      ;


    public Utilisateur(long _ID, String login, String nom) {

        this(login,nom) ;
        this._ID = _ID;
        this.database = _ID + ".db" ;
    }

    public Utilisateur(String login, String nom) {
        this._ID   = -1 ;
        this.login = login;
        this.nom   = nom;
    }

    public Utilisateur(String login) {
        this._ID   = -1 ;
        this.login = login;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return  "_ID:" + _ID +
                "/login:" + login +
                "/database:" + database +
                "/nom:" + nom ;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

        dest.writeLong(_ID);
        dest.writeString(login);
        dest.writeString(database);
        dest.writeString(nom);
    }

    //region CREATOR
    public Utilisateur(Parcel in) {
        this._ID         = in.readLong() ;
        this.login       = in.readString() ;
        this.database    = in.readString() ;
        this.nom         = in.readString() ;
    }

    static Creator<Utilisateur> CREATOR = new Creator<Utilisateur>() {
        @Override
        public Utilisateur createFromParcel(Parcel parcel) {
            return new Utilisateur(parcel);
        }

        @Override
        public Utilisateur[] newArray(int i) {
            return new Utilisateur[i];
        }
    };
    //endregion

}


