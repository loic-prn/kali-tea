/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author p2008444
 */
public class Accueil extends JPanel{
    
    JFrame owner;
    
    JButton catalogue;
    JButton infuProgrammé;
    JButton Param;
    
    
    public Accueil(JFrame o) {
        this.owner = o;      
        this.init();
    }
    
    private void init(){
        
        //inits
        catalogue = new JButton("Catalogue");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Accueil");
        
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(catalogue, cont);        
    }
    
    
    
}
