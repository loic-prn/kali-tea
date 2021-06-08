/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.modele;

/**
 *
 * @author p2008444
 */
public class Categorie {
    String nom;
    String description;
    String origine;

    
    /**
     * Un thé
     * @param nom Le nom du thé
     * @param origine Origine géographique du thé.
     * @param description Description du thé
     */
    
    public Categorie(String nom, String description, String origine) {
        this.nom = nom;
        this.description = description;
        this.origine = origine;
    }
    
    
}
