/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé;

import com.pi4j.io.i2c.I2CFactory;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Date;
import kali.thé.graphique.Menu;
import kali.thé.modele.*;

/**
 *
 * @author lperr
 */
public class KaliThé {

    /**
     * @param args the command line arguments
     * truc
     */
    public static void main(String[] args) throws IOException, I2CFactory.UnsupportedBusNumberException {
        // truc
        Menu me = new Menu(790,430);
        me.setVisible(true);
        AnalogInput anal = new AnalogInput(0);
        anal.start();
    }
    
}
