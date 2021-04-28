/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;

/**
 *
 * @author USER
 */
public class Comment {
    private int id;
    private String content;
    private String created_at;
    private int idh;
    private int idc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

   

    public int getIdh() {
        return idh;
    }

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }

    public Comment(String content, String created_at, int idh, int idc) {
        this.content = content;
        this.created_at = created_at;
        this.idh = idh;
        this.idc = idc;
    }

    public Comment(int id, String content, String created_at, int idh, int idc) {
        this.id = id;
        this.content = content;
        this.created_at = created_at;
        this.idh = idh;
        this.idc = idc;
    }
    
    
    
}
