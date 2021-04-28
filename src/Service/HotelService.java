/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Hotel;
import Interface.IService;
import Utulitaire.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class HotelService implements IService<Hotel> {

    private Connection con = Singleton.getConn();

    @Override
    public void Ajouter(Hotel e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("insert into hotel (name,pays,adresse,nbetoile,num,email,image,lat,lng) values (?,?,?,?,?,?,?,?,?)");

            preparedStmt.setString(1, e.getName());
            preparedStmt.setString(2, e.getPays());
            preparedStmt.setString(3, e.getAdresse());
            preparedStmt.setInt(4, e.getNbEtoile());
            preparedStmt.setInt(5, e.getNum());
            preparedStmt.setString(6, e.getEmail());
            preparedStmt.setString(7, e.getImage());

            preparedStmt.setLong(8, 1);
            preparedStmt.setLong(9, 1);

            preparedStmt.execute();
            System.out.println("Insertion Avec Succes");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void Supprimer(Hotel e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from hotel where id= ?");
            preparedStmt.setInt(1, e.getId());
            preparedStmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void Modifier(Hotel e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("update hotel set  name=? ,pays=?,adresse=? ,nbetoile=? ,num=? ,email=? ,image=?  where id=?");
            preparedStmt.setString(1, e.getName());
            preparedStmt.setString(2, e.getPays());
            preparedStmt.setString(3, e.getAdresse());
            preparedStmt.setInt(4, e.getNbEtoile());
            preparedStmt.setInt(5, e.getNum());
            preparedStmt.setString(6, e.getEmail());
            preparedStmt.setString(7, e.getImage());
            preparedStmt.setInt(8, e.getId());
            preparedStmt.execute();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Hotel> Lister() {
 ArrayList<Hotel> res = new ArrayList<Hotel>();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM hotel";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pays = rs.getString("pays");
                String adresse= rs.getString("adresse");
                int nbetoile=rs.getInt("nbetoile");
                int num=rs.getInt("num");
                String email=rs.getString("email");
                String image=rs.getString("image");
                long lat=rs.getLong("lat");
                long lng=rs.getLong("lng");
                
                
                Hotel H = new Hotel (id,name,pays,adresse,nbetoile,num,email,image,lng,lat);
                res.add(H);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }
    

     public ArrayList<String> ListerNom() {
 ArrayList<String> res = new ArrayList<String>();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM hotel";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                
                String name = rs.getString("name");
                
                
                res.add(name);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }
     
     public int getIdNom(String nom){
      Hotel h=new Hotel();
      int id =0;
       try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM hotel where name='"+nom+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
             
                  id = rs.getInt("id");
                  h=new Hotel(id);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
       return id;
  }
     public String getNom(int id){
      Hotel h=new Hotel();
      String nom ="";
       try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM hotel where id='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
             
                  nom = rs.getString("name");
                  h=new Hotel(id);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
       return nom;
  }
    public boolean estUnEntier(String chaine) {
		try {
			Integer.parseInt(chaine);
		} catch (NumberFormatException e){
			return false;
		}
 
		return true;
	}
     public  Hotel ListerH(int id1) throws SQLException {
      Hotel H =new Hotel();     
 try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM hotel where id="+id1;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String pays = rs.getString("pays");
                String adresse= rs.getString("adresse");
                int nbetoile=rs.getInt("nbetoile");
                int num=rs.getInt("num");
                String email=rs.getString("email");
                String image=rs.getString("image");
                long lat=rs.getLong("lat");
                long lng=rs.getLong("lng");
                
                
                 H = new Hotel (id,name,pays,adresse,nbetoile,num,email,image,lng,lat);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return H;  
    
}
  public void Ajouterrating( int idh,int idc,double rating) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("insert into rating (idhotel,idclient,rating) values (?,?,?)");

        
            preparedStmt.setInt(1,idh);
            preparedStmt.setInt(2, idc);
                       preparedStmt.setDouble(3, rating);


            preparedStmt.execute();
            System.out.println("raing Avec Succes");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
  public  double moyenneratingparhotel(int id1) throws SQLException {
      double i=0;
      int a =0;
 try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM rating where idhotel="+id1;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
               i=i+rs.getDouble("rating");
                       a++;
               ;
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
 if (a!=0)
        return i/a; 
 else return 0; 
    
}
  public  boolean ratedornot(int id1) throws SQLException {
      int i=0;
  boolean a;
 try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM rating where idclient="+id1;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
               i++
               ;
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
 if (i==0)
       return false;
 else return true;
    
}
  public void modifiererrating( int idh,double rating,int idc) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("update rating set  rating=?  where idhotel=? && idclient=?");

        
            preparedStmt.setDouble(1,rating);
            preparedStmt.setInt(2, idh);
                        preparedStmt.setInt(3, idc);



            preparedStmt.execute();
            System.out.println("raing Avec Succes");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
  
  



}
