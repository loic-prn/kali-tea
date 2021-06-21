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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import kali.thé.Connect;
import kali.thé.SQLRequest;
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
    public boolean popup;
    private SQLRequest sql;
    
    /**
     * Catalogue constructor
     * @param o the parent menu. JFrame that own this JPanel
     * @param pop if this JPanel correspond to a popup
     */
    public Catalogue(Menu o, boolean pop) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        popup = pop;
        this.owner = o;
        this.setBackground(Color.white);
        tabThe = new ArrayList<The>();
        
        sql = new SQLRequest();
        
        int a = sql.selectDesc().size();
        System.out.println("lulu tu sais pas coder: "  + a);
        
        for (int i = 0; i < a; i++){
            //tabThe.add(new The(11, "lulu", 34, "lulu il sait pas faire de tableau et ça rend un peu fou quand même"));
            //The truc = new The(sql.selectDuree().get(i), sql.selectName().get(i), sql.selectTemperature().get(i), sql.selectDesc().get(i));
            //System.out.println(truc.getNom() + "\n" + truc.getTemperature() + "\n" + truc.getTempsInfusion() + "\n" + truc.getDescription());
            tabThe.add(new The(sql.selectDuree().get(i), sql.selectName().get(i), sql.selectTemperature().get(i), sql.selectDesc().get(i),sql.selectCate().get(i)));
        }
        
        tabTheButton = new JButton[tabThe.size()];
        
        this.init();
    }
    /**
     * This function is called in the constructor to make it easier to understand.
     */
    private void init(){
        Color vert = new Color(0x00b300);
        Font police1 = new Font("Arial",Font.BOLD,18);
        owner.setTitle("Catalogue");
        owner.retour.setEnabled(true);
        owner.title.setVisible(true);
        
        this.setLayout(new GridBagLayout());
        
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints cont2 = new GridBagConstraints();
        cont2.fill = GridBagConstraints.BOTH;
        cont2.gridx = 0;
        cont2.insets = new Insets(0,0,2,0);
        
        
        for(int i = 0; i < tabThe.size(); i++){
            tabTheButton[i] = new JButton(tabThe.get(i).getCategorie() + " : " + tabThe.get(i).getNom());
            tabTheButton[i].addActionListener(this);
            tabTheButton[i].setPreferredSize(new Dimension(482,40));
            tabTheButton[i].setForeground(Color.white);
            tabTheButton[i].setBackground(vert);
            tabTheButton[i].setFont(police1);
            cont2.gridy = i;
            pane.add(tabTheButton[i], cont2); 
        }
        
        
        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setPreferredSize(new Dimension(500,400));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        
        GridBagConstraints cont = new GridBagConstraints();
        cont.fill = GridBagConstraints.BOTH;
        cont.gridx = 0;
        cont.gridy = 0;
        this.add(scrollPane, cont);
    }

    /**
     * The function that handle the possible action performed.
     * @param e the event that occured.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < tabThe.size() ; i++){
            if(!popup && e.getSource() == tabTheButton[i]){
                    owner.setPano(new Infusion(owner, tabThe.get(i)));
            }
        }
    }
    
    /**
     * 
     * @return Dimension the window dimension.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(owner.getLongueur(),owner.getLargeur());
    }
    
}
