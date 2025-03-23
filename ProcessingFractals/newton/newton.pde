//graph size
float minX = -2;
float maxX = 2;
float minY = -1.5;
float maxY = 1.5;

//max iterations
int maxiter = 20;

//user input:
//Ellie's palette: #006699, #ae82fa, #f261b1, #ffb433
color[] colorPicks = {#006699, #ae82fa, #f261b1, #ffb433};
String variety = "newton";

color myColor = 0;

//funky filter
boolean filterOn = false;
float nsmooth;

//roots for x^3 - 1
// 1 + 0i
float root1_real = 1;
float root1_i = 0;
//-1/2 + (sqrt(3)/2)i
float root2_real = -.5;
float root2_i = sqrt(3)/2;
//-1/2 - (sqrt(3)/2)i
float root3_real = -.5;
float root3_i = -(sqrt(3)/2);

void setup(){
  //4:3 ratio
  size(1400, 1050);
  //hue saturation brightness
  colorMode(HSB);
}

void draw(){
  loadPixels();
  
  //pixel mapping from https://thecodingtrain.com/challenges/21-mandelbrot-set-with-p5js
  //for each pixel
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      
      //puts math on a smaller interval
      var a = map(x, 0, width, minX, maxX);
      var b = map(y, 0, height, minY, maxY);
      
     //number of iterations
      int n = 0;
            
      //run Newton's method n times
      while (n < maxiter){
        float [] newnum = NewtonsMethod(a,b);
        a = newnum[0];
        b = newnum[1];
        if (distance(a, b, root1_real, root1_i) < .1 || distance(a, b, root2_real, root2_i) < .1 || distance(a, b, root3_real, root3_i) < .1){
          break;
        }
        n += 1;
        //funky filter component
        if (filterOn){
          nsmooth = n + 1 - log(log(abs(a)))/log(2);
  }
      }         
      //pixel location within a 1d array
      int location = (x + y*width);
      addColor(n, location);
      //rootColor(a, b, location);
      }  
    }
    updatePixels();
}

float [] NewtonsMethod(float a, float b){
    /*
    Applies Newton's Method to a complex number a + bi
    Newton's Method: x(n+1) = xn - f(xn)/f'(xn)
    But broken down into real and imaginary parts
    */
    float denominator = 3*(a*a*a*a + 2*a*a*b*b + b*b*b*b);
    //real part
    a = a - ((a*a*a*a*a + 2*a*a*a*b*b - a*a + a*b*b*b*b +b*b)/denominator);
    //imaginary part
    b = b - ((a*a*a*a*b+ 2*a*a*b*b*b + 2*a*b + b*b*b*b*b)/denominator);
    float [] new_num = {a, b};
    return new_num;
}

float distance(float a, float b, float rootreal, float rooti){
  //finds square of distance between a two points: (a, b) and (rootreal, rooti)
  float dis = (a - rootreal)*(a - rootreal) + (b - rooti)*(b - rooti);
  return dis;
}
