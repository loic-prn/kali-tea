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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import kali.thé.modele.The;

/**
 *
 * @author p2008444
 */
public class Catalogue extends JPanel implements ActionListener{
    
    Menu owner;
    JButton[] tabTheButton;
    ArrayList<The> tabThe;
    JScrollPane scrollPane;
    
    
    public Catalogue(Menu o) {
        this.owner = o;
        this.setBackground(Color.white);
        tabThe = new ArrayList<The>();
        
        for (int i = 0; i < 100; i++){
            
            tabThe.add(new The(10, "The " + i, 50, "Desc" + i));
        }
        
        tabTheButton = new JButton[tabThe.size()];
        this.init();
    }
    
    private void init(){
        owner.setTitle("Catalogue");
        owner.retour.setEnabled(true);
        owner.title.setVisible(true);
        
        this.setLayout(new GridBagLayout());
        
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints cont2 = new GridBagConstraints();
        cont2.fill = GridBagConstraints.BOTH;
        cont2.gridx = 0;
        
        
        
        for(int i = 0; i < tabThe.size(); i++){
            tabTheButton[i] = new JButton(tabThe.get(i).getNom());
            tabTheButton[i].addActionListener(this);
            tabTheButton[i].setPreferredSize(new Dimension(282,30));
            cont2.gridy = i;
            pane.add(tabTheButton[i], cont2); 
        }
        
        
        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setPreferredSize(new Dimension(300,400));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(scrollPane, cont);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
    
}
