package com.example.fractalprototype;

import javafx.scene.paint.Color;
import processing.core.*;

import java.util.Objects;

public class FractalSketch extends PApplet {
    protected final FractalModel model;
    protected final int sketchWidth, sketchHeight;
    protected String fractalType;
    protected int[] colorPicks;


    public FractalSketch(FractalModel model, int width, int height) {
        this.model = model;
        this.sketchWidth = width;
        this.sketchHeight = height;

        this.colorPicks = new int[4];
        this.fractalType = "mandelbrot";

        updateColors(false);
    }


    // Exports the current sketch as a PNG image.
    // Uses Processing's built-in save() function.
    public void saveImage(String fileName){
        save(fileName);
    }


    // Saves the current sketch data to a .zeep file.
    public void saveZeep(String fileName) {
        String [] fractalData = {
                fractalType,
                Float.toString(minX),
                Float.toString(maxX),
                Float.toString(minY),
                Float.toString(maxY),
                Float.toString(juliaX),
                Float.toString(juliaY)
        };
        for (int color : colorPicks) {
            fractalData = append(fractalData, Integer.toString(color));
        }
        saveStrings(fileName, fractalData);
    }


    // Loads sketch data from an existing .zeep file into the current sketch.
    public void loadZeep(String fileName) {
        // good lord this function is ugly

        String [] fractalData = loadStrings(fileName);

        // type, position
        fractalType = fractalData[0];
        minX = parseFloat(fractalData[1]);
        maxX = parseFloat(fractalData[2]);
        minY = parseFloat(fractalData[3]);
        maxY = parseFloat(fractalData[4]);
        juliaX = parseFloat(fractalData[5]);
        juliaY = parseFloat(fractalData[6]);
        // colors
        model.colorAProperty().set(model.getColorFromString(fractalData[7]));
        model.colorBProperty().set(model.getColorFromString(fractalData[8]));
        model.colorCProperty().set(model.getColorFromString(fractalData[9]));
        model.colorDProperty().set(model.getColorFromString(fractalData[10]));
        updateColors(false);

        if (Objects.equals(fractalType, "julia")) {
            juliaPaused = true;
        }

        redraw();
    }


    public void setup() {
        surface.setSize(sketchWidth, sketchHeight);
        surface.setLocation(displayWidth / 3, (displayHeight - sketchHeight) / 2);
        // NOTE: this will implicitly truncate any non-int results
        // may want to make explicit in the future

        surface.setTitle("Fractal Viewer");

        PImage icon = loadImage("ZEEP_icon.png");
        surface.setIcon(icon);

        colorMode(HSB);
    }


    //graph size
    float minX = -2;
    float maxX = 2;
    float minY = -1.5f;
    float maxY = 1.5f;

    //max iterations
    int maxiter = 100;

    int myColor = 0;

    //funky filter
    boolean filterOn = false;
    float nsmooth;

    // julia set variables
    boolean juliaPaused = false;
    float juliaX;
    float juliaY;


    public void draw() {
        loadPixels();

        //pixel mapping and mandelbrot math from https://thecodingtrain.com/challenges/21-mandelbrot-set-with-p5js
        //for each pixel
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                //puts math on a smaller interval
                var a = map(x, 0, width, minX, maxX);
                var b = map(y, 0, height, minY, maxY);

                //number of iterations
                var n = 0;

                // could include more fractal types in the future
                // just add another switch case and another function
                switch (fractalType) {
                    case "mandelbrot":
                        n = mandelbrot(a, b, n);
                        break;
                    case "julia":
                        n = julia(a, b, n);
                        break;
                    default:
                        n = mandelbrot(a, b, n);
                }

                //pixel location within a 1d array
                int location = (x + y*width);
                addColor(n, location);

            }
        }
        updatePixels();
    }


    public int mandelbrot (float a, float b, int n) {
        /*Mandelbrot Fractal function
        Takes in complex number a+bi, max number of iterations, n
        Returns how many iterations were completed, n
        */

        //real component
        var start_real = a;
        //imaginary component
        var start_i = b;

        //makes faster by setting center of mandelblobs to max iterations
        if (((a + .25f)*(a + .25f) + .81f*b*b <= .25f) || ((a+1)*(a+1) + b*b <= .06f)){
            n = maxiter;
        }

        while (n < maxiter) {
            //(a+bi)^2 = a^2 + 2abi - b^2
            // real component
            var new_real = a*a - b*b;
            //imaginary component
            var new_i = 2*a*b;

            //next iteration components
            a = new_real + start_real;
            b = new_i + start_i;

            //checks if point is going towards infinity
            if (a*a + b*b > 4) {
                break;
            }
            //increments iteration counter
            n++;
        }
        //funky filter component
        nsmooth = n + 1 - log(log(abs(a)))/log(2);
        return n;
    }


    public int julia(float a, float b, int n) {
        /*Function to render Julia set fractals
        Takes in complex number a+bi, max number of iterations, n
        Returns how many iterations were completed, n
        */
        float realc;
        float imagc;

        if (!juliaPaused){
            //set Julia constant based on mouse
            realc= map(mouseX, 0, width, -1, 1);
            imagc= map(mouseY, 0, height, -.75f, .75f);
        }else{
            realc= map(juliaX, 0, width, -1, 1);
            imagc=map(juliaY, 0, height, -.75f, .75f);
        }

        while (n < maxiter) {
            //(a+bi)^2 = a^2 + 2abi - b^2
            // real component
            var real= a*a - b*b;
            //imaginary component
            var imag = 2*a*b;

            //next iteration components
            a=real + realc;
            b=imag+ imagc;

            //checks if point is going towards infinity
            if (a*a + b*b > 4) {
                break;
            }
            //funky filter component
            //nsmooth = n + 1 - log(log(abs(a)))/log(2);

            //increments iteration counter
            n++;
        }
        //funky filter component
        if (filterOn){
            nsmooth = n + 1 - log(log(abs(a)))/log(2);
        }
        return n;
    }


    public void mouseDragged(){
        //rate of change of movement based on scale of graph
        float delta = .0025f * abs(maxX - minX);

        //determines direction mouse is moving in
        if (pmouseX < mouseX) {
            //shifts range that window sees
            minX = minX - delta;
            maxX = maxX - delta;
        }
        if (pmouseX > mouseX) {
            minX = minX + delta;
            maxX = maxX + delta;
        }
        if (pmouseY < mouseY) {
            minY = minY - delta;
            maxY = maxY - delta;
        }
        if (pmouseY > mouseY) {
            minY = minY + delta;
            maxY = maxY + delta;
        }
    }


    public void keyPressed(){
        //center point in window
        float xCenter = (minX + maxX)/2;
        float yCenter = (minY + maxY)/2;

        float rangex = maxX - minX;
        //zoom factor
        float zoomIn = .3f * rangex;
        float zoomOut = .9f * rangex;

        //zoom in
        if (key == '+' || key == '=') {
            minX = xCenter - zoomIn;
            maxX = xCenter + zoomIn;
            minY = yCenter - .8f*zoomIn;
            maxY = yCenter + .8f*zoomIn;
        }
        //zoom out
        if (key == '_' || key == '-') {
            minX = xCenter - zoomOut;
            maxX = xCenter + zoomOut;
            minY = yCenter - .8f*zoomOut;
            maxY = yCenter + .8f*zoomOut;
        }
        // pause re-mapped to right click
        if (key == 'f' || key == 'F'){
            filterOn = !filterOn;
        }
    }


    public void mouseClicked() {
        if (Objects.equals(fractalType, "julia")) {
            if (mouseButton == RIGHT) {
                juliaPaused = !juliaPaused;
                if (juliaPaused) {
                    juliaX = mouseX;
                    juliaY = mouseY;
                }
            }
        }
    }


    public void addColor(int n, int location){
        //grayscale color scheme if no given colors
        if (colorPicks.length == 0) {
            myColor = color(map(n, 0, maxiter, 0, 250));
        } else if (colorPicks.length == 1) { // one color given
            myColor = colorPicks[0];
            myColor = color(hue(myColor), saturation(myColor), map(n, 0, maxiter, 0, 250));
        } else { // multiple colors given
            int numColors = colorPicks.length;
            for (int i=0; i<numColors; i++){
                //divides number of iterations into groups based on number of colors
                if (n >= (i*maxiter)/numColors && n <= ((i+1)*maxiter)/numColors) {
                    myColor=colorPicks[i];
                }
            }
        }
        if (filterOn){
            //https://stackoverflow.com/questions/369438/smooth-spectrum-for-mandelbrot-set-rendering
            myColor = color(.95f*hue(myColor)+10*nsmooth, .6f*saturation(myColor), brightness(myColor));
        }

        //sets pixel color
        pixels[location]=color(myColor);
    }


    public void updateColors(boolean isRedraw) {
        colorPicks[0] = model.getColorProcessingValue(model.getColorA());
        colorPicks[1] = model.getColorProcessingValue(model.getColorB());
        colorPicks[2] = model.getColorProcessingValue(model.getColorC());
        colorPicks[3] = model.getColorProcessingValue(model.getColorD());

        if (isRedraw) {
            redraw();
        }
    }


    // used with the Recenter button in the UI
    public void resetView() {
        minX = -2;
        maxX = 2;
        minY = -1.5f;
        maxY = 1.5f;
    }


    public void setFractalType(String type) { fractalType = type; }


    public void settings() {
        size(1400, 1050);
    }
}
