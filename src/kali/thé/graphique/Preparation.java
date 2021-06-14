/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author p2008444
 */
public class Preparation extends JPanel{
    
    JFrame owner;
    
    JLabel temperaturePrepa;
    // Slider
    
    JLabel temperature;
    JComboBox listeTemps;
    JButton preparer;
    
    //Slider
    JLabel tempsRestant;
    
    
    public Preparation(JFrame o) {
        this.owner = o;
    }
       
}
