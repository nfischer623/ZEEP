//graph size
float minX = -2;
float maxX = 2;
float minY = -1.5;
float maxY = 1.5;

//max iterations
int maxiter = 100;

//user input:
//Ellie's palette: #006699, #ae82fa, #f261b1, #ffb433
color[] colorPicks = {#006699, #ae82fa, #f261b1, #ffb433};
String variety = "mandelbrot";

color myColor = 0;

//funky filter
boolean filterOn = false;
float nsmooth;

void setup(){
  size(1400, 1050);
  colorMode(HSB);
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
      int n = 0;
            
      n = myMandelbrot(a, b, n);
           
      //pixel location within a 1d array
      int location = (x + y*width);
      addColor(n, location);

      }  
    }
    updatePixels();
}

int myMandelbrot (float a, float b, int n){
  /*Mandelbrot Fractal function
  Takes in complex number a+bi, max number of iterations, n
  Returns how many iterations were completed, n
  */
  
  //real component
  var start_real = a;
  //imaginary component
  var start_i = b;
  
  //makes faster by setting center of mandelblobs to max iterations
  if (((a + .25)*(a + .25) + .81*b*b <= .25) || ((a+1)*(a+1) + b*b <= .06)){
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
  if (filterOn){
    nsmooth = n + 1 - log(log(abs(a)))/log(2);
  }
  return n;
}
