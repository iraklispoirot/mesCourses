package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class Produit implements Parcelable{

    private long     _ID          ;
    private String   nom         ;
    private String   marque      ;
    private String   description ;
    private String   type        ;
    private float    prix        ;
    private float    poids       ;
    private String   lienPhoto   ;


    public Produit(String nom, String marque, String description, String type, float prix, float poids, String lienPhoto) {
        this._ID         = -1;
        this.nom         = nom;
        this.marque      = marque;
        this.description = description;
        this.type        = type;
        this.prix        = prix;
        this.poids       = poids;
        this.lienPhoto   = lienPhoto;
    }

    public Produit(long ID, String nom, String marque, String description, String type, float prix, float poids, String lienPhoto) {
        this(nom,marque,description,type,prix,poids,lienPhoto) ;
        this._ID = ID;
    }


    public Produit(String nom, String marque, String type) {
        this._ID = -1;
        this.nom = nom;
        this.marque = marque;
        this.type = type;
    }


    public Produit(long ID, String nom, String marque, String type) {
        this(nom,marque,type) ;
        this._ID = ID;
    }

    @Override
    public String toString() {

        return ("ID:" + this._ID + "/" + "Nom:" + this.nom + "/" + "Marque:" + this.marque + "/" + "Type:" +
        this.type + "/" + "Poids:" + this.poids + "/" + "Prix:" + this.prix) ;

    }


    public long getID() {
        return _ID;
    }

    public void setID(long ID) {
        this._ID = ID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public String getLienPhoto() {
        return lienPhoto;
    }

    public void setLienPhoto(String lienPhoto) {
        this.lienPhoto = lienPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

         dest.writeLong(_ID);
         dest.writeString(nom) ;
         dest.writeString(marque) ;
         dest.writeString(description) ;
         dest.writeString(type) ;
         dest.writeFloat(prix)  ;
         dest.writeFloat(poids) ;
         dest.writeString(lienPhoto) ;
    }
    //region CREATOR
    public Produit(Parcel in) {
        this._ID         = in.readLong() ;
        this.nom         = in.readString();
        this.marque      = in.readString();
        this.description = in.readString();
        this.type        = in.readString();
        this.prix        = in.readFloat() ;
        this.poids       = in.readFloat() ;
        this.lienPhoto   = in.readString();
    }

    static Creator<Produit> CREATOR = new Creator<Produit>() {
        @Override
        public Produit createFromParcel(Parcel parcel) {
            return new Produit(parcel);
        }

        @Override
        public Produit[] newArray(int i) {
            return new Produit[i];
        }
    };
    //endregion


}
