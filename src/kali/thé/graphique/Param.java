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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author p2008444
 */
public class Param extends JPanel implements ActionListener{
    
    Menu owner;
    
    JButton timeSwap;
    JButton majThe;
    JButton majLog;
    JButton wifi;
    JButton language;
    
    
    /**
     * The settings page constructor
     * @param o the parent menu.
     */
    public Param(Menu o) {
        this.owner = o;
        this.setBackground(Color.white);
        this.init();
    }
    
    /**
     * This function just make the constructor reading simpler.
     */
    private void init(){
        Font police1 = new Font("Arial",Font.BOLD,15);
        owner.setTitle("Paramètres");
        owner.retour.setEnabled(true);
        owner.title.setVisible(true);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        
        timeSwap = new JButton("      Changer l'heure");
        timeSwap.addActionListener(this);
        timeSwap.setFont(police1);
        
        majThe = new JButton("      Mettre à jour les thés");
        majThe.addActionListener(this);
        majThe.setFont(police1);
        
        majLog = new JButton("      Mettre à jour le logiciel");
        majLog.addActionListener(this);
        majLog.setFont(police1);
        
        wifi = new JButton("      Connexion WiFi");
        wifi.addActionListener(this);
        wifi.setFont(police1);
        
        language = new JButton("      Langue du système");
        language.addActionListener(this);
        language.setFont(police1);
        
        ImageIcon horloge = new ImageIcon(getClass().getResource("/icones/clock2.png"));
        ImageIcon languageIcon = new ImageIcon(getClass().getResource("/icones/language.png"));
        ImageIcon updateThea = new ImageIcon(getClass().getResource("/icones/update.png"));
        ImageIcon updateSett = new ImageIcon(getClass().getResource("/icones/updateSettings.png"));
        ImageIcon wifiIcon = new ImageIcon(getClass().getResource("/icones/wifi.png"));
        timeSwap.setIcon(horloge);
        timeSwap.setBackground(Color.white);
        timeSwap.setBorder(BorderFactory.createLineBorder(Color.white));
        majThe.setIcon(updateThea);
        majThe.setBackground(Color.white);
        majThe.setBorder(BorderFactory.createLineBorder(Color.white));
        majLog.setIcon(updateSett);
        majLog.setBackground(Color.white);
        majLog.setBorder(BorderFactory.createLineBorder(Color.white));
        wifi.setIcon(wifiIcon);
        wifi.setBackground(Color.white);
        wifi.setBorder(BorderFactory.createLineBorder(Color.white));
        language.setIcon(languageIcon);
        language.setBackground(Color.white);
        language.setBorder(BorderFactory.createLineBorder(Color.white));
        
        cont.fill = GridBagConstraints.CENTER;
        cont.anchor = GridBagConstraints.WEST;
        cont.insets = new Insets(20,0,20,0);
        cont.gridx = 1;
        cont.gridy = 0;
        
        
        this.add(timeSwap, cont);
        cont.gridy = 1;
        
        this.add(majThe, cont);
        
        cont.gridy = 2;
        this.add(majLog, cont);
        
        cont.gridy = 3;
        this.add(wifi, cont);
        
        cont.gridy = 4;
        this.add(language,cont);
        
         
    }
    
    /**
     * 
     * @return Dimension the current window dimension. 
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timeSwap){
            
        }
        else if(e.getSource()==majThe){
            
        }
        else if(e.getSource() == majLog){
            
        }
        
        else if(e.getSource() == language){
            
        }
        else if(e.getSource() == wifi){
            
        }
    }
}
