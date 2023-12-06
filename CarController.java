import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public CarController() {
        // Instance of this class
        CarController cc = new CarController();


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();

        cc.frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.gas(cc.frame.gasAmount);
            }
        });
        cc.frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cc.brake(cc.frame.gasAmount);

            }
        });

        cc.frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cc.turboOn();
            }
        });

        cc.frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cc.turboOff();
            }
        });

        cc.frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cc.liftBed();
            }
        });

        cc.frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cc.lowerBed();
            }
        });
        cc.frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {cc.startAllCars();
            }
        });
        cc.frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { cc.stopAllCars();
            }
        });

    }
    
    /* Each step the TimerListener moves all the cars in the list and tells the view to update its images.*/
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                reverseCarAtWindowEdge(car);
                car.move();
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    protected static void reverseCarAtWindowEdge(Vehicle car) {
        if(car.getXPosition() <= 0 || car.getXPosition() >= 800-110){
            car.turnLeft();
            car.turnLeft();
        }
    }



    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle car : cars
                ) {
            car.gas(gas);
        }
    }
    void brake(int amount){
        double brake = ((double)amount/ 100);
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }

    void startAllCars(){
        for (Vehicle car : cars){
            car.startEngine();
        }
    }

    void stopAllCars(){
        for (Vehicle car : cars){
            car.stopEngine();
        }
    }


    void turboOn(){
        for (Vehicle car: cars){
            if (car instanceof Saab95)
                ((Saab95) car).setTurboOn();
        }
    }

    void turboOff(){
        for (Vehicle car: cars){
            if (car instanceof Saab95)
                ((Saab95) car).setTurboOff();
        }
    }

    void liftBed(){
        for (Vehicle car: cars){
            if (car instanceof Scania)
                ((Scania) car).increaseTruckbedAngle(10);

        }
    }

    void lowerBed(){
        for (Vehicle car: cars){
            if (car instanceof Scania){
                ((Scania) car).reduceTruckbedAngle(10);
            }
        }
    }

}
