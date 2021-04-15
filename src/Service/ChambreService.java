/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Chambre;
import Entity.Hotel;
import Interface.IService;
import Utulitaire.Singleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author USER
 */
public  class ChambreService implements IService<Chambre> {

    private Connection con = Singleton.getConn();

    @Override
    public void Ajouter(Chambre e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("insert into chambre (numero,type,prix,idhotel_id,image) values (?,?,?,?,?)");

            preparedStmt.setInt(1, e.getNumero());
            preparedStmt.setString(2, e.getType());
            preparedStmt.setLong(3, e.getPrix());
            preparedStmt.setInt(4, e.getIdh());        
            preparedStmt.setString(5, e.getImage());

           

            preparedStmt.execute();
            System.out.println("Insertion Avec Succes");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Supprimer(Chambre e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from chambre where id= ?");
            preparedStmt.setInt(1, e.getId());
            preparedStmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void Modifier(Chambre e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("update chambre set  numero=? ,type=?,prix=? ,idhotel_id=? ,image=?  where id=?");
            preparedStmt.setInt(1, e.getNumero());
            preparedStmt.setString(2, e.getType());
            preparedStmt.setLong(3, e.getPrix());
            preparedStmt.setInt(4, e.getIdh());            
            preparedStmt.setString(5, e.getImage());
            preparedStmt.setInt(6, e.getId());
            preparedStmt.execute();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    

    public ArrayList<Chambre> Lister() {
 ArrayList<Chambre> res = new ArrayList<Chambre>();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM chambre";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int numero = rs.getInt("numero");
                String type = rs.getString("type");
                long prix=rs.getLong("prix");
                int idh;
                idh = rs.getInt("idhotel_id");
                
                String image=rs.getString("image");
                
//                Hotel h=new Hotel (idh);
//                System.out.println(h.getId());
                Chambre C = new Chambre (id,numero,type,prix,image,idh);
                res.add(C);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }

    public ChambreService() {
    }
    
  
  
   
    
   
}