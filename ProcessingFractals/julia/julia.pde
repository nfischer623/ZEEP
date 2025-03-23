//graph size
float minX = -2;
float maxX = 2;
float minY = -1.5;
float maxY = 1.5;

//max iterations
int maxiter = 100;

//user input:
//Ellie's palette: #006699, #ae82fa, #f261b1, #ffb433
color[] colorPicks = {};
String variety = "julia";

color myColor = 0;

//funky filter
boolean filterOn = false;
float nsmooth;

void setup(){
  //4:3 ratio
  size(1400, 1050);
  //hue saturation brightness
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
        
      //Julia set
      n = myJulia(a, b, n);
           
      //pixel location within a 1d array
      int location = (x + y*width);
      addColor(n, location);
      }  
    }
    updatePixels();
}

//julia set variables
boolean juliaPaused = false;
float juliaX;
float juliaY;

int myJulia(float a, float b, int n){
  /*
  Function to render Julia set fractals
  Takes in complex number a+bi and max number of iterations n
  Returns how many iterations were completed, n
  */
  float realc;
  float imagc;
  
  if (juliaPaused==false){
    //set Julia constant based on mouse
    realc= map(mouseX, 0, width, -1, 1);
    imagc= map(mouseY, 0, height, -.75, .75);
  }else{
    realc= map(juliaX, 0, width, -1, 1);
    imagc=map(juliaY, 0, height, -.75, .75);
  }
  
  while (n < maxiter) {
    //(a+bi)^2 = a^2 + 2abi - b^2
    // real component
    var real= a*a - b*b;
    //imaginary component
    var imag = 2*a*b;
    
    //next iteration components
    a=real + realc;
    b=imag + imagc;
    
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
