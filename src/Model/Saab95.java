package src.Model;

import java.awt.*;

public class Saab95 extends Cars{

    private boolean turboOn;

    public Saab95 (int nrDoors, double enginePower, Color color, String modelName, double xPos, double yPos) {

        super(nrDoors, enginePower, color, modelName, xPos, yPos);
       // setPosition(xPos,yPos);
        turboOn = false;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
    @Override
    protected void incrementSpeed(double amount){
        if (getCurrentSpeed() != 0) {
            currentSpeed = getCurrentSpeed() + speedFactor() * amount;
        }

    }
    @Override
    protected void decrementSpeed(double amount){
        currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }
}
