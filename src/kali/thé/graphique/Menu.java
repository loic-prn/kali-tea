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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import kali.thé.modele.*;

/**
 *
 * @author lperr
 */
public class Menu extends JFrame implements ActionListener{
    private int longueur;
    private int largeur;
    ArrayList<The> list;
    ArrayList<TheProgramme> proglist;
    
    JPanel pano;
    
    //menu
    JPanel menu;
    JButton retour;
    JLabel heure;
    JLabel title;
    
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
       
        //timer pour heure
        javax.swing.Timer t = new javax.swing.Timer(1000, new ClockListener());
        t.start();
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPano(new Accueil(this));
    }
    
    class ClockListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            initPan(df.format(Calendar.getInstance().getTime()));
        }
    }
    
    public void setPano(JPanel j){
        pano = j;
        SimpleDateFormat s = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        initPan(s.format(date));
    }
    
    private void initMenu(String heure){
        menu = new JPanel();
        retour = new JButton("retour");
        retour.addActionListener(this);
        retour.setBackground(Color.white);
        this.heure = new JLabel(heure);
        title = new JLabel("Kali-Thé");
        title.setFont(new Font("Arial",Font.CENTER_BASELINE,35));
        
        menu.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        menu.setPreferredSize(new Dimension(720,50));
        menu.setBackground(Color.white);
        
        cont.insets = new Insets(0,0,0,500);
        cont.gridx = 0; cont.gridy = 0;
        menu.add(retour,cont);
        
        cont.insets = new Insets(0,0,0,0);
        cont.gridx = 10; cont.gridy = 0;
        menu.add(this.heure,cont);
        cont.gridheight = 2;
        cont.gridx = 0; cont.gridy = 0;
        menu.add(title,cont);
    }
    
    private void initPan(String heure){
        initMenu(heure);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retour){
            if (pano.getClass() == Accueil.class){}
            else if (pano.getClass() == Catalogue.class){
                this.setPano(new Accueil(this));
            }
            else if (pano.getClass() == Programmation.class){
                this.setPano(new Accueil(this));
            }
            else if (pano.getClass() == Param.class){
                this.setPano(new Accueil(this));
            }
            else if (pano.getClass() == Preparation.class){
                this.setPano(new Accueil(this));
            }
        }
    }
    
}
