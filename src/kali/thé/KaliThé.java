/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé;

import com.pi4j.io.gpio.RaspiBcmPin;
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
     *
     */
    public static void main(String[] args) throws IOException, I2CFactory.UnsupportedBusNumberException, InterruptedException, InstantiationException, IllegalAccessException {
        // truc
        Menu me = new Menu(790,430);
        me.setVisible(true);
        //AnalogInput anal = new AnalogInput(1);
        //anal.start();
        // test unitaire sur GPIO_18
//        buzzer digitaBCMGpio = new buzzer(RaspiBcmPin.GPIO_18);
//        digitaBCMGpio.start();
//        Thread.sleep(1000);
//        digitaBCMGpio.stop();        
        //SQLRequest app = new SQLRequest();
        //app.selectName();
//          StepperMotorGpio cx28byj48 = new StepperMotorGpio(RaspiBcmPin.GPIO_22, RaspiBcmPin.GPIO_23, RaspiBcmPin.GPIO_24, RaspiBcmPin.GPIO_25);
//          cx28byj48.monter();
//          cx28byj48.descendre();

    }
    
}
