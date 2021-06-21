/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.PopupFactory;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import kali.thé.modele.The;
import kali.thé.modele.TheProgramme;

/**
 *
 * @author p2008444
 */
public class Programmation extends JPanel implements ActionListener{
    
    Menu owner;
    
    JLabel heure;
    JLabel horaire;
    
    JButton selection;
    JCheckBox[] listeCheckBox;
    JLabel[] joursSemaine;
    JLabel theChoisit;
    JComboBox heures;
    JComboBox minutes;
    JButton valider;
    
    Integer[] h;
    Integer[] m;
    String[] joursDeLaSemaine = { "Lundi","Mardi","Mercredi","Jeudi","Vendredi","Samedi","Dimanche" };
    
    int actualHour = 0;
    int actualMin = 0;
    boolean[] joursDeLaSemaineVerif = {false,false,false,false,false,false,false};
    String chaineAffiche;
    Color vert = new Color(0x00b300);
    
    TheProgramme tp;
    The t;
    
    /**
     * Progrmmation constructor
     * @param o the parent Menu, which is a JFrame
     */
    public Programmation(Menu o) {
        this.owner = o;
        this.setBackground(Color.white);
        
        theChoisit = new JLabel("");
        this.init();
    }
    /**
     * This function is just to make the constructor easier to read.
     */
    private void init(){
        this.removeAll();
        
        Font police1 = new Font("Arial",Font.BOLD,18);
        Font police2 = new Font("Arial",Font.PLAIN,18);
        owner.setPano(this);
        //inits
        m = new Integer[61];
        h = new Integer[25];
        heure = new JLabel("Pour quelle heure:");
        heure.setFont(police1);
        
        horaire = new JLabel("Programmé pour: ");
        horaire.setFont(police1);
        
        selection = new JButton("Selectionner un thé");
        selection.setBackground(vert);
        selection.setForeground(Color.white);
        selection.addActionListener(this);
        selection.setFont(police1);
        
        listeCheckBox = new JCheckBox[7];
        valider = new JButton("Valider");
        valider.addActionListener(this);
        valider.setBackground(Color.white);
        
        for(int i = 0; i < 60; i ++){
            m[i] = i;
        }
        
        for(int i = 0; i < 24; i++){
            h[i] = i;
        }
        
        heures = new JComboBox(h);
        heures.addActionListener(this);
        minutes = new JComboBox(m);
        minutes.addActionListener(this);
        
        for (int i=0; i<7; i++){
            listeCheckBox[i] = new JCheckBox(joursDeLaSemaine[i]);
            listeCheckBox[i].setBackground(Color.white);
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
        cont.insets = new Insets(10,0,10,0);
        cont.gridx = 0;
        cont.gridy = 2;
        this.add(horaire,cont);
        
        
        cont.insets = new Insets(10,0,5,0);
        cont.fill = GridBagConstraints.CENTER;
        cont.gridwidth = 2;
        cont.gridx = 0;
        cont.gridy = 3;
        this.add(selection, cont);
        
        cont.fill = GridBagConstraints.NONE;
        cont.gridx = 0;
        cont.gridy = 4;
        this.add(theChoisit, cont);
        
        
        
        cont.insets = new Insets(0,0,0,0);
        int i;
        for (i=0; i<7; i++){
            cont.gridx = 0;
            cont.gridy = 6+i;
            listeCheckBox[i].setFont(police2);
            cont.anchor = GridBagConstraints.WEST;
            cont.fill = GridBagConstraints.CENTER;
            this.add(listeCheckBox[i], cont);
            listeCheckBox[i].addActionListener(this);
            
        }
        
        cont.anchor = GridBagConstraints.CENTER;
        cont.insets = new Insets(15,0,0,0);
        cont.gridx = 0;
        cont.gridy = i+7;
        valider.setBackground(vert);
        valider.setForeground(Color.white);;
        valider.setFont(police1);
        this.add(valider,cont);
        
        
        chaineUpdate();
    }
    /**
     * Update the hour.
     */
    public void chaineUpdate(){
        chaineAffiche = Integer.toString(actualHour) + "h" + Integer.toString(actualMin) + " ";
        heures.setSelectedItem(actualHour);
        minutes.setSelectedItem(actualMin);
        for(int i = 0; i < 7; i++){
            if(joursDeLaSemaineVerif[i]){
                //chaineAffiche = chaineAffiche + joursDeLaSemaine[i] + " ";
                listeCheckBox[i].setSelected(true);
            }
        }
        horaire.setText(chaineAffiche);
    }

    /**
     * handle the event that may occure.
     * @param e the event that occured.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        The temp = null;
        if (e.getSource() == selection){
            PopupCatalogue fen;
            try {
                fen = new PopupCatalogue(new Catalogue(owner,true), this.owner);
                temp = fen.showDialog();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Programmation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Programmation.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Programmation.class.getName()).log(Level.SEVERE, null, ex);
            }
            //temp = fen.showDialog();
            if (temp != null){
                theChoisit.setText("Thé choisi : " + temp.getNom());
                t = temp;
                this.init();
            }
        }
        else if (e.getSource() == heures){
            actualHour = (Integer)heures.getSelectedItem();
            chaineUpdate();
        }
        
        else if (e.getSource() == minutes){
            actualMin = (Integer)minutes.getSelectedItem();
            chaineUpdate();
        }
        
        else if (e.getSource() == valider){
            tp = new TheProgramme(new Date(0, 0, 0, actualHour, actualMin, 0),joursDeLaSemaineVerif,t);
            owner.proglist.add(tp);
            System.out.println(tp);
            owner.setPano(new Accueil(owner));
        }
        
        for(int i =0; i < 7; i++){
            if(e.getSource() == listeCheckBox[i]){
                joursDeLaSemaineVerif[i] = !joursDeLaSemaineVerif[i];
                chaineUpdate();
            }
        }
        
        
    }
    /**
     * 
     * @return Dimension, the window dimension.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
    
}
