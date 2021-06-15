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
import javax.swing.JFrame;
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
    
    
    public Accueil(Menu o){
        this.owner = o;   
        this.setBackground(Color.white);
        this.init();
    }
    
    private void init(){
        
        //inits
        catalogue = new JButton("Catalogue de thés");
        catalogue.setBackground(Color.blue);
        catalogue.setForeground(Color.white);
        catalogue.addActionListener(this);
        infuProgrammé = new JButton("Infusion programmée");
        infuProgrammé.setBackground(Color.blue);
        infuProgrammé.setForeground(Color.white);
        infuProgrammé.addActionListener(this);
        Param = new JButton("Paramètres");
        Param.setBackground(Color.blue);
        Param.setForeground(Color.white);
        Param.addActionListener(this);
        theManuel = new JButton(" Thé Manuel");
        theManuel.setBackground(Color.blue);
        theManuel.setForeground(Color.white);
        theManuel.addActionListener(this);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Accueil");
        owner.retour.setEnabled(false);
        
        cont.fill = GridBagConstraints.BOTH;
        cont.insets = new Insets(15,0,15,0);
        
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(catalogue, cont);
        
        cont.gridx = 0;
        cont.gridy = 1;
        this.add(infuProgrammé, cont);   
        
        cont.gridx = 0;
        cont.gridy = 2;
        this.add(Param, cont);

        cont.gridx = 0;
        cont.gridy = 2;
        this.add(Param, cont);    
        
        cont.gridx = 0;
        cont.gridy = 3;
        this.add(theManuel, cont);   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == catalogue){
            owner.setPano(new Catalogue(owner));
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
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
        
    
}
