
package Tables;

import java.time.LocalDate;

public class Evenement implements Comparable<Evenement>{
    private int ID;
    private String Nom;
    private LocalDate Date_De_Debut;
    private LocalDate Date_De_Fin;
    private double Prix;

    public Evenement(int ID, String Nom, LocalDate Date_De_Debut, LocalDate Date_De_Fin, double Prix) {
        this.ID = ID;
        this.Nom = Nom;
        this.Date_De_Debut = Date_De_Debut;
        this.Date_De_Fin = Date_De_Fin;
        this.Prix = Prix;
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
        return ID +getSpace(50)+ Nom +getSpace(50)+ Date_De_Debut +getSpace(50)+ Date_De_Fin +getSpace(80)+ Prix ;
    }

    @Override
    public int compareTo(Evenement o) {
        return ((int)(this.Prix-o.Prix));
    }
    

   
}
