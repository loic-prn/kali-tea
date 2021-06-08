/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author p2008444
 */
public class Page5 extends Menu{
    
    JLabel heure;
    JComboBox listeHeures;
    JButton selection;
    JCheckBox[] listeCheckBox;
    JLabel[] joursSemaine;
    
    public Page5(int longueur, int largeur) {
        super(longueur, largeur);
    }
    
}
