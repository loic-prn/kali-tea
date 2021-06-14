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
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author p2008444
 */
public class Accueil extends JPanel implements ActionListener{
    
    Menu owner;
    
    JButton catalogue;
    JButton infuProgrammé;
    JButton Param;
    
    
    public Accueil(Menu o){
        this.owner = o;      
        this.init();
    }
    
    private void init(){
        
        //inits
        catalogue = new JButton("Catalogue de thés");
        catalogue.setBackground(Color.blue);
        catalogue.setForeground(Color.white);
        catalogue.addActionListener(this);
        infuProgrammé = new JButton("Infusion programmées");
        infuProgrammé.setBackground(Color.blue);
        infuProgrammé.setForeground(Color.white);
        infuProgrammé.addActionListener(this);
        Param = new JButton("Paramètres");
        Param.setBackground(Color.blue);
        Param.setForeground(Color.white);
        Param.addActionListener(this);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Accueil");
        
        cont.fill = GridBagConstraints.BOTH;
        cont.insets = new Insets(20,0,20,0);
        
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(catalogue, cont);
        
        cont.gridx = 0;
        cont.gridy = 1;
        this.add(infuProgrammé, cont);   
        
        cont.gridx = 0;
        cont.gridy = 2;
        this.add(Param, cont);           
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == catalogue){
            System.out.println("works");
            owner.setPano(new Infusion(owner));
        }
    }
        
    
}
