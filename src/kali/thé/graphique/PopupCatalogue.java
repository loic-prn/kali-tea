/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import kali.thé.modele.The;

/**
 *
 * @author lucas
 */
public class PopupCatalogue extends JDialog implements ActionListener{

    Catalogue j;
    The ret;
    
    /**
     * The pop up from Catalogue constructor
     * @param j the catalogue
     * @param owner the JFrame that own the pop-up
     */
    public PopupCatalogue(Catalogue j, JFrame owner){
        
        super(owner,true);
        this.j = j;
        this.add(j);
        this.pack();
        for (int i=0; i<j.tabTheButton.length; i++){
            j.tabTheButton[i].addActionListener(this);
        }
    }
    
    /**
     * Set visible the dialogue pop-up
     * @return The the the you selected.
     */
    public The showDialog(){
        this.setVisible(true);
        return ret;
    }
    /**
     * Handle the action that may occures.
     * @param e the action performed. 
     */
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0; i<j.tabTheButton.length; i++){
            if (e.getSource() == j.tabTheButton[i]){
                ret = new The(j.tabThe.get(i).getTempsInfusion(),Integer.toString(i),j.tabThe.get(i).getTemperature(),j.tabThe.get(i).getDescription(),j.tabThe.get(i).getCategorie());
                this.setVisible(false);
            }
        }
    }
}
