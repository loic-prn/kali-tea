/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    
    String[] joursDeLaSemaine = { "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche" };
    
    public Programmation(Menu o) {
        this.owner = o;
        this.setBackground(Color.white);
        this.init();
    }
    
    private void init(){
        
        //inits
        heure = new JLabel("Pour quelle heure:");
        listeHeures = new JComboBox();
        selection = new JButton("Selectionner un thé");
        selection.addActionListener(this);
        listeCheckBox = new JCheckBox[7];
        for (int i=0; i<7; i++){
            listeCheckBox[i] = new JCheckBox(joursDeLaSemaine[i]);
        }
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Infusion programmée");
        owner.retour.setEnabled(true);
        owner.title.setVisible(true);
        
        cont.fill = GridBagConstraints.BOTH;
                
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(heure, cont);
        cont.gridx = 0;
        cont.gridy = 1;
        this.add(listeHeures, cont);
        cont.insets = new Insets(20,0,20,0);
        cont.gridx = 0;
        cont.gridy = 3;
        this.add(selection, cont);
        cont.insets = new Insets(0,0,0,0);
        for (int i=0; i<7; i++){
            cont.gridx = 0;
            cont.gridy = 4+i;
            this.add(listeCheckBox[i], cont);
        }
          
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selection){
            JDialog dialog = new JDialog();
            dialog.add(new Catalogue(owner));
            dialog.pack();
            dialog.show();
        }
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
    
}
