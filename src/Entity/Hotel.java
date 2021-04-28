/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author USER
 */
public class Hotel {
    private int id;
    private String name;
    private String pays;
    private String adresse;
    private int nbEtoile;
    private int num;
    private String email;
    private String image;
    private long lat;
    private long lng;
    public static int id1;

    public Hotel() {
    }

    public Hotel(String name, String pays, String adresse, int nbEtoile, int num, String email, String image, long lat, long lng) {
        this.name = name;
        this.pays = pays;
        this.adresse = adresse;
        this.nbEtoile = nbEtoile;
        this.num = num;
        this.email = email;
        this.image = image;
        this.lat = lat;
        this.lng = lng;
    }

    public Hotel(int id, String name, String pays, String adresse, int nbEtoile, int num, String email, String image, long lat, long lng) {
        this.id = id;
        this.name = name;
        this.pays = pays;
        this.adresse = adresse;
        this.nbEtoile = nbEtoile;
        this.num = num;
        this.email = email;
        this.image = image;
        this.lat = lat;
        this.lng = lng;
    }

    public Hotel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNbEtoile() {
        return nbEtoile;
    }

    public void setNbEtoile(int nbEtoile) {
        this.nbEtoile = nbEtoile;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLng() {
        return lng;
    }

    public void setLng(long lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", name=" + name + ", pays=" + pays + ", adresse=" + adresse + ", nbEtoile=" + nbEtoile + ", num=" + num + ", email=" + email + ", image=" + image + ", lat=" + lat + ", lng=" + lng + '}';
    }
    
    
    
    
}
