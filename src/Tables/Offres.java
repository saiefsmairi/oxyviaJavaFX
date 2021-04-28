
package Tables;

import java.time.LocalDate;

public class Offres implements Comparable<Offres>{
    private int ID;
    private String Nom;
    private LocalDate Date_De_Debut;
    private LocalDate Date_De_Fin;
    private String Sujet;
    private double Prix;

    public Offres(int ID, String Nom, LocalDate Date_De_Debut, LocalDate Date_De_Fin,String Sujet, double Prix) {
        this.ID = ID;
        this.Nom = Nom;
        this.Date_De_Debut = Date_De_Debut;
        this.Date_De_Fin = Date_De_Fin;
        this.Sujet=Sujet;
        this.Prix = Prix;
    }

    public String getSujet() {
        return Sujet;
    }

    public void setSujet(String Sujet) {
        this.Sujet = Sujet;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public LocalDate getDate_De_Debut() {
        return Date_De_Debut;
    }

    public void setDate_De_Debut(LocalDate Date_De_Debut) {
        this.Date_De_Debut = Date_De_Debut;
    }

    public LocalDate getDate_De_Fin() {
        return Date_De_Fin;
    }

    public void setDate_De_Fin(LocalDate Date_De_Fin) {
        this.Date_De_Fin = Date_De_Fin;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }
    public String getSpace(int count)
    {
      String space="";
      for(int i=0;i<count;i++)
            space+=" ";
       return space;
    }

    @Override
    public String toString() {
        
        if(this.Sujet.length()<45){
            return ID+getSpace(15)+Nom+getSpace(32)+Date_De_Debut+getSpace(43)+Date_De_Fin+getSpace(33)+String.format("%50s", Sujet)+getSpace(21)+Prix;
        }
        else{
            return ID+getSpace(15)+Nom+getSpace(32)+Date_De_Debut+getSpace(43)+Date_De_Fin+getSpace(33)+String.format("%50s", Sujet.substring(0, 45))+getSpace(21)+Prix+"\n"+getSpace(175)+Sujet.substring(45);
        }
        
    }

    @Override
    public int compareTo(Offres o) {
        return((int)(this.Prix-o.Prix));
    }
    

   
}
