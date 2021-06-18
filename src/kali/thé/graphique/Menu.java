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
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import kali.thé.AnalogInput;
import kali.thé.DigitaBCMGpio;
import kali.thé.DigitaBCMGpio;
import kali.thé.StepperMotorGpio;
import kali.thé.buzzer;
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
    JButton theProgShow;
    
    //peripheriques
    DigitaBCMGpio led;
    AnalogInput termometre;
    buzzer b;
    StepperMotorGpio motor;
    
    int cpt;
    
    /**
     * 
     * @param longueur longueur de la fenetre
     * @param largeur  largeur de la fenetre
     */
    public Menu(int longueur, int largeur) throws InstantiationException, IllegalAccessException {
        led = new DigitaBCMGpio(RaspiBcmPin.GPIO_16);
        try {
            termometre = new AnalogInput(0);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (I2CFactory.UnsupportedBusNumberException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        b = new buzzer(RaspiBcmPin.GPIO_18);
        b.stop();
        led.stop();
        motor = new StepperMotorGpio(RaspiBcmPin.GPIO_22, RaspiBcmPin.GPIO_23, RaspiBcmPin.GPIO_24, RaspiBcmPin.GPIO_25);
        
        this.longueur = longueur;
        this.largeur = largeur;
        this.list = new ArrayList<>();
        this.proglist = new ArrayList<>();
       
        //timer pour heure
        javax.swing.Timer t = new javax.swing.Timer(1000, new ClockListener());
        t.start();
       
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        retour = new JButton();
        title = new JLabel("   Kali-Thé");
        this.setPano(new Accueil(this));
    }
    /**
     * This function just maintain the date up to date (LOL).
     */
    class ClockListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            repaintMenu(df.format(Calendar.getInstance().getTime()));
            
            
            ++cpt;
            if (cpt == 10){ //chaque minute, on verifie si un thé programmé doit commencer
                cpt = 0;
                if (!proglist.isEmpty()){
                    for (int i=0; i<proglist.size(); i++){
                        if (proglist.get(i).getPretPour().getHours() == date.getHours() && proglist.get(i).getPretPour().getMinutes()== date.getMinutes()){
                            int da = date.getDay() - 1;
                            if (da == -1){
                                da = 6;
                            }
                            if (proglist.get(i).isRecurrence(da)){
                                Infusion temp = new Infusion(Menu.this,proglist.get(i));
                                setPano(temp);
                                temp.startThe();
                            }
                        }
                    }
                }
            }  
        }
    }
    
    /**
     * The functino put the date onto the JPanel
     * @param j the JPanel you want to use.
     */
    public void setPano(JPanel j){
        pano = j;
        SimpleDateFormat s = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        initPan(s.format(date));
    }
    
    /**
     * Create the menu.
     * @param heure the time you want to display.
     */
    private void initMenu(String heure){
        ImageIcon retourIcon = new ImageIcon(getClass().getResource("/icones/return.png"));
        ImageIcon progIcon = new ImageIcon(getClass().getResource("/icones/alarm_clock2.png"));
        menu = new JPanel();
        retour.setIcon(retourIcon);
        retour.setBorder(BorderFactory.createLineBorder(Color.white));
        retour.addActionListener(this);
        retour.setBackground(Color.white);
        this.heure = new JLabel(heure);
        title.setFont(new Font("Arial",Font.CENTER_BASELINE,35));
        theProgShow = new JButton();
        theProgShow.setIcon(progIcon);
        theProgShow.addActionListener(this);
        theProgShow.setBorder(BorderFactory.createLineBorder(Color.white));
        theProgShow.setBackground(Color.white);
        
        
        menu.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        menu.setPreferredSize(new Dimension(longueur,50));
        menu.setBackground(Color.white);
        
        cont.insets = new Insets(0,0,0,475);
        cont.gridx = 0; cont.gridy = 0;
        menu.add(retour,cont);
        cont.insets = new Insets(0,0,0,50);
        cont.gridx = 1; cont.gridy = 0;
        menu.add(theProgShow,cont);
        cont.insets = new Insets(0,0,0,0);
        cont.gridx = 2; cont.gridy = 0;
        menu.add(this.heure,cont);
        cont.gridheight = 2;
        cont.gridx = 0; cont.gridy = 0;
        menu.add(title,cont);
    }
    
    /**
     * This initialize the JPanel, calling initMenu/
     * @param heure the current time that will be displayed on the screen.
     */
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
     * This repaint the manu with the hour up to date. 
     * @param heure the current time to repaint.
     */
    public void repaintMenu(String heure){
        this.remove(menu);
        initMenu(heure);
        this.add(menu);
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

    /**
     * Handle the differents action that may be performed.
     * @param e the event that happened. 
     */
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
            else if(pano.getClass() == Infusion.class){
                this.setPano(new Accueil(this));
            }
            else if(pano.getClass() == RecapTheProg.class){
                this.setPano(new Accueil(this));
            }
            
        }
        else if (e.getSource() == theProgShow){
            System.out.println("tprog");
            this.setPano(new RecapTheProg(this));
        }
    }
    
}
