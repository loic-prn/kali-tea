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
import javax.swing.PopupFactory;
import kali.thé.modele.The;

/**
 *
 * @author p2008444
 */
public class Programmation extends JPanel implements ActionListener{
    
    Menu owner;
    
    JLabel heure;
    JLabel heureChoisie;
    JButton selection;
    JCheckBox[] listeCheckBox;
    JLabel[] joursSemaine;
    JLabel theChoisit;
    JComboBox heures;
    JComboBox minutes;
    
    Integer[] h;
    Integer[] m;
    String[] joursDeLaSemaine = { "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche" };
    
    
    public Programmation(Menu o) {
        this.owner = o;
        this.setBackground(Color.white);
        
        theChoisit = new JLabel("");
        this.init();
    }
    
    private void init(){
        this.removeAll();
        //inits
        m = new Integer[61];
        h = new Integer[25];
        heure = new JLabel("Pour quelle heure:");
        heureChoisie = new JLabel("Programmé pour: ");
        heures = new JComboBox(h);
        minutes = new JComboBox(m);
        selection = new JButton("Selectionner un thé");
        selection.addActionListener(this);
        listeCheckBox = new JCheckBox[7];
        
        for(int i = 0; i < 61; i ++){
            m[i] = i;
        }
        
        for(int i = 0; i < 25; i++){
            h[i] = i;
        }
        
        for (int i=0; i<7; i++){
            listeCheckBox[i] = new JCheckBox(joursDeLaSemaine[i]);
        }
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Infusion programmée");
        owner.retour.setEnabled(true);
        owner.title.setVisible(true);
        
        cont.fill = GridBagConstraints.BOTH;
        cont.gridwidth = 2;
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(heure, cont);
        
        cont.ipadx = 60;
        cont.gridwidth = 1;
        cont.gridx = 0;
        cont.gridy = 1;
        this.add(heures, cont);
        
        cont.gridwidth = 1;
        cont.gridx = 1;
        cont.gridy = 1;
        this.add(minutes, cont);
        
        
        cont.fill = GridBagConstraints.CENTER;
        cont.weightx = 0;
        cont.ipadx = 0;
        cont.gridwidth = 2;
        cont.insets = new Insets(20,0,20,0);
        cont.gridx = 0;
        cont.gridy = 2;
        this.add(heureChoisie,cont);
        
        cont.fill = GridBagConstraints.BOTH;
        cont.gridwidth = 2;
        cont.gridx = 0;
        cont.gridy = 3;
        this.add(selection, cont);
        
        cont.gridx = 0;
        cont.gridy = 4;
        this.add(theChoisit, cont);
        cont.insets = new Insets(0,0,0,0);
        for (int i=0; i<7; i++){
            cont.gridx = 0;
            cont.gridy = 6+i;
            this.add(listeCheckBox[i], cont);
        }
          
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        The temp = null;
        if (e.getSource() == selection){
            PopupCatalogue fen = new PopupCatalogue(new Catalogue(owner,true), this.owner);
            temp = fen.showDialog();
            if (temp != null){
                theChoisit.setText(temp.getNom());
                this.init();
            }
        }
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
    
}
