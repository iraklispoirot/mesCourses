package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class Produit implements Parcelable{

    private long        _ID         ;
    private Utilisateur unUtilisateur ;
    private String      nom         ;
    private String      marque      ;
    private String      description ;
    private String      type        ;
    private float       prix        ;
    private float       poids       ;
    private String      lienPhoto   ;
    private int         manque      ;


    public Produit(Utilisateur unUtilisateur, String nom, String marque, String description, String type, float prix, float poids, String lienPhoto, int manque) {
        this._ID           = -1;
        this.unUtilisateur = unUtilisateur ;
        this.nom           = nom;
        this.marque        = marque;
        this.description   = description;
        this.type          = type;
        this.prix          = prix;
        this.poids         = poids;
        this.lienPhoto     = lienPhoto;
        this.manque        = manque;
    }

    public Produit(long ID, Utilisateur unUtilisateur, String nom, String marque, String description, String type, float prix, float poids, String lienPhoto, int manque) {
        this(unUtilisateur, nom,marque,description,type,prix,poids,lienPhoto,manque) ;
        this._ID = ID;
    }


    public Produit(Utilisateur unUtilisateur, String nom, String marque, String type) {
        this._ID = -1;
        this.unUtilisateur = unUtilisateur ;
        this.nom = nom;
        this.marque = marque;
        this.type = type;
    }


    public Produit(long ID, Utilisateur unUtilisateur, String nom, String marque, String type) {
        this(unUtilisateur,nom,marque,type) ;
        this._ID = ID;
    }

    @Override
    public String toString() {

        return ("ID:" + this._ID + "/" + "Nom:" + this.nom + "/" + "Marque:" + this.marque + "/" + "Type:" +
        this.type + "/" + "Poids:" + this.poids + "/" + "Prix:" + this.prix + "Manque:" + this.manque) ;

    }


    public long get_ID() {
        return _ID;
    }

    public void set_ID(long ID) {
        this._ID = ID;
    }

    public Utilisateur getUnUtilisateur() {
        return unUtilisateur;
    }

    public void setUnUtilisateur(Utilisateur unUtilisateur) {
        this.unUtilisateur = unUtilisateur;
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

    public int getManque() {
        return manque;
    }

    public void setManque(int manque) {
        this.manque = manque;
    }

    @Override
    public int describeContents() {
        return 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

         dest.writeLong(_ID);
         dest.writeParcelable(unUtilisateur,1);
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
        this.unUtilisateur = in.readParcelable(Utilisateur.class.getClassLoader()) ;
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
