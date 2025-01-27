void setup() {
  size(1600, 1200);
}

void draw() {
  loadPixels();
  
  //f(x)=x^3

  //max iterations
  var maxiter=300;
      
  //https://thecodingtrain.com/challenges/21-mandelbrot-set-with-p5js
  //for each pixel
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      

      //puts math on a smaller interval
      var a = map(x, 0, width, -2, 2);
      var b = map(y, 0, height, -2, 2);
      
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


      
      var red=map(n, 0, maxiter, 0, 250);
      var green=map(n, 0, maxiter, 0, 250);
      var blue=map(n, 0, 100, 0, 250);

      
      //pixel location within a 1d array
      int location= (x + y*width);
      //sets pixel color
      pixels[location]=color(red, green, blue);
        }       

      }
      updatePixels();
    }
