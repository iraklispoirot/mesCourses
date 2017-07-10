package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class Magasin implements Parcelable{

   private long    _ID ;
   private Utilisateur unUtilisateur ;
   private String  nom ;
   private String  adresse ;
   private String  description ;
   private float   pos_latitude  ;
   private float   pos_longitude ;

    @Override
    public String toString() {
        return  "_ID:" + _ID + '/' +
                "nom:" + nom + '/' +
                "adresse:" + adresse + '/' +
                "description:" + description + '/' +
                "pos_latitude:" + pos_latitude + '/' +
                "pos_longitude:" + pos_longitude
                ;
    }

    public Magasin(long _ID, Utilisateur unUtilisateur ,String nom, String adresse, String description, float pos_latitude, float pos_longitude) {
        this(unUtilisateur, nom,adresse,description, pos_latitude,pos_longitude) ;
        this._ID = _ID;
    }

    public Magasin(Utilisateur unUtilisateur, String nom, String adresse, String description, float pos_latitude, float pos_longitude) {
        this._ID = -1 ;
        this.unUtilisateur = unUtilisateur ;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description ;
        this.pos_latitude = pos_latitude;
        this.pos_longitude = pos_longitude;
    }

    public Magasin(long _ID, Utilisateur unUtilisateur, String nom, String adresse) {
        this(unUtilisateur, nom,adresse) ;
        this._ID = _ID;
    }

    public Magasin(Utilisateur unUtilisateur, String nom, String adresse) {
        this._ID = -1 ;
        this.unUtilisateur = unUtilisateur ;
        this.nom = nom;
        this.adresse = adresse;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public float getPos_latitude() {
        return pos_latitude;
    }

    public void setPos_latitude(float pos_latitude) {
        this.pos_latitude = pos_latitude;
    }

    public float getPos_longitude() {
        return pos_longitude;
    }

    public void setPos_longitude(float pos_longitude) {
        this.pos_longitude = pos_longitude;
    }

    @Override
    public int describeContents() {
        return 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

       dest.writeLong(_ID);
       dest.writeParcelable(unUtilisateur,1);
       dest.writeString(nom);
       dest.writeString(adresse);
       dest.writeString(description) ;
       dest.writeFloat(pos_latitude);
       dest.writeFloat(pos_longitude);
    }

    //region CREATOR
    public Magasin(Parcel in) {
        this._ID         = in.readLong() ;
        this.unUtilisateur = in.readParcelable(Utilisateur.class.getClassLoader()) ;
        this.nom         = in.readString();
        this.adresse     = in.readString();
        this.description = in.readString();
        this.pos_latitude   = in.readFloat() ;
        this.pos_longitude  = in.readFloat() ;

    }

    static Creator<Magasin> CREATOR = new Creator<Magasin>() {
        @Override
        public Magasin createFromParcel(Parcel parcel) {
            return new Magasin(parcel);
        }

        @Override
        public Magasin[] newArray(int i) {
            return new Magasin[i];
        }
    };
    //endregion

}


