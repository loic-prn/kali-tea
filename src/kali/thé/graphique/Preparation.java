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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import kali.thé.modele.The;

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
    JSpinner secondes;
    JSpinner minutes;
    JButton preparer;
    JProgressBar progressPrep;
    JLabel tempsRestant;
    JLabel printHour;
    
    
    private String chaineSec ="";
    private String chaineMin="";
    
    private double tRestant;
    
    
    public Preparation(Menu o) {
        this.owner = o;
        this.setBackground(Color.white);
        this.init();
    }
    
    private void init(){
        owner.setTitle("Préparation");
        owner.retour.setEnabled(true);
        owner.title.setVisible(true);
        
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
        secondes = new JSpinner(new SpinnerNumberModel(0,0,60,1));
        secondes.addChangeListener(this);
        minutes = new JSpinner(new SpinnerNumberModel(0,0,60,1));
        minutes.addChangeListener(this);
        preparer = new JButton("Préparer");
        preparer.setBackground(Color.blue);
        preparer.setForeground(Color.white);
        preparer.addActionListener(this);
        progressPrep = new JProgressBar();
        progressPrep.setPreferredSize(new Dimension(200,20));
        progressPrep.setForeground(Color.red);
        progressPrep.setValue(20);
        tempsRestant = new JLabel("Temps restant : 1 min");
        printHour = new JLabel(":");
        
        
        


        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridx = 0;
        cont.gridy = 0;
        cont.gridwidth = 2;
        cont.insets = new Insets(5,0,5,0);
        this.add(temperaturePrepa,cont);
        
        
        cont.gridy = 1;
        this.add(slider1,cont);
        
        
        cont.gridy = 2;
        cont.insets = new Insets(0,0,40,0);
        this.add(temperature,cont);
        
        
        
        cont.fill = GridBagConstraints.CENTER;
        cont.insets = new Insets(0,0,10,0);
        cont.gridy = 3;
        this.add(tempsPrep,cont);
        
        cont.gridx = 0;
        cont.gridwidth = 1;
        cont.weightx = 70;
        cont.fill = GridBagConstraints.EAST;
        cont.anchor = GridBagConstraints.EAST;
        cont.insets = new Insets(0,0,5,0);
        cont.gridy = 4;
        cont.ipady = 20;
        cont.ipadx = 30;
        this.add(minutes,cont);
       
        
        cont.fill = GridBagConstraints.WEST;
        cont.anchor = GridBagConstraints.WEST;
        cont.gridx = 1;
        this.add(secondes,cont);
      
        cont.ipady = 0;
        cont.weightx = 0;
        cont.ipadx = 0;
        cont.anchor = GridBagConstraints.CENTER;
        cont.fill = GridBagConstraints.CENTER;
        cont.gridx = 0;
        cont.gridwidth = 2;
        
        cont.gridy = 5;
        cont.insets = new Insets(0,0,40,0);
        this.add(printHour, cont);

        
        cont.fill = GridBagConstraints.BOTH;
        cont.gridy = 6;
        cont.insets = new Insets(0,0,35,0);
        cont.fill = GridBagConstraints.CENTER;
        cont.gridwidth = 90;
        this.add(preparer,cont);
        
        
        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridwidth = 0;
        cont.insets = new Insets(0,0,5,0);
        cont.gridy = 7;
        this.add(progressPrep,cont);
        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridy = 8;
        this.add(tempsRestant,cont);
            
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==preparer){
            //double temps = (Double.parseDouble(chaineHeures) * 60) + (Double.parseDouble(chaineMin));
            int tempsmin = (int)minutes.getValue();
            int tempssec = (int)secondes.getValue();
            double temps = tempsmin + Double.valueOf(tempssec)/60;
            
            
            System.out.println(temps);
            The theManuel = new The(temps,"",slider1.getValue(),"");
            
            System.out.println("\n");
            System.out.println("Thé crée : \n" + "temps : " + temps + "\n" + "temperature : " + slider1.getValue());
            
            
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == minutes){
            chaineMin = minutes.getValue().toString();
            printHour.setText(chaineMin + " : " + chaineSec);
        }
        else if(e.getSource() == secondes){
            chaineSec = secondes.getValue().toString();
            printHour.setText(chaineMin + " : " + chaineSec);
        }
        
    }
}
