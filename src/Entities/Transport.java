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
public class Transport {
     private int id;
    private String nom;
    private String type;
    private String matricule;
    private int prixLocation;

    public Transport() {
    }

    @Override
    public String toString() {
        return "Transport{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", matricule=" + matricule + ", prixLocation=" + prixLocation + '}';
    }

    public Transport(int id, String nom, String type, String matricule, int prixLocation) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.matricule = matricule;
        this.prixLocation = prixLocation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getPrixLocation() {
        return prixLocation;
    }

    public void setPrixLocation(int prixLocation) {
        this.prixLocation = prixLocation;
    }
    
}
