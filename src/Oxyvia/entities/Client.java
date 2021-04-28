/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.entities;

import java.sql.Date;

/**
 *
 * @author smp
 */
public class Client {
    int id;
    String nom; 
    String prenom;
    Date daten;
    int num;
    String email;
    String adresse;
    String mdp;
    int cin;

    public Client(int id, String nom, String prenom, Date daten, int num, String email, String adresse, String mdp, int cin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.daten = daten;
        this.num = num;
        this.email = email;
        this.adresse = adresse;
        this.mdp = mdp;
        this.cin = cin;
    }

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDaten() {
        return daten;
    }

    public void setDaten(Date daten) {
        this.daten = daten;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }
    
    
}
