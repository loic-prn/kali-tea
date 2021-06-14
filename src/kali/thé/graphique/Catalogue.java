/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé.graphique;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import kali.thé.modele.The;

/**
 *
 * @author p2008444
 */
public class Catalogue extends JPanel implements ActionListener{
    
    JFrame owner;
    JButton[] tabTheButton;
    ArrayList<The> tabThe;
    
    
    public Catalogue(JFrame o) {
        this.owner = o;
        tabThe = new ArrayList<The>();
        
        for (int i = 0; i < 10; i++){
            
            tabThe.add(new The(10, "The " + i, 50, "Desc" + i));
        }
        
        tabTheButton = new JButton[tabThe.size()];
        this.init();
    }
    
    private void init(){
        owner.setTitle("Catalogue");
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        

        cont.fill = GridBagConstraints.BOTH;
        cont.gridx = 0;
        

        
        for(int i = 0; i < tabThe.size(); i++){
            tabTheButton[i] = new JButton(tabThe.get(i).getNom());
            tabTheButton[i].addActionListener(this);
            cont.gridy = i;
            this.add(tabTheButton[i], cont);   
            
            
            
        }
        
         
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
