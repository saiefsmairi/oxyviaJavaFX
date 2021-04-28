/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.entities;

import java.sql.Date;
import javafx.scene.image.ImageView;

/**
 *
 * @author smp
 */
public class Depense {
int id ;
String id_personnel_id ;
String email;
String picture ;
String occupation ;
String salaire ;
String horaire_reguliere ;
String horaire_sup ;
String exempte ;
String date_depense ;
String nom ;
String prenom ;
boolean enabled ;
String color ;

   /* public Depense(int id, String id_personnel_id, String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom, boolean enabled, String color) {
        this.id = id;
        this.id_personnel_id = id_personnel_id;
        this.email = email;
        this.picture = picture;
        this.occupation = occupation;
        this.salaire = salaire;
        this.horaire_reguliere = horaire_reguliere;
        this.horaire_sup = horaire_sup;
        this.exempte = exempte;
        this.date_depense = date_depense;
        this.nom = nom;
        this.prenom = prenom;
        this.enabled = enabled;
        this.color = color;
    }*/

    

   /* public Depense(String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom) {
        this.email = email;
        this.picture = picture;
        this.occupation = occupation;
        this.salaire = salaire;
        this.horaire_reguliere = horaire_reguliere;
        this.horaire_sup = horaire_sup;
        this.exempte = exempte;
        this.date_depense = date_depense;
        this.nom = nom;
        this.prenom = prenom;
    }*/

   /* public Depense(String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom) {
        this.picture = picture;
        this.occupation = occupation;
        this.salaire = salaire;
        this.horaire_reguliere = horaire_reguliere;
        this.horaire_sup = horaire_sup;
        this.exempte = exempte;
        this.date_depense = date_depense;
        this.nom = nom;
        this.prenom = prenom;
    }*/


   public Depense(int id, String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom, boolean enabled, String color) {
        this.id = id;
        this.email= email;
        this.picture = picture;
        this.occupation = occupation;
        this.salaire = salaire;
        this.horaire_reguliere = horaire_reguliere;
        this.horaire_sup = horaire_sup;
        this.exempte = exempte;
        this.date_depense = date_depense;
        this.nom = nom;
        this.prenom = prenom;
        this.enabled = enabled;
        this.color = color;
    }

//    public Depense(String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom) {
//        this.email=email;
//        this.picture = picture;
//        this.occupation = occupation;
//        this.salaire = salaire;
//        this.horaire_reguliere = horaire_reguliere;
//        this.horaire_sup = horaire_sup;
//        this.exempte = exempte;
//        this.date_depense = date_depense;
//        this.nom = nom;
//        this.prenom = prenom;
//        //this.enabled = enabled;
//        //this.color = color;
//    }

     
    

  
    public Depense() {
        //To change body of generated methods, choose Tools | Templates.
    }

    public Depense(int id, String id_personnel_id, String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom, boolean enabled, String color) {
        this.id = id;
        this.id_personnel_id = id_personnel_id;
        this.email = email;
        this.picture = picture;
        this.occupation = occupation;
        this.salaire = salaire;
        this.horaire_reguliere = horaire_reguliere;
        this.horaire_sup = horaire_sup;
        this.exempte = exempte;
        this.date_depense = date_depense;
        this.nom = nom;
        this.prenom = prenom;
        this.enabled = enabled;
        this.color = color;
    }

    public Depense(String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, String date_depense, String nom, String prenom) {
        this.email=email;
        this.picture = picture;
        this.occupation = occupation;
        this.salaire = salaire;
        this.horaire_reguliere = horaire_reguliere;
        this.horaire_sup = horaire_sup;
        this.exempte = exempte;
        this.date_depense = date_depense;
        this.nom = nom;
        this.prenom = prenom;
    }

   

   
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_personnel_id() {
        return id_personnel_id;
    }

    public void setId_personnel_id(String id_personnel_id) {
        this.id_personnel_id = id_personnel_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public String getHoraire_reguliere() {
        return horaire_reguliere;
    }

    public void setHoraire_reguliere(String horaire_reguliere) {
        this.horaire_reguliere = horaire_reguliere;
    }

    public String getHoraire_sup() {
        return horaire_sup;
    }

    public void setHoraire_sup(String horaire_sup) {
        this.horaire_sup = horaire_sup;
    }

    public String getExempte() {
        return exempte;
    }

    public void setExempte(String exempte) {
        this.exempte = exempte;
    }

    public String getDate_depense() {
        return date_depense;
    }

    public void setDate_depense(String date_depense) {
        this.date_depense = date_depense;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   
    

   
    

   





   

   
}
