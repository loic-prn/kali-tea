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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import kali.thé.modele.The;

/**
 *
 * @author p2008444
 */
public class Accueil extends JPanel implements ActionListener{
    
    Menu owner;
    
    JButton catalogue;
    JButton infuProgrammé;
    JButton Param;
    JButton theManuel;
    JLabel logo;
    
    /**
     * The menu constructor
     * @param menu, the JFrame that own this JPanel.
     */
    public Accueil(Menu o){
        this.owner = o;   
        this.setBackground(Color.white);
        owner.title.setVisible(false);
        this.init();
    }
    /**
     * Just a function to initialize the menu. Organize the different items.
     */
    private void init(){
        Color vert = new Color(0x00b300);
        Font police1 = new Font("Arial",Font.BOLD,18);
        //inits
        logo = new JLabel();
        logo.setIcon(new ImageIcon(getClass().getResource("/icones/logoKali-Thé2.png")));
        
        catalogue = new JButton("Catalogue de thés");
        catalogue.setBackground(vert);        //inits

        catalogue.setForeground(Color.white);
        catalogue.addActionListener(this);
        catalogue.setFont(police1);
        
        infuProgrammé = new JButton("Infusion programmée");
        infuProgrammé.setBackground(vert);
        infuProgrammé.setForeground(Color.white);
        infuProgrammé.addActionListener(this);
        infuProgrammé.setFont(police1);
        
        Param = new JButton("Paramètres");
        Param.setBackground(vert);
        Param.setForeground(Color.white);
        Param.addActionListener(this);
        Param.setFont(police1);
        
        theManuel = new JButton(" Thé Manuel");
        theManuel.setFont(police1);
        theManuel.setBackground(vert);
        theManuel.setForeground(Color.white);
        theManuel.addActionListener(this);
        
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Accueil");
        owner.retour.setEnabled(false);
        
        cont.fill = GridBagConstraints.BOTH;
        cont.insets = new Insets(0,0,20,25);
        
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(logo, cont);
        
        cont.insets = new Insets(0,0,10,0);
        cont.gridx = 0;
        cont.gridy = 1;
        this.add(catalogue, cont);
        
        cont.gridx = 0;
        cont.gridy = 2;
        this.add(infuProgrammé, cont);   
        
        cont.gridx = 0;
        cont.gridy = 3;
        this.add(Param, cont);
        
        cont.gridx = 0;
        cont.gridy = 4;
        this.add(theManuel, cont);   
    }

    /**
     * this is the function where the differents action listened are managed.
     * @param e is the event that occured.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == catalogue){
            try {
                owner.setPano(new Catalogue(owner,false));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Accueil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else if (e.getSource() == infuProgrammé){
            owner.setPano(new Programmation(owner));
        }
        else if (e.getSource() == Param){
            owner.setPano(new Param(owner));
        }
        else if (e.getSource() == theManuel){
            owner.setPano(new Preparation(owner));
        }
        
    }
    
    /**
     * Where you get the size of our screen
     * @return Dimension the dimension of the rpi screen.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
        
    
}
