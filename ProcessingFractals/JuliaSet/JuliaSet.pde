//graph size
float minx = -2;
float maxx = 2;
float miny = -1.5;
float maxy = 1.5;

//max iterations
int maxiter = 100;

boolean paused = false;

float myX;
float myY;

void setup() {
  size(1400, 1050);
}

void draw() {
  loadPixels();
  
  //julia constant (complex number)
  //real component plotted on x-axis, imaginary plotted on y
  float realc= map(mouseX, 0, width, -1, 1);
  float imagc= map(mouseY, 0, height, -1, 1);
  
  if (paused==false){
    realc= map(mouseX, 0, width, -1, 1);
    imagc= map(mouseY, 0, height, -1, 1);
  }
  if (paused == true){
    realc= map(myX, 0, width, -1, 1);
    imagc=map(myY, 0, height, -1, 1);
  }
  
  boolean clicked=false;
      
  //https://thecodingtrain.com/challenges/21-mandelbrot-set-with-p5js
  //for each pixel
  //if (paused==false){
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      

      //puts math on a smaller interval
      var a = map(x, 0, width, minx, maxx);
      var b = map(y, 0, height, miny, maxy);
      
      //number of iterations
      var n = 0;

      //max iterations
      while (n<maxiter) {
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
        
        //increments iteration counter
        n++;
      }
      
      var red=map(n, 0, 50, 0, 250);
      var green=map(n, 0, maxiter, 0, 250);
      var blue=map(n, 0, 50, 0, 250);
      
      //pixel location within a 1d array
      int location= (x + y*width);
      
      //sets pixel color
      pixels[location]=color(red, green, blue);
        }  
      if (mousePressed){
        clicked=true;
      }
       if (clicked){
        break;
      }
      }
  //}
      updatePixels();
    }
    
void keyPressed(){
  if (key == 'p' || key == 'P'){
    paused = !paused;
    if (paused==true){
      println("Paused");
      myX = mouseX;
      myY = mouseY;
    }
    if (paused==false){
      println("Playing");
    }
  }
  //center point in window
  float xCenter = (minx + maxx)/2;
  float yCenter = (miny + maxy)/2;
  
  float rangex = maxx - minx;
  //zoom factor
  float zoomIn = .3*rangex;
  float zoomOut = .9*rangex;
  
  //zoom in
  if (key == '+' || key == '=') {
    minx = xCenter - zoomIn;
    maxx = xCenter + zoomIn;
    miny = yCenter - .8*zoomIn;
    maxy = yCenter + .8*zoomIn;
  }
  //zoom out
  if (key == '_' || key == '-') {
    minx = xCenter - zoomOut;
    maxx = xCenter + zoomOut;
    miny = yCenter - .8*zoomOut;
    maxy = yCenter + .8*zoomOut;
  }
  if (key == 'c' || key == 'C'){
    println("Min X: ", minx, "Max X: ", maxx, "Min Y: ", miny, "Max Y: ", maxy);
  }
}
