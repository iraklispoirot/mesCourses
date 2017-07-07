package com.mescourses.appli.mescourses.modeles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 07-07-17.
 */

public class Panier implements Parcelable{

    private  long      _ID ;
    private Course uneCourse ;
    private Magasin unMagasin ;
    private Produit unProduit ;
    private  int       trouve    ;

    public Panier(long _ID, Course uneCourse, Magasin unMagasin, Produit unProduit) {
        this(uneCourse,unMagasin,unProduit) ;
        this._ID = _ID;
    }

    public Panier(Course uneCourse, Magasin unMagasin, Produit unProduit) {
        this._ID = -1 ;
        this.uneCourse = uneCourse;
        this.unMagasin = unMagasin;
        this.unProduit = unProduit;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public Course getUneCourse() {
        return uneCourse;
    }

    public void setUneCourse(Course uneCourse) {
        this.uneCourse = uneCourse;
    }

    public Magasin getUnMagasin() {
        return unMagasin;
    }

    public void setUnMagasin(Magasin unMagasin) {
        this.unMagasin = unMagasin;
    }

    public Produit getUnProduit() {
        return unProduit;
    }

    public void setUnProduit(Produit unProduit) {
        this.unProduit = unProduit;
    }

    public int isTrouve() {
        return trouve;
    }

    public void setTrouve(int trouve) {
        this.trouve = trouve;
    }

    @Override
    public int describeContents() {
        return 3;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {

        dest.writeLong(_ID);
        dest.writeParcelable(uneCourse,1);
        dest.writeParcelable(unMagasin,2);
        dest.writeParcelable(unProduit,3);
        dest.writeInt(trouve);
    }


    //region CREATOR
    public Panier(Parcel in) {
        this._ID         = in.readLong() ;
        this.uneCourse   = in.readParcelable(Course.class.getClassLoader());
        this.unMagasin   = in.readParcelable(Magasin.class.getClassLoader());
        this.unProduit   = in.readParcelable(Produit.class.getClassLoader());


    }

    static Creator<Panier> CREATOR = new Creator<Panier>() {
        @Override
        public Panier createFromParcel(Parcel parcel) {
            return new Panier(parcel);
        }

        @Override
        public Panier[] newArray(int i) {
            return new Panier[i];
        }
    };
    //endregion


}
