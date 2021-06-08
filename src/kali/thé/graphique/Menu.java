/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import kali.thé.modele.*;

/**
 *
 * @author lperr
 */
public class Menu extends JFrame{
    private int longueur;
    private int largeur;
    ArrayList<The> list;
    ArrayList<TheProgramme> proglist;

    public Menu(int longueur, int largeur) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.list = new ArrayList<>();
        this.proglist = null;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public void setLargeur(int largeur) {
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(longueur,largeur);
    }
    
}
