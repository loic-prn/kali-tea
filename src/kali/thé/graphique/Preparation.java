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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;

/**
 *
 * @author p2008444
 */
public class Preparation extends JPanel implements ActionListener,ChangeListener{
    
    Menu owner;
    JLabel temperaturePrepa;
    JSlider slider1;
    JLabel temperature;
    JLabel tempsPrep;
    JComboBox listeTemps;
    JButton preparer;
    JProgressBar progressPrep;
    JLabel tempsRestant;
    
    
    
    
    public Preparation(Menu o) {
        this.owner = o;
        this.init();
    }
    
    private void init(){
        owner.setTitle("Préparation");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        
        temperaturePrepa = new JLabel("Temperature de prepartion");
        temperature = new JLabel("°C");
        
        slider1 = new JSlider();
        slider1.setPreferredSize(new Dimension(300,20));
        slider1.setMaximum(120);
        slider1.setMinimum(30);
        slider1.addChangeListener(e-> temperature.setText(String.valueOf(slider1.getValue()) + "°C"));
        
        tempsPrep = new JLabel("Temps de préparation");
        listeTemps = new JComboBox();
        listeTemps.setPreferredSize(new Dimension(150,30));
        preparer = new JButton("Préparer");
        preparer.setBackground(Color.blue);
        preparer.setForeground(Color.white);
        preparer.addActionListener(this);
        progressPrep = new JProgressBar();
        progressPrep.setPreferredSize(new Dimension(200,20));
        progressPrep.setForeground(Color.red);
        progressPrep.setValue(20);
        tempsRestant = new JLabel("Temps restant : 1 min");
        


        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridx = 0;
        cont.gridy = 0;
        cont.insets = new Insets(5,0,5,0);
        
        
        this.add(temperaturePrepa,cont);
        
        
        cont.gridy = 1;
        this.add(slider1,cont);
        
        
        cont.gridy = 2;
        cont.insets = new Insets(0,0,40,0);
        this.add(temperature,cont);
        
        
        
        cont.fill = GridBagConstraints.CENTER;
        cont.insets = new Insets(0,0,0,0);
        cont.gridy = 3;
        this.add(tempsPrep,cont);
        
        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridwidth = 100;
        cont.insets = new Insets(0,0,40,0);
        cont.gridy = 4;
        this.add(listeTemps,cont);
        
        
        
        cont.fill = GridBagConstraints.BOTH;
        cont.gridy = 5;
        cont.insets = new Insets(0,0,35,0);
        cont.fill = GridBagConstraints.CENTER;
        cont.gridwidth = 90;
        this.add(preparer,cont);
        
        
        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridwidth = 0;
        cont.insets = new Insets(0,0,5,0);
        cont.gridy = 6;
        this.add(progressPrep,cont);
        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridy = 7;
        this.add(tempsRestant,cont);
            
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }

    @Override
    public void changed(ObservableValue ov, Object t, Object t1) {
        
    }
}
