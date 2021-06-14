/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import kali.thé.modele.The;

/**
 *
 * @author p2008444
 */
public class Infusion extends JPanel implements ActionListener{
    
    JFrame owner;
    The t;
    
    JLabel titreThe;
    JLabel description;
    JButton infuser;
    //Slider
    JLabel tempsRestant;
    JProgressBar progressTime;
    
    
    public Infusion(JFrame o, The t) {
        this.owner = o;
        this.t = t;
        init();
    }
    private void init(){
        
        //inits
        titreThe = new JLabel(t.getNom());
        description = new JLabel(t.getDescription());
        description.setPreferredSize(new Dimension(200,100));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(1, 1, 210, 259);
        description.add(scrollPane);
        infuser = new JButton("Infuser");
        progressTime = new JProgressBar();
        tempsRestant = new JLabel("Temps restant: " + Double.toString(t.getTempsInfusion()) + " mins");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Infusion");
        
        
        cont.insets = new Insets(10,0,10,0);
        
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(titreThe, cont);
        cont.gridx = 0;
        cont.gridy = 1;
        this.add(description, cont);
        cont.gridx = 0;
        cont.gridy = 2;
        this.add(infuser, cont);
        cont.gridx = 0;
        cont.gridy = 3;
        cont.fill = GridBagConstraints.BOTH;
        this.add(progressTime, cont);
        cont.gridx = 0;
        cont.gridy = 4;
        cont.fill = GridBagConstraints.NONE;
        this.add(tempsRestant, cont);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
                
    }
    
}
