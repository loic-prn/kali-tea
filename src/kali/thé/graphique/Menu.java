/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import kali.thé.modele.*;

/**
 *
 * @author lperr
 */
public class Menu extends JFrame{
    private int longueur;
    private int largeur;
    ArrayList<The> list;
    ArrayList<TheProgramme> proglist;
    
    JPanel pano;
    
    //menu
    JPanel menu;
    JButton retour;
    JLabel heure;    
    
    /**
     * 
     * @param longueur longueur de la fenetre
     * @param largeur  largeur de la fenetre
     */
    public Menu(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.list = new ArrayList<>();
        this.proglist = null;
       
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPano(new Accueil(this));
    }
    
    public void setPano(JPanel j){
        pano = j;
        initPan();
    }
    
    private void initMenu(){
        menu = new JPanel();
        retour = new JButton("retour");
        heure = new JLabel("15:40");
        
        menu.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        
        menu.setPreferredSize(new Dimension(720,50));
        
        cont.anchor = GridBagConstraints.WEST;
        cont.gridx = 0; cont.gridy = 0;
        menu.add(retour,cont);
        
        //cont.anchor = GridBagConstraints.EAST;
        cont.gridx = 1; cont.gridy = 0;
        menu.add(heure,cont);
    }
    
    private void initPan(){
        initMenu();
        JPanel panTemp = new JPanel();
        panTemp.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();        
        
        cont.gridx = 0; cont.gridy = 0;
        panTemp.add(menu,cont);
        cont.gridx = 0; cont.gridy = 1;
        panTemp.add(pano,cont);
        
        this.setContentPane(panTemp);
        this.pack();
    }
    

    /**
     * 
     * @param longueur change la longueur de la fenetre
     */
    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    /**
     * 
     * @param largeur change la largeur de la fenetre
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     * permet d'obtenir la longueur de la fenetre
     * @return la longueur de la fenetre
     */
    public int getLongueur() {
        return longueur;
    }

    /**
     * permet d'obtenir la largeur de la fenetre
     * @return la largeur de la fenetre
     */
    public int getLargeur() {
        return largeur;
    }

    /**
     * permet d'obtenir l'ArrayList de thés
     * @return la liste de thés
     */
    public ArrayList<The> getList() {
        return list;
    }
    
    /**
     * permet d'obtenir l'ArrayList des thés programmés
     * @return la liste des thés programmés
     */
    public ArrayList<TheProgramme> getProglist() {
        return proglist;
    }

//    /**
//     * obtenir la taille de préférence de la fenetre
//     * @return <code>Dimension</code> de la fenetre
//     */
//    @Override
//    public Dimension getPreferredSize() {
//        return new Dimension(longueur,largeur);
//    }
    
}
