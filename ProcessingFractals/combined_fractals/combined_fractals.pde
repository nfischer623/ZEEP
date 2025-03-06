//graph size
float minX = -2;
float maxX = 2;
float minY = -1.5;
float maxY = 1.5;

//max iterations
int maxiter = 100;

//user inputs:
//Ellie's palette: #006699, #ae82fa, #f261b1, #ffb433
color[] colorPicks = {#006699, #ae82fa, #f261b1, #ffb433};
//varieties: "mandelbrot", "julia", "newton"
String variety = "julia";

color myColor = 0;

//funky filter
boolean filterOn = false;
float nsmooth;

void setup(){
  size(1400, 1050);
}

void draw(){
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
            
      if (variety == "mandelbrot"){
        n = mandelbrot(a, b, n);
      }else
      if (variety == "julia"){
        n = julia(a, b, n);
      }else
      if (variety == "newton"){
        n = newtonCountIterations(a, b, n);
      }
      
      //pixel location within a 1d array
      int location = (x + y*width);
           
      addColor(n, location);     
        }  
      }
      updatePixels();
    }
