/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author PC-Saif
 */
public class Chambre {

    private int id;
    private String type;
    private String occupe;
    private int prix;

    public Chambre() {
    }

    public Chambre(int id, String type, String occupe, int prix) {
        this.id = id;
        this.type = type;
        this.occupe = occupe;
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Chambre{" + "id=" + id + ", type=" + type + ", occupe=" + occupe + ", prix=" + prix + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOccupe() {
        return occupe;
    }

    public void setOccupe(String occupe) {
        this.occupe = occupe;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    
}
