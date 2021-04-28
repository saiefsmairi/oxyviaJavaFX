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
public class Facture {
int id;
String identifiant ;
String nom_prenom;
String montant ;
Date date_paiement ;
String devise ;
String moyen_paiement ;
String mode_paiement ;
String location;
String pays ;
boolean enabled;
String color;


    public Facture(int id, String identifiant, String nom_prenom, String montant, Date date_paiement, String devise, String moyen_paiement, String mode_paiement, String location, String pays, boolean enabled, String color) {
        this.id = id;
        this.identifiant = identifiant;
        this.nom_prenom = nom_prenom;
        this.montant = montant;
        this.date_paiement = date_paiement;
        this.devise = devise;
        this.moyen_paiement = moyen_paiement;
        this.mode_paiement = mode_paiement;
        this.location = location;
        this.pays = pays;
        this.enabled = enabled;
        this.color = color;
    }

    public Facture(String identifiant, String nom_prenom, String montant, Date date_paiement, String devise, String moyen_paiement, String mode_paiement,String location, String pays, boolean enabled) {
        this.identifiant = identifiant;
        this.nom_prenom = nom_prenom;
        this.montant = montant;
        this.date_paiement = date_paiement;
        this.devise = devise;
        this.moyen_paiement = moyen_paiement;
        this.mode_paiement = mode_paiement;
       
        this.location = location;
        this.pays = pays;
        this.enabled = enabled;
    }

    public Facture(String identifiant, String nom_prenom, String montant, Date date_paiement, String devise, String moyen_paiement, String mode_paiement, String pays) {
        this.identifiant = identifiant;
        this.nom_prenom = nom_prenom;
        this.montant = montant;
        this.date_paiement = date_paiement;
        this.devise = devise;
        this.moyen_paiement = moyen_paiement;
        this.mode_paiement = mode_paiement;
        this.pays = pays;
    }

    

   

   

    

   
    public Facture() {
         //To change body of generated methods, choose Tools | Templates.
    }

    

    

   



   

    

    

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom_prenom() {
        return nom_prenom;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public Date getDate_paiement() {
        return date_paiement;
    }

    public void setDate_paiement(Date date_paiement) {
        this.date_paiement = date_paiement;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getMoyen_paiement() {
        return moyen_paiement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMoyen_paiement(String moyen_paiement) {
        this.moyen_paiement = moyen_paiement;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

   

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

   

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Facture{" + "id=" + id + ", identifiant=" + identifiant + ", montant=" + montant + ", date_paiement=" + date_paiement + ", devise=" + devise + ", moyen_paiement=" + moyen_paiement + ", mode_paiement=" + mode_paiement + ", location=" + location + ", pays=" + pays + ", enabled=" + enabled + ", color=" + color + '}';
    }

   



    
}
