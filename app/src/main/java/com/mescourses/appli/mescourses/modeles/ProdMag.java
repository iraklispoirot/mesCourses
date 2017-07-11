package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class ProdMag implements Parcelable{

    private    long      _ID ;
    private    String    nom ;
    private Produit      unProduit  ;
    private Magasin      unMagasin  ;
    private Utilisateur  unUtilisateur ;
    private int          favori        ;

    public ProdMag(long _ID, String nom, Produit unProduit, Magasin unMagasin,  Utilisateur  unUtilisateur, int favori) {
        this(nom,unProduit,unMagasin, unUtilisateur, favori) ;
        this._ID = _ID;
    }

    public ProdMag(String nom, Produit unProduit, Magasin unMagasin, Utilisateur  unUtilisateur, int favori) {
        this._ID = -1;
        this.nom = nom;
        this.unProduit = unProduit;
        this.unMagasin = unMagasin;
        this.unUtilisateur = unUtilisateur;
        this.favori = favori ;
    }

    @Override
    public String toString() {
        return
                "_ID:" + _ID +'/' +
                "nom:" + nom + '/' +
                "Produit:" + unProduit + '/' +
                "Magasin:" + unMagasin + '/' +
                "Utilisateur:" + unUtilisateur
                ;
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

    public Produit getUnProduit() {
        return unProduit;
    }

    public void setUnProduit(Produit unProduit) {
        this.unProduit = unProduit;
    }

    public Magasin getUnMagasin() {
        return unMagasin;
    }

    public void setUnMagasin(Magasin unMagasin) {
        this.unMagasin = unMagasin;
    }

    public Utilisateur getUnUtilisateur() {
        return unUtilisateur;
    }

    public void setUnUtilisateur(Utilisateur unUtilisateur) {
        this.unUtilisateur = unUtilisateur;
    }

    public int getFavori() {
        return favori;
    }

    public void setFavori(int favori) {
        this.favori = favori;
    }

    @Override
    public int describeContents() {
        return 3;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(_ID);
        dest.writeString(nom);
        dest.writeParcelable(unProduit,1);
        dest.writeParcelable(unMagasin,2);
        dest.writeParcelable(unUtilisateur,3);
        dest.writeInt(favori);
    }

    //region CREATOR
    public ProdMag(Parcel in) {
        this._ID             = in.readLong() ;
        this.nom             = in.readString();
        this.unProduit       = in.readParcelable(Produit.class.getClassLoader()) ;
        this.unMagasin       = in.readParcelable(Magasin.class.getClassLoader()) ;
        this.unUtilisateur   = in.readParcelable(Utilisateur.class.getClassLoader()) ;
        this.favori          = in.readInt() ;
    }

    static Creator<ProdMag> CREATOR = new Creator<ProdMag>() {
        @Override
        public ProdMag createFromParcel(Parcel parcel) {
            return new ProdMag(parcel);
        }

        @Override
        public ProdMag[] newArray(int i) {
            return new ProdMag[i];
        }
    };
    //endregion



}




