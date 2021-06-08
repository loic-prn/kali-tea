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
public class ThéProgrammé {
    String pretPour;
    boolean recurrence;

    public ThéProgrammé(String pretPour, boolean recurrence) {
        this.pretPour = pretPour;
        this.recurrence = recurrence;
    }

    public String getPretPour() {
        return pretPour;
    }

    public boolean isRecurrence() {
        return recurrence;
    }

    public void setPretPour(String pretPour) {
        this.pretPour = pretPour;
    }

    public void setRecurrence(boolean recurrence) {
        this.recurrence = recurrence;
    }
    
    
    
}
