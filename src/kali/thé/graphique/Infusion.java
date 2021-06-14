/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author p2008444
 */
public class Infusion extends JPanel{
    
    JFrame owner;
    
    JLabel titreThe;
    JLabel description;
    JButton infuser;
    //Slider
    JLabel tempsRestant;
    JProgressBar progressTime;
    
    
    public Infusion(JFrame o) {
        this.owner = o;
    }
    
    
}
