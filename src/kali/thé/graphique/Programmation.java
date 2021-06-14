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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author p2008444
 */
public class Programmation extends JPanel implements ActionListener{
    
    Menu owner;
    
    JLabel heure;
    JComboBox listeHeures;
    JButton selection;
    JCheckBox[] listeCheckBox;
    JLabel[] joursSemaine;
    
    public Programmation(Menu o) {
        this.owner = o;
        this.init();
    }
    
    private void init(){
        
        //inits
        heure = new JLabel();
        
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Accueil");
        
        cont.fill = GridBagConstraints.BOTH;
        cont.insets = new Insets(20,0,20,0);
        
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(null, cont);
                  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selection){
            owner.setPano(new Catalogue(owner));
        }
        
    }
    
}
