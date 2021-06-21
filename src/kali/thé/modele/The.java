/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.modele;

/**
 *
 * @author p2002527
 */
public class The {

    private double tempsInfusion;
    private String nom;
    private int temperature;
    private String description;
    private String categorie;

    
    /**
     * Un thé
     * @param tempsInfusion Le temps d'infusion du thé durant, soit le temps que restera le panier de thé dans l'eau
     * @param nom Le nom du thé
     * @param temperature la température d'infusion du thé, soit la température que la resistance atteindra pour l'infusion
     * @param description Description du thé
     * @param categorie La catégorie du thé selectionné
     */
    public The(double tempsInfusion, String nom, int temperature, String description,String categorie) {
        this.tempsInfusion = tempsInfusion;
        this.nom = nom;
        this.temperature = temperature;
        this.description = description;
        this.categorie = categorie;
    }

    /**
     * 
     * @return le temps d'infusion du thé
     */
    public double getTempsInfusion() {
        return tempsInfusion;
    }
    
    /**
     * 
     * @return le nom du thé
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * 
     * @return la température du thé
     */
    public int getTemperature() {
        return temperature;
    }

    /**
     * 
     * @return la description du thé
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @return la température du thé
     */
    public String getCategorie() {
        return categorie;
    }    
    
    
    
}
