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

    
    /**
     * Un thé
     * @param tempsInfusion Le temps d'infusion du thé durant, soit le temps que restera le panier de thé dans l'eau
     * @param nom Le nom du thé
     * @param temperature la température d'infusion du thé, soit la température que la resistance atteindra pour l'infusion
     * @param description Description du thé
     */
    
    public The(double tempsInfusion, String nom, int temperature, String description) {
        this.tempsInfusion = tempsInfusion;
        this.nom = nom;
        this.temperature = temperature;
        this.description = description;
    }

    public double getTempsInfusion() {
        return tempsInfusion;
    }
    public String getNom() {
        return nom;
    }

    public int getTemperature() {
        return temperature;
    }


    public String getDescription() {
        return description;
    }
    
    
    
}
