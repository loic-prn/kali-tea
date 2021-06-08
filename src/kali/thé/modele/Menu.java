/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.modele;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author lperr
 */
public class Menu extends JFrame{
    private double longueur;
    private double largeur;
    ArrayList<The> list;
    ArrayList<TheProgramme> proglist;

    public Menu(double longueur, double largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.list = new ArrayList<>();
        this.proglist =null;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public void setList(ArrayList<The> list) {
        this.list = list;
    }

    public void setProglist(ArrayList<TheProgramme> proglist) {
        this.proglist = proglist;
    }

    public double getLongueur() {
        return longueur;
    }

    public double getLargeur() {
        return largeur;
    }

    public ArrayList<The> getList() {
        return list;
    }

    /*
    *
    */
    
    public ArrayList<TheProgramme> getProglist() {
        return proglist;
    }
    
    
}
