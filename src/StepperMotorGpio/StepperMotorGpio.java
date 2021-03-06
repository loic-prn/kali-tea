package kali.thé;

/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  StepperMotorGpio.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  https://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2021 Pi4J
 * %%
 * #L%
 */

import com.pi4j.component.motor.impl.GpioStepperMotorComponent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiBcmPin;
import com.pi4j.io.gpio.RaspiGpioProvider;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.RaspiPinNumberingScheme;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author lolo
 */
public class StepperMotorGpio implements Execute{

    final private GpioStepperMotorComponent motor;
    final private GpioController gpio;
   
    public  StepperMotorGpio(Pin in1, Pin in2, Pin in3, Pin in4) {
        // in order to use the Broadcom GPIO pin numbering scheme, we need to configure the
        // GPIO factory to use a custom configured Raspberry Pi GPIO provider
        GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

        // create gpio controller
        gpio = GpioFactory.getInstance();


        // provision gpio pins #00 to #03 as output pins and ensure in LOW state
        final GpioPinDigitalOutput[] pins = {
                gpio.provisionDigitalOutputPin(in1, "IN1", PinState.HIGH),
                gpio.provisionDigitalOutputPin(in2, "IN2", PinState.HIGH),
                gpio.provisionDigitalOutputPin(in3, "IN3", PinState.HIGH),
                gpio.provisionDigitalOutputPin(in4, "IN4", PinState.HIGH)};


        // this will ensure that the motor is stopped when the program terminates
        gpio.setShutdownOptions(true, PinState.LOW, pins);

        // create motor component
        motor = new GpioStepperMotorComponent(pins);

    }
    
    @Override
    public void start(){
        
        System.out.println("<--Pi4J--> GPIO Stepper Motor Example ... started.");
  
        // create byte array to demonstrate a double-step sequencing
        // (In this method two coils are turned on simultaneously.  This method does not generate
        //  a smooth movement as the previous method, and it requires double the current, but as
        //  return it generates double the torque.)
        byte[] double_step_sequence = new byte[4];
        double_step_sequence[0] = (byte) 0x9;//0b1000; //0b0011; //9
        double_step_sequence[1] = (byte) 0x5;//0b0001;//0b0110; // 5
        double_step_sequence[2] = (byte) 0x6;//0b0100;//0b1100; // 6
        double_step_sequence[3] = (byte) 0xA;//0b0010;//0b1001; // A

        // define stepper parameters before attempting to control motor
        // anything lower than 2 ms does not work for my sample motor using single step sequence
        motor.setStepInterval(2);
        motor.setStepSequence(double_step_sequence);

        // There are 32 steps per revolution on my sample motor, and inside is a ~1/64 reduction gear set.
        // Gear reduction is actually: (32/9)/(22/11)x(26/9)x(31/10)=63.683950617
        // This means is that there are really 32*63.683950617 steps per revolution =  2037.88641975 ~ 2038 steps!
        motor.setStepsPerRevolution(2048);

        // test motor control : STEPPING FORWARD for half revolution

       try {
           // descendre = positif
           // monter = négatif
           motor.rotate(1.5);
           Thread.sleep(100);
        } catch (InterruptedException ex) {
           Logger.getLogger(StepperMotorGpio.class.getName()).log(Level.SEVERE, null, ex);
       }
        // final stop to ensure no motor activity
        motor.stop();

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        gpio.shutdown();
    }
    
    public void monter(){
        byte[] double_step_sequence = new byte[4];
        double_step_sequence[0] = (byte) 0x9;//0b1000; //0b0011; //9
        double_step_sequence[1] = (byte) 0x5;//0b0001;//0b0110; // 5
        double_step_sequence[2] = (byte) 0x6;//0b0100;//0b1100; // 6
        double_step_sequence[3] = (byte) 0xA;//0b0010;//0b1001; // A
        
        motor.setStepInterval(2);
        motor.setStepSequence(double_step_sequence);
        motor.setStepsPerRevolution(2048);
        
        try {
            motor.rotate(-1);
            Thread.sleep(100);
        } catch(InterruptedException ex){
            Logger.getLogger(StepperMotorGpio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        motor.stop();
        gpio.shutdown();
    }
    
        public void descendre(){
        byte[] double_step_sequence = new byte[4];
        double_step_sequence[0] = (byte) 0x9;//0b1000; //0b0011; //9
        double_step_sequence[1] = (byte) 0x5;//0b0001;//0b0110; // 5
        double_step_sequence[2] = (byte) 0x6;//0b0100;//0b1100; // 6
        double_step_sequence[3] = (byte) 0xA;//0b0010;//0b1001; // A
        
        motor.setStepInterval(2);
        motor.setStepSequence(double_step_sequence);
        motor.setStepsPerRevolution(2048);
        
        try {
            motor.rotate(1);
            Thread.sleep(100);
        } catch(InterruptedException ex){
            Logger.getLogger(StepperMotorGpio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        motor.stop();
        gpio.shutdown();
    }
}
