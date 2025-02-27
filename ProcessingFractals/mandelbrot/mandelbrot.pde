//user's color picks
//palette: #006699, #ae82fa, #f261b1, #ffb433
color[] colorPicks = {#006699, #ae82fa, #f261b1, #ffb433};
  
//window size in pixels
void setup() {
  size(1400, 1050);
}

//graph size
float minx = -2.5;
float maxx = 1.5;
float miny = -1.5;
float maxy = 1.5;

//max iterations
int maxiter = 100;
  
void mouseDragged(){
  //rate of change of movement based on scale of graph
  float delta = .0025 * abs(maxx - minx);
  
  //determines direction mouse is moving in
  if (pmouseX < mouseX) {
    //shifts range that window sees
    minx = minx - delta;
    maxx = maxx - delta;
  }
  if (pmouseX > mouseX) {
    minx = minx + delta;
    maxx = maxx + delta;
  }
    if (pmouseY < mouseY) {
    miny = miny - delta;
    maxy = maxy - delta;
  }
  if (pmouseY > mouseY) {
    miny = miny + delta;
    maxy = maxy + delta;
  }
  
}
  
void keyPressed(){
  //center point in window
  float xCenter = (minx + maxx)/2;
  float yCenter = (miny + maxy)/2;
  
  float rangex = maxx - minx;
  //zoom factor
  float zoomIn = .3 * rangex;
  float zoomOut = .9 * rangex;
  
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


void draw() {
  loadPixels();  

  //https://thecodingtrain.com/challenges/21-mandelbrot-set-with-p5js
  //for each pixel
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {

      //puts math on a smaller interval
      double a = map(x, 0, width, minx, maxx);
      double b = map(y, 0, height, miny, maxy);
      
     //number of iterations
      int n = 0;
      
      //real component
      double origa = a;
      //imaginary component
      double origb = b;
      
      //makes faster by setting center of mandelblobs to max iterations
      if (((a + .25)*(a + .25) + .81*b*b <= .25) || ((a+1)*(a+1) + b*b <= .06)){
        n = maxiter;
      }
      
      while (n < maxiter) {
        //(a+bi)^2 = a^2 + 2abi - b^2
        // real component
        double newreal = a*a - b*b;
        //imaginary component
        double newcomplex = 2*a*b;
        
        //next iteration components
        a = newreal + origa;
        b = newcomplex + origb;
        
        //checks if point is going towards infinity
        if (a*a + b*b > 4) {
          break;
        }
        //increments iteration counter
        n++;
      }
      
      //pixel location within a 1d array
      int location = (x + y*width);
      
      //grayscale color scheme if no given colors
      if (colorPicks.length == 0) {
        var grayscale=map(n, 0, 100, 0, 250);
        //sets pixel color
        pixels[location]=color(grayscale);
      }
           
      //color scheme given user's picks
      if (colorPicks.length > 0) {
        var myColor = 0;
      
        int numColors = colorPicks.length;
        for (int i=0; i<numColors; i++){
          //divides number of iterations into groups based on number of colors
          if (n >= (i*maxiter)/numColors && n <= ((i+1)*maxiter)/numColors) {
            myColor=colorPicks[i];
          }
      }
        //sets pixel color
        pixels[location]=color(myColor);
      }
      
        }  
      }
      updatePixels();
    }
