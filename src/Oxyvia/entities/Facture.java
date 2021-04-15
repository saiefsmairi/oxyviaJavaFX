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
String montant ;
Date date_paiement ;
String devise ;
String moyen_paiement ;
String mode_paiement ;
String type_cb ;
String ncb;
String code_securite ;
Date date_expiration ;
String location;
String pays ;
boolean enabled;
String color;

    public Facture(int id, String identifiant, String montant, Date date_paiement, String devise, String moyen_paiement, String mode_paiement, String type_cb, String ncb, String code_securite, Date date_expiration, String location, String pays, boolean enabled, String color) {
        this.id = id;
        this.identifiant = identifiant;
        this.montant = montant;
        this.date_paiement = date_paiement;
        this.devise = devise;
        this.moyen_paiement = moyen_paiement;
        this.mode_paiement = mode_paiement;
        this.type_cb = type_cb;
        this.ncb = ncb;
        this.code_securite = code_securite;
        this.date_expiration = date_expiration;
        this.location = location;
        this.pays = pays;
        this.enabled = enabled;
        this.color = color;
    }

    

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
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

    public String getType_cb() {
        return type_cb;
    }

    public void setType_cb(String type_cb) {
        this.type_cb = type_cb;
    }

    public String getNcb() {
        return ncb;
    }

    public void setNcb(String ncb) {
        this.ncb = ncb;
    }

    public String getCode_securite() {
        return code_securite;
    }

    public void setCode_securite(String code_securite) {
        this.code_securite = code_securite;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
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
        return "Facture{" + "id=" + id + ", identifiant=" + identifiant + ", montant=" + montant + ", date_paiement=" + date_paiement + ", devise=" + devise + ", moyen_paiement=" + moyen_paiement + ", mode_paiement=" + mode_paiement + ", type_cb=" + type_cb + ", ncb=" + ncb + ", code_securite=" + code_securite + ", date_expiration=" + date_expiration + ", location=" + location + ", pays=" + pays + ", enabled=" + enabled + ", color=" + color + '}';
    }

   



    
}
