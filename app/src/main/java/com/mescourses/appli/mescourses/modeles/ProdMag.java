package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class ProdMag implements Parcelable{

    private    long       _ID ;
    private    String     nom ;
    private Produit unProduit  ;
    private Magasin unMagasin  ;

    public ProdMag(long _ID, String nom, Produit unProduit, Magasin unMagasin) {
        this(nom,unProduit,unMagasin) ;
        this._ID = _ID;
    }

    public ProdMag(String nom, Produit unProduit, Magasin unMagasin) {
        this._ID = -1;
        this.nom = nom;
        this.unProduit = unProduit;
        this.unMagasin = unMagasin;
    }

    @Override
    public String toString() {
        return
                "_ID:" + _ID +'/' +
                "nom:" + nom + '/' +
                "Produit:" + unProduit + '/' +
                "Magasin:" + unMagasin
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

    @Override
    public int describeContents() {
        return 2;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(_ID);
        dest.writeString(nom);
        dest.writeParcelable(unProduit,1);
        dest.writeParcelable(unMagasin,2);
    }

    //region CREATOR
    public ProdMag(Parcel in) {
        this._ID         = in.readLong() ;
        this.nom         = in.readString();
        this.unProduit   = in.readParcelable(Produit.class.getClassLoader()) ;
        this.unMagasin   = in.readParcelable(Magasin.class.getClassLoader()) ;

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




