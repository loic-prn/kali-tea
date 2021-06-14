/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author p2008444
 */
public class Programmation extends JPanel{
    
    JFrame owner;
    
    JLabel heure;
    JComboBox listeHeures;
    JButton selection;
    JCheckBox[] listeCheckBox;
    JLabel[] joursSemaine;
    
    public Programmation(JFrame o) {
        this.owner = o;
    }
    
}
