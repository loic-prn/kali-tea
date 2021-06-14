package kali.thé;

/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  DigitaBCMGpio.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  https://www.pi4j.com/
 * **********************************************************************
 *
 * #L%
 */

import com.pi4j.io.gpio.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This example code demonstrates how to perform simple state
 * control of a GPIO pin on the Raspberry Pi
 * using the Broadcom chipset GPIO pin numbering scheme.
 *
 * @author Robert Savage
 * adapted by Lionel Buathier
 */
public class DigitaBCMGpio implements Execute{
    
    final private GpioPinDigitalOutput pin;
    final private GpioController gpio;
    
    public DigitaBCMGpio(Pin pinNumber) throws UnsupportedOperationException{
        // in order to use the Broadcom GPIO pin numbering scheme, we need to configure the
        // GPIO factory to use a custom configured Raspberry Pi GPIO provider
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        // create gpio controller
        gpio = GpioFactory.getInstance();

        // provision broadcom gpio pin #16 as an output pin and turn on
        pin = gpio.provisionDigitalOutputPin(pinNumber, "MyLED", PinState.HIGH);
        
        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);

    }

    @Override
    public void start()  {
        System.out.println("<--Pi4J--> GPIO Control LED on GPIO BCM_16 ... started.");

        System.out.println("--> GPIO state should be: ON");

        try {
            Thread.sleep(1000);
            pin.high();
            Thread.sleep(1000000000);
        
        }catch (InterruptedException ex) {
            Logger.getLogger(DigitaBCMGpio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    
    
    public static void main(String[] args) throws InterruptedException {
    // test unitaire sur GPIO_16
        DigitaBCMGpio digitaBCMGpio = new DigitaBCMGpio(RaspiBcmPin.GPIO_16);
        digitaBCMGpio.start();
    }
}

