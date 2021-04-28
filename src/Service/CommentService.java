/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Comment;
import Utulitaire.Singleton;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class CommentService {
     private Connection con = Singleton.getConn();
     
    public void Ajouter(Comment e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("insert into comment (idhotel_id,idclient_id,content,created_at) values (?,?,?,?)");

            preparedStmt.setInt(1, e.getIdh());
            preparedStmt.setInt(2, e.getIdc());
            preparedStmt.setString(3, e.getContent());
            preparedStmt.setString(4,  e.getCreated_at());        
            

           

            preparedStmt.execute();
            System.out.println("Insertion Avec Succes");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 public void Supprimer(Comment e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement(" delete from Comment where id= ?");
            preparedStmt.setInt(1, e.getId());
            preparedStmt.executeUpdate();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
  public void Modifier(Comment e) {
        try {
            PreparedStatement preparedStmt = con.prepareStatement("update Comment set  idhotel_id=? ,idclient_id=?,content=? ,created_at=?  where id=?");
            preparedStmt.setInt(1, e.getIdh());
            preparedStmt.setInt(2, e.getIdc());
            preparedStmt.setString(3, e.getContent());
            preparedStmt.setString(4, e.getCreated_at());
            preparedStmt.setInt(5, e.getId());
            preparedStmt.execute();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        
}
 public ArrayList<Comment> Lister(int id1) {
 ArrayList<Comment> res = new ArrayList<Comment>();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Comment where idhotel_id="+id1;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int idh=rs.getInt("idhotel_id");
                int idc=rs.getInt("idclient_id");
                String content= rs.getString("content");
                String created_at=rs.getString("created_at");
             
                
                
                
                Comment C = new Comment (id,content,created_at,idh,idc);
                res.add(C);
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return res;    
    }
    
public String Listernomutilisateur(int id1) {
   String content="";
       try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM client where id="+id1;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
               
                 content=rs.getString("nom");
                
                
                
            }
            rs.close();
            } 
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return content;    
    }

}
