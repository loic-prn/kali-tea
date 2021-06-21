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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import kali.thé.modele.The;

/**
 *
 * @author p2008444
 */
public class Infusion extends JPanel implements ActionListener{
    Color vert = new Color(0x00b300);
    Menu owner;
    The t;
    
    JLabel titreThe;
    JTextArea description;
    JButton infuser;
    //Slider
    JLabel tempsRestant;
    JProgressBar progressTime;
    JScrollPane scrollPane;
    JLabel indicationEtat;
    
    int percentageComplete = 0;
    int cpt = 0;
    double temps = 0.0;
    int temperatureGET = 0;
    int tempsmin = 0;
    int tempssec = 0;
    boolean preCho = true;
    String precho = " ";
    boolean panier = true;
    
    javax.swing.Timer timer;
    
    /**
     * Infusion constructor.
     * @param o the parent menu.
     * @param t the thea.
     */
    public Infusion(Menu o, The t) {
        this.owner = o;
        this.t = t;
        temps = t.getTempsInfusion();
        temperatureGET = t.getTemperature();
        this.setBackground(Color.white);
        init();
    }
    /**
     * This function just help the constructor to be easier to read.
     */
    private void init(){
        this.removeAll();
        Font police1 = new Font("Arial",Font.BOLD,18);
        Font police2 = new Font("Arial",Font.PLAIN,18);
        //inits
        titreThe = new JLabel(t.getNom());
        titreThe.setFont(police1);
        
        description = new JTextArea(t.getDescription());
        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setFont(police2);
        
        scrollPane = new JScrollPane(description);
        scrollPane.setFont(police1);
        scrollPane.setPreferredSize(new Dimension(400,150));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
                
        infuser = new JButton("Infuser");
        infuser.setBackground(vert);
        infuser.setForeground(Color.white);
        infuser.addActionListener(this);
        infuser.setFont(police1);
        
        progressTime = new JProgressBar();
        progressTime.setValue(percentageComplete);
        progressTime.setForeground(Color.red);
        
        
        indicationEtat = new JLabel(precho);
        indicationEtat.setForeground(Color.red);
        
        tempsmin = (int)t.getTempsInfusion();
        double temp = t.getTempsInfusion() - tempsmin;
        tempssec = (int)(temp*60);
        tempsRestant = new JLabel("     Temps restant : " + Integer.toString((int)(temps*60-cpt)/60) + " mins " + Integer.toString((int)(temps*60-cpt)%60) + " sec     ");
        tempsRestant.setFont(police1);
        
        
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        owner.setTitle("Infusion");
        owner.retour.setEnabled(true);
        owner.title.setVisible(true);
        
        
        
        
        cont.insets = new Insets(10,0,10,0);
        
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(titreThe, cont);
        cont.gridx = 0;
        cont.gridy = 1;
        this.add(scrollPane, cont);
        cont.gridx = 0;
        cont.gridy = 2;
        this.add(infuser, cont);
        cont.gridx = 0;
        cont.gridy = 3;
        cont.fill = GridBagConstraints.BOTH;
        this.add(progressTime, cont);
        
        cont.gridx = 0;
        cont.gridy = 4;
        cont.fill = GridBagConstraints.CENTER;
        this.add(indicationEtat,cont);
        
        cont.gridx = 0;
        cont.gridy = 5;
        cont.fill = GridBagConstraints.NONE;
        this.add(tempsRestant, cont);
    }
    
    /**
     * This class will listen to the time and make the progress bar grow up or ring the buzzer at the end of the time. The function inside is called once a second
     */
    class ClockListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            
            if(preCho){
                owner.led.start();
                preCho = prechauffe();
                owner.retour.setEnabled(false);
                infuser.setEnabled(false);
                owner.theProgShow.setEnabled(false);
            }
            
            else if (panier){
                owner.motor.descendre();
                panier = false;
                owner.retour.setEnabled(false);
                infuser.setEnabled(false);
                owner.theProgShow.setEnabled(false);
            }
            
            else if(!preCho && (cpt*100/(int)(temps*60) < 100)){
                owner.led.stop();
                
                precho = "     Infusion     ";
                
                cpt++;
                percentageComplete = (cpt*100/(int)(temps*60)); //Cb de temps en % il reste.
                owner.setPano(Infusion.this); //La progress bar est refresh avec la nouvelle valeur (percentageComplete)
                owner.retour.setEnabled(false);
                infuser.setEnabled(false);
                owner.theProgShow.setEnabled(false);
              
               
//                owner.led.start();
            }
            else if ((cpt*100/(int)(temps*60) == 100)){
                
                timer.stop();
                precho = "                   ";
                owner.setPano(Infusion.this);
                owner.retour.setEnabled(false);
                infuser.setEnabled(false);
                owner.theProgShow.setEnabled(false);
                cpt = 0;
                percentageComplete = 0;
                
                owner.b.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Preparation.class.getName()).log(Level.SEVERE, null, ex);
                }
                owner.b.stop();
                owner.motor.monter();
                panier = true;
                owner.retour.setEnabled(true);
                infuser.setEnabled(true);
                owner.theProgShow.setEnabled(true);
                
            }
        }
        
        /*public void actionPerformed(ActionEvent e) { 
            
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
        }*/
    }

    /**
     * the funciton that start the timer, and so the thea
     */
    public void startThe(){
        precho = "En préchauffe";
        init();
        timer = new javax.swing.Timer(1000, new Infusion.ClockListener());
        timer.start();
        preCho = prechauffe();
    }
    
    /**
     * 
     * @param e the event that occured.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == infuser){
            this.startThe();
        }
    }
    
    /**
     * a function that knows if the the water is hot enough
     * @return 
     */
    private boolean prechauffe(){
        
        owner.retour.setEnabled(false);
        owner.termometre.start();
        int matemp = 0;
        System.out.println("> " + Double.toString(owner.termometre.getDonnees()) + " <");
        /*if(t.getTemperature() == 60){
            matemp = 80;
        }
        else if(t.getTemperature() == 75){
            matemp = 93;
        }
        else if(t.getTemperature() == 85){
            matemp = 123;
        }
        else if(t.getTemperature() == 90){
            matemp = 170;
        }
        else if(t.getTemperature() == 95){
            matemp = 175;
        }*/
        if(owner.termometre.getDonnees() >= t.getTemperature()){
            System.out.println("BONNE TEMPERATURE " + t.getTemperature());
            return false;
        }
        
        
        return true;
    }
    
    /**
     * 
     * @return Dimension the dimension of the current window. 
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
    
}
