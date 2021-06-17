/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.i2c.I2CFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import kali.thé.AnalogInput;
import kali.thé.DigitaBCMGpio;
import kali.thé.DigitaBCMGpio;
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
    JLabel prechauffeTxt;
    
    int percentageComplete = 0;
    int cpt = 0;
    int tempsmin = 0;
    int tempssec = 0;
    double temps = 0.0;
    int temperatureGET = 0;
    The theManuel;
    String precho = " ";
    
    boolean preCho = true;
    
    javax.swing.Timer timer;
    //led
    
    /**
     * This is the function to initialize de preparation page.
     * @param o the page that is displayed. 
     */
    public Preparation(Menu o) {
        this.owner = o;
        this.setBackground(Color.white);
        owner.b.stop();
        owner.led.stop();
        this.init();
    }
    
    /**
     * The function organize the different items.
     */
    private void init(){
        this.removeAll();
        Font police1 = new Font("Arial",Font.BOLD,15);
        Color vert = new Color(0x00b300);
        owner.setTitle("Préparation");
        owner.retour.setEnabled(true);
        owner.title.setVisible(true);
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        
        temperaturePrepa = new JLabel("Temperature de prepartion");
        temperaturePrepa.setFont(police1);
        
        slider1 = new JSlider();
        slider1.setPreferredSize(new Dimension(300,20));
        slider1.setMaximum(120);
        slider1.setMinimum(30);
        slider1.addChangeListener(this);
        temperature = new JLabel(Integer.toString(temperatureGET) + "°C");
        slider1.setValue(temperatureGET);
        
        tempsPrep = new JLabel("Temps de préparation");
        tempsPrep.setFont(police1);
        
        secondes = new JSpinner(new SpinnerNumberModel(0,0,60,1));
        secondes.setValue(tempssec);
        secondes.addChangeListener(this);
        
        minutes = new JSpinner(new SpinnerNumberModel(0,0,60,1));
        minutes.setValue(tempsmin);
        minutes.addChangeListener(this);
        
        preparer = new JButton("Préparer");
        preparer.setBackground(vert);
        preparer.setForeground(Color.white);
        preparer.addActionListener(this);
        preparer.setFont(police1);
        
        progressPrep = new JProgressBar();
        progressPrep.setValue(percentageComplete);
        progressPrep.setPreferredSize(new Dimension(200,20));
        progressPrep.setForeground(Color.red);
        
        printHour = new JLabel(Integer.toString(tempsmin) + " mins " + Integer.toString(tempssec) + " sec");
        tempsRestant = new JLabel("Temps restant : " + Integer.toString((int)(temps*60-cpt)/60) + " mins " + Integer.toString((int)(temps*60-cpt)%60) + " sec");
        
        prechauffeTxt = new JLabel(precho);
        prechauffeTxt.setForeground(Color.red);
        
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
        cont.insets = new Insets(5,0,5,0);
        cont.gridy = 7;
        this.add(prechauffeTxt,cont);
        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridwidth = 0;
        cont.insets = new Insets(0,0,5,0);
        cont.gridy = 8;
        this.add(progressPrep,cont);
        
        cont.fill = GridBagConstraints.CENTER;
        cont.gridy = 9;
        this.add(tempsRestant,cont);
            
    }

    /**
     * This function will listen to the time and make the progress bar grow up or ring the buzzer at the end of the time.
     */
    class ClockListener implements ActionListener { //Chaque seconde, le code ci-dessous est réalisé, (après avoir qclickuer sur préparer)
        
        public void actionPerformed(ActionEvent e) { 
            
            if(preCho){
                owner.led.start();
                preCho = prechauffe();
                owner.retour.setEnabled(false);
                preparer.setEnabled(false);
                owner.theProgShow.setEnabled(false);
            }
            
            else if(!preCho && (cpt*100/(int)(temps*60) < 100)){ // Si la bar est pas complète
                
                owner.led.stop();
                precho = "     Infusion     ";
                init();
                
                cpt++;
                percentageComplete = (cpt*100/(int)(temps*60)); //Cb de temps en % il reste.
                init(); //La progress bar est refresh avec la nouvelle valeur (percentageComplete)
                
                //INFUSION
                
            }
            
            else if ((cpt*100/(int)(temps*60) == 100)){
                timer.stop();
                precho = "                   ";
                init();
                cpt = 0;
                percentageComplete = 0;
                owner.retour.setEnabled(true);
                preparer.setEnabled(true);
                owner.theProgShow.setEnabled(true);
                
                
                owner.b.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Preparation.class.getName()).log(Level.SEVERE, null, ex);
                }
                owner.b.stop();
            }
        }
    }
    /** 
     * here are handled the differents action performed on this page.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==preparer){
            //double temps = (Double.parseDouble(chaineHeures) * 60) + (Double.parseDouble(chaineMin));
            
            tempsmin = (int)minutes.getValue();
            tempssec = (int)secondes.getValue();
            temps = tempsmin + Double.valueOf(tempssec)/60;
            
            System.out.println(temps);
            theManuel = new The(temps,"",slider1.getValue(),"");
            
            System.out.println("\n");
            System.out.println("Thé crée : \n" + "temps : " + temps + "\n" + "temperature : " + slider1.getValue());
            
            
            
            

            
            precho = "En préchauffe";
            init();
            timer = new javax.swing.Timer(1000, new Preparation.ClockListener());
            timer.start();
            preCho = prechauffe();
        }
    }
    
    private boolean prechauffe(){
        
        owner.retour.setEnabled(false);
        preparer.setEnabled(false);
        owner.termometre.start();
        
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + Double.toString(owner.termometre.getDonnees()) + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        
        if(owner.termometre.getDonnees() >= theManuel.getTemperature()){
            System.out.println("BONNE TEMPERATURE " + theManuel.getTemperature());
            return false;
        }
        
        
        return true;
    }
    
    
     /**
     * Where you get the size of our screen
     * @return Dimension the dimension of the rpi screen.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }

    /**
     * It manages the JSpinner and get the value selected.
     * @param e is the event we aim to manage.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == minutes){
            tempsmin = (int)minutes.getValue();
            printHour.setText(Integer.toString(tempsmin) + " mins " + Integer.toString(tempssec) + " sec");
        }
        else if(e.getSource() == secondes){
            tempssec = (int)secondes.getValue();
            printHour.setText(Integer.toString(tempsmin) + " mins " + Integer.toString(tempssec) + " sec");
        }
        else if (e.getSource() == slider1){
            temperature.setText(String.valueOf(slider1.getValue()) + "°C");
            temperatureGET = slider1.getValue();
        }
    }
}
