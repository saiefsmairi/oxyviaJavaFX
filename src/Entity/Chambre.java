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
public class Chambre {
    private int id;
    private int numero;
    private String type;
    private long prix; 
    private String image;
    private String occupe;
   // private Hotel idh; 
private int idh;


  public Chambre() {
    }

    public String getOccupe() {
        return occupe;
    }

    public void setOccupe(String occupe) {
        this.occupe = occupe;
    }


 public Chambre(int numero, String type, long prix, String image,int idh , String occupe) {
             this.id = id;

        this.numero = numero;
        this.type = type;
        this.prix = prix;
        this.image = image;
        this.idh = idh;
        this.occupe=occupe;
        
    }

 public Chambre(int id,int numero, String type, long prix, String image,int idh) {
                  this.id = id;

        this.numero = numero;
        this.type = type;
        this.prix = prix;
        this.image = image;
        this.idh = idh;
        
    }

    public Chambre(int id, int numero, long prix, Hotel idh, String image) {
          this.id = id;

        this.numero = numero;
        this.type = type;
        this.prix = prix;
        this.image = image;
        
    }

    public int getId() {
        return id;
    }

    public int getNumero() {
        return numero;
    }

    public String getType() {
        return type;
    }

    public long getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public int getIdh() {
        return idh;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(long prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

   
}