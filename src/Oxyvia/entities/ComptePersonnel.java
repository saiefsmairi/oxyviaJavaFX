/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oxyvia.entities;

/**
 *
 * @author smp
 */
public class ComptePersonnel {
 int id;
 String id_personnel;
 String email;
 String nom;
 String prenom;
String tel;
String occupation;
String salaire_annuel;

    public ComptePersonnel(int id, String id_personnel, String nom, String prenom, String tel, String occupation, String salaire_annuel) {
        this.id = id;
        this.id_personnel = id_personnel;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.occupation = occupation;
        this.salaire_annuel = salaire_annuel;
    }

    public ComptePersonnel(String email, String nom, String prenom, String tel, String occupation, String salaire_annuel) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.occupation = occupation;
        this.salaire_annuel = salaire_annuel;
    }

    public ComptePersonnel(int id, String nom, String prenom, String tel, String occupation, String salaire_annuel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.occupation = occupation;
        this.salaire_annuel = salaire_annuel;
    }

    public ComptePersonnel(String nom, String prenom, String tel, String occupation, String salaire_annuel) {
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.occupation = occupation;
        this.salaire_annuel = salaire_annuel;
    }

    













    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


  

    

   

    

    public ComptePersonnel() {
     
    }

    public ComptePersonnel(int id) {
        this.id=id;
    }

     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

     public String getId_personnel() {
        return id_personnel;
    }

    public void setId_personnel(String id_personnel) {
        this.id_personnel = id_personnel;
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

  public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

  public String getSalaire_annuel() {
        return salaire_annuel;
    }

    public void setSalaire_annuel(String salaire_annuel) {
        this.salaire_annuel = salaire_annuel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

  

    
}
