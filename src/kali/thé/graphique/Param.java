/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author p2008444
 */
public class Param extends JPanel{
    
    Menu owner;
    
    JButton timeSwap;
    JButton majThe;
    JButton majLog;
    JButton wifi;
    JButton language;
    
    
    
    public Param(Menu o) {
        this.owner = o;
        this.init();
    }
    
    private void init(){
        owner.setTitle("Paramètres");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        
        timeSwap = new JButton("Changer l'heure");
        majThe = new JButton("Mettre à jour les thés");
        majLog = new JButton("Mettre à jour le logiciel");
        wifi = new JButton("Connxeion WiFi");
        language = new JButton("Langue du système");
        
        
        cont.fill = GridBagConstraints.BOTH;
        cont.gridx = 1;
        cont.gridy = 0;
        
        
        this.add(timeSwap, cont);
        cont.gridy = 1;
        
        this.add(majThe, cont);
        
        cont.gridy = 2;
        this.add(majLog, cont);
        
        cont.gridy = 3;
        this.add(wifi, cont);
        
        cont.gridy = 4;
        this.add(language,cont);
        
         
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
}
