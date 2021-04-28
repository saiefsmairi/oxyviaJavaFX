/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public interface IService <T> {
    public void Ajouter(T e);
    public void Supprimer(T e);
    public void Modifier(T e);
    public ArrayList<T> Lister();
    
}
