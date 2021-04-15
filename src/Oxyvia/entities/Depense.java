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
int id;
String id_personnel_id ;
String nom_prenom ;
String email;
String picture;
String occupation ;
String salaire ;
String horaire_reguliere ;
String horaire_sup ;
String exempte ;
Date date_depense ;
String enabled;

    public Depense(int id, String id_personnel_id, String nom_prenom, String email, String picture, String occupation, String salaire, String horaire_reguliere, String horaire_sup, String exempte, Date date_depense, String enabled) {
        this.id = id;
        this.id_personnel_id = id_personnel_id;
        this.nom_prenom = nom_prenom;
        this.email = email;
        this.picture = picture;
        this.occupation = occupation;
        this.salaire = salaire;
        this.horaire_reguliere = horaire_reguliere;
        this.horaire_sup = horaire_sup;
        this.exempte = exempte;
        this.date_depense = date_depense;
        this.enabled = enabled;
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

    public String getNom_prenom() {
        return nom_prenom;
    }

    public void setNom_prenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
    
    private ImageView photo= new ImageView();

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
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

    public Date getDate_depense() {
        return date_depense;
    }

    public void setDate_depense(Date date_depense) {
        this.date_depense = date_depense;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Depense{" + "id_personnel_id=" + id_personnel_id + ", nom_prenom=" + nom_prenom + ", email=" + email + ", picture=" + picture + ", occupation=" + occupation + ", salaire=" + salaire + ", horaire_reguliere=" + horaire_reguliere + ", horaire_sup=" + horaire_sup + ", exempte=" + exempte + ", date_depense=" + date_depense + ", enabled=" + enabled + '}';
    }





   

   
}
