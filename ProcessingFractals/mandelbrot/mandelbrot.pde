void setup() {
  size(1600, 1200);
}

void draw() {
  //https://thecodingtrain.com/challenges/21-mandelbrot-set-with-p5js
  loadPixels();
  
  //max iterations
  var maxiter = 100;
  var min =-2;
  var maxx =2;
  var maxy = 2;
  
  //for each pixel
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {

      //puts math on a smaller interval
      var a = map(x, 0, width, min, 2);
      var b = map(y, 0, height, -2, 2);
      
      var n = 0;
      
      var origa = a;
      var origb= b;
      
      while (n<maxiter) {
        
        //(a+bi)^2 = a^2 + 2abi - b^2
        // real component
        var real= a*a - b*b;
        //imaginary component
        var complex = 2*a*b;
        
        //next iteration components
        a=real +origa;
        b=complex+origb;
        
        //checks if point is going towards infinity
        if (a*a + b*b > 4) {
          break;
        }
        //increments iteration counter
        n++;
      }
      
      var red=0;
      var green=0;
      var blue=0;
      if (n<10) {
        red = 0;
        green=0;
        blue=0;
      }
      if (n>=10 && n<20) {
        red = 250;
        green=0;
        blue=150;
      }
      if (n>=20 && n<30) {
        red = 250;
        green=0;
        blue=250;
      }
      if (n>=30 && n<40) {
        red = 150;
        green=0;
        blue=250;
      }
      if (n>=40 && n<50) {
        red = 0;
        green=0;
        blue=250;
      }      
      if (n>=50 && n<60) {
        red = 0;
        green=250;
        blue=250;
      }
      if (n>=60 && n<70) {
        red = 0;
        green=250;
        blue=0;
      }     
      if (n>=80 && n<90) {
        red = 250;
        green=250;
        blue=0;
      }      
      if (n>=90 && n<=100) {
        red = 250;
        green=150;
        blue=0;        
      }  
      var grayscale=map(n, 0, 100, 0, 250);
      
      //pixel location within a 1d array
      int location= (x + y*width);
      //sets pixel color
      pixels[location]=color(grayscale);
        }  
      }
      updatePixels();
    }
