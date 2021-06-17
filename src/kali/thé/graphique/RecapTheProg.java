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
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author lucas
 */
public class RecapTheProg extends JPanel implements ActionListener{

    Menu owner;
    
    ArrayList<JTextArea> tabTP;
    ArrayList<JButton> tabDel;
    
    public RecapTheProg(Menu owner){
        this.owner = owner;
        this.setBackground(Color.white);
        owner.retour.setEnabled(true);
        init();
    }
    
    private void init(){
        this.removeAll();
        
        tabTP = new ArrayList<>();
        tabDel = new ArrayList<>();
        ImageIcon binIcon = new ImageIcon(getClass().getResource("/icones/bin2.png"));
        this.setLayout(new GridBagLayout());
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;
        cont.insets = new Insets(0,0,0,50);
        for (int i=0; i<owner.proglist.size(); i++){
            tabTP.add(new JTextArea("Thé " + Integer.toString(i) + ":\n" + owner.proglist.get(i).toString()));
            tabTP.get(i).setEditable(false);
            JButton boutTemp = new JButton();
            boutTemp.setBackground(Color.white);
            boutTemp.addActionListener(this);
            boutTemp.setBorder(BorderFactory.createLineBorder(Color.white));
            boutTemp.setIcon(binIcon);
            tabDel.add(boutTemp);
            
            cont.gridx = 0;
            cont.gridy = i;
            this.add(tabTP.get(i), cont);
            
            cont.gridx = 1;
            this.add(tabDel.get(i), cont);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0; i<tabDel.size(); i++){
            if(e.getSource() == tabDel.get(i)){
                owner.proglist.remove(i);
                this.init();
                owner.setPano(this);
            }
        }
    }
    
    /**
     * Where you get the size of our screen
     * @return Dimension the dimension of the rpi screen.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
    
}
