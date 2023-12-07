package src.VC;

import src.Model.Vehicle;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    ArrayList<Vehicle> cars;
    protected HashMap<Vehicle, BufferedImage> imageMap = new HashMap<>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Vehicle> cars) {
        this.cars = cars;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        // Print an error message in case file is not found with a try/catch block
        try {
            addImageFilesToCars(cars);
        } catch (IOException ex) {ex.printStackTrace();}
    }

    // adds entry with each car and its associated image to HashMap
    protected void addImageFilesToCars(ArrayList<Vehicle> cars) throws IOException {
        for (Vehicle car: cars){
            String vehicleName = car.getModelName();
            String picName = "pics/" + vehicleName + ".jpg";
            imageMap.put(car, ImageIO.read(DrawPanel.class.getResourceAsStream(picName)));
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw image associated with each car:
        drawImagesForCars(g);
    }

    private void drawImagesForCars(Graphics g) {
        for (Vehicle car : imageMap.keySet()) {
            g.drawImage(imageMap.get(car), (int) Math.round(car.getPosition().x), (int) Math.round(car.getPosition().y), null);
        }
    }
}

