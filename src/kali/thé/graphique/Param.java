/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author p2008444
 */
public class Param extends JPanel{
    
    JFrame owner;
    
    JButton timeSwap;
    JButton majThé;
    JButton majLog;
    JButton wifi;
    JButton language;
    
    public Param(JFrame o) {
        this.owner = o;
    }
    
}
