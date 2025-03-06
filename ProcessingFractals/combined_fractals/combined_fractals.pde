//graph size
float minX = -2;
float maxX = 2;
float minY = -1.5;
float maxY = 1.5;

//max iterations
int maxiter = 100;

//user inputs:

//palette: #006699, #ae82fa, #f261b1, #ffb433
color[] colorPicks = {#006699, #ae82fa, #f261b1, #ffb433};
//varieties: "mandelbrot", "julia", "newton"
String variety = "julia";

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
      }
      
      //pixel location within a 1d array
      int location = (x + y*width);
      
      //grayscale color scheme if no given colors
      if (colorPicks.length <= 1) {
        var grayscale=map(n, 0, 100, 0, 250);
        //sets pixel color
        pixels[location]=color(grayscale);
      }
           
      //color scheme given user's picks
      if (colorPicks.length > 1) {
        addColor(n, location);
      }

        }  
      }
      updatePixels();
    }

void keyPressed(){
  //center point in window
  float xCenter = (minX + maxX)/2;
  float yCenter = (minY + maxY)/2;
  
  float rangex = maxX - minX;
  //zoom factor
  float zoomIn = .3 * rangex;
  float zoomOut = .9 * rangex;
  
  //zoom in
  if (key == '+' || key == '=') {
    minX = xCenter - zoomIn;
    maxX = xCenter + zoomIn;
    minY = yCenter - .8*zoomIn;
    maxY = yCenter + .8*zoomIn;
  }
  //zoom out
  if (key == '_' || key == '-') {
    minX = xCenter - zoomOut;
    maxX = xCenter + zoomOut;
    minY = yCenter - .8*zoomOut;
    maxY = yCenter + .8*zoomOut;
  }
  //save as image
  if (key == 's' || key == 'S'){
    saveImage();
  }
  //save as .zeep
  if (key == 'z' || key == 'Z'){
    saveZeep();
  }  
  //load .zeep
  if (key == 'l' || key == 'L'){
    loadZeep();
  }
  if (key == 'p' || key == 'P'){
    juliaPaused = !juliaPaused;
    if (juliaPaused==true){
      juliaX = mouseX;
      juliaY = mouseY;
    }
  }
}

void mouseDragged(){
  //rate of change of movement based on scale of graph
  float delta = .0025 * abs(maxX - minX);
  
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
