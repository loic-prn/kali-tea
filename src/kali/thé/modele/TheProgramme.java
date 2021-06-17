/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.modele;

import java.util.Date;

/**
 *
 * @author p2008444
 */
public class TheProgramme extends The{
    /**
     * @param pretPour une date qui indique la fin de préparation du thé
     * @param recurrence un tableau de  7 booléens correspondant aux 7 jours de la semaine. Le tableau indique quels jours sont concernés par l'infusion d'un thé programmé
     */
    Date pretPour;
    boolean[] recurrence;
    
    String[] joursDeLaSemaine = { "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche" };

    /**
     * 
     * @param pretPour, for when you want the tea.
     * @param recurrence Which days do you want it.
     */
    public TheProgramme(Date pretPour, boolean[] recurrence, The t) {
        super(t.getTempsInfusion(),t.getNom(),t.getTemperature(),t.getDescription());
        this.pretPour = pretPour;
        this.recurrence = new boolean[recurrence.length];
        for (int i=0; i<recurrence.length; i++)
            this.recurrence[i] = recurrence[i];
    }
    
    /**
     * obtenir le paramètre pretPour de la classe
     * @return le paramètre pretPour
     */
    public Date getPretPour() {
        return pretPour;
    }

    /**
     * La fonction vérifie si, pour un jour donné, un thé est programmé
     * @param i un entier corresponant à un jour de la semaine
     * @return <code>true</code> ou <code>false</code>
     */
    public boolean isRecurrence(int i) {
        return recurrence[i];
    }

    public void setPretPour(Date pretPour) {
        this.pretPour = pretPour;
    }

    public void setRecurrence(boolean[] recurrence) {
        recurrence = new boolean[recurrence.length];
        this.recurrence = recurrence;
    }

    @Override
    public String toString() {
        String ret = "";
        ret += "\tNom du thé: " + this.getNom()+ "\n";
        ret += "\tHoraire: " + Integer.toString(this.pretPour.getHours()) + "h" + Integer.toString(this.pretPour.getMinutes()) + "\n";
        ret += "\tRecurrence: ";
        for (int i=0;i<7; i++){
            if (recurrence[i])
                ret += joursDeLaSemaine[i] + " ";
        }
        ret += "\n";
        return ret;
    }
    
    
    
}
