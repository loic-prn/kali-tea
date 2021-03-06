/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
                dataRead = (buffer[1] * 256) + (128 * i) + buffer[0];
                //console.println("dataRead: " + dataRead);
                if(prev > 0 && dataRead < 0){
                    i++;
                }
                dataRead = (buffer[1] * 256) + (256 * i) + buffer[0];
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kali.thé;

/**
 * @author l.buathier
 *
 * Analog read from I2C analog port A0 change numCanal to read another Analog
 * input (0 to 3)
 *
 */
import java.io.IOException;
import java.util.Arrays;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;
import com.pi4j.platform.PlatformAlreadyAssignedException;
import com.pi4j.util.Console;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kali.thé.Execute;

/**
 * This example code demonstrates how to perform simple I2C communication on the
 * Raspberry Pi. For this example we will connect to RPi BAs Hat on canal A0
 *
 */
public class AnalogInput implements Execute{

    private static final byte WORD = 2;  // number of bytes to read

    // TSL2561 I2C address
    private static final byte BASE_HAT_ADDR = 0x04;

    // TSL2561 registers
    private static final byte BASE_HAT_REG_SET_ADDR = (byte) 0xC0;
    private static final byte BASE_HAT_REG_RAW_DATA = (byte) 0x10;
    private static final byte BASE_HAT_REG_INPUT_VOLTAGE = (byte) 0x20;
    private static int numCanal = 0;  // A0 connector
    private double donnees; // HEDI
    I2CDevice device;
    
    
    //test
    int compteur = 0;
    double memoireTemp = 0;
    /**
     * The constructor to handle an analog input device.
     * @param numCanal the canal where the input is connected.
     * @throws IOException to handle the possible exceptions.
     * @throws com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException because we may throw exceptions linked to this lib.
     */
    public AnalogInput(int numCanal) throws IOException, UnsupportedBusNumberException{
         this.numCanal=numCanal;       
        // fetchAllAvailableBusses(console);
        // get the I2C bus to communicate on
        I2CBus i2c = I2CFactory.getInstance(I2CBus.BUS_1);

        // create an I2C device for an individual device on the bus that you want to communicate with
        // in this example we will use the default address for the TSL2561 chip which is 0x39.
        device = i2c.getDevice(BASE_HAT_ADDR);

    }


    /**
     * The function that start the analog.
     */
    @Override
    public void start() {
        // create Pi4J console wrapper/helper
        // (This is a utility class to abstract some of the boilerplate code)
        final Console console = new Console();

        // print program title/header
        //console.title("<-- The Pi4J Project -->", "I2C Analog Conversion");

        // allow for user to exit program using CTRL-C
        //console.promptForExit();
        for (int a =0; a <3; a++){

            // now we will perform our first I2C READ operation to retrieve raw integration
            // results from DATA_0 and DATA_1 registers
            console.println("... reading DATA registers from A0");
            try {
                device.write(BASE_HAT_REG_RAW_DATA);
            } catch (IOException ex) {
                Logger.getLogger(AnalogInput.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(AnalogInput.class.getName()).log(Level.SEVERE, null, ex);
            }
    /*     // if only byte to read 
        int dataRead= device.read(BASE_HAT_REG_RAW_DATA+numCanal);
        console.println("AnalogInput input A" + numCanal +" = " +  dataRead);
    */
            int prev = 1;
            int dataRead;
            byte buffer[] = new byte[2];
            int BytesReceveived = 0;
            try {
                BytesReceveived = device.read(BASE_HAT_REG_RAW_DATA, buffer, numCanal, WORD);
            } catch (IOException ex) {
                Logger.getLogger(AnalogInput.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (BytesReceveived == 2) {
                
                
                dataRead = ((int)buffer[1])<<8 | (int)buffer[0]; //BUTAHIER
                
                if (dataRead < 4096 && dataRead > 0) {
                    console.println("Analog A0  " + (double)dataRead/5.0);
                    donnees = (dataRead/5.0)/2.0;
                    
                    
                    System.out.println("AnalogInput A0 (decimal) = " + (double)dataRead/5.0);
                    //console.println("AnalogInput A0 (hexa) = " + String.format("0x%02x", dataRead));
                }
            }
        }
    }

    
    
    public double getDonnees(){ // HEDI
        return donnees;
    }
    
    
    private void fetchAllAvailableBusses(Console console) {
        // fetch all available busses
        try {
            int[] ids = I2CFactory.getBusIds();
            console.println("Found follow I2C busses: " + Arrays.toString(ids));
        } catch (IOException exception) {
            console.println("I/O error during fetch of I2C busses occurred");
        }

        // find available busses
        for (int number = I2CBus.BUS_0; number <= I2CBus.BUS_17; ++number) {
            try {
                @SuppressWarnings("unused")
                I2CBus bus = I2CFactory.getInstance(number);
                console.println("Supported I2C bus " + number + " found");
            } catch (IOException exception) {
                console.println("I/O error on I2C bus " + number + " occurred");
            } catch (UnsupportedBusNumberException exception) {
                console.println("Unsupported I2C bus " + number + " required");
            }
        }
    }

}