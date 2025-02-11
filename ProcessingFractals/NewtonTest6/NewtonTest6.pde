//user's color picks
//palette: #EDE048, #8CD89D, #4F6DFC (blue, yellow, green)
color[] colorPicks = {#4F6DFC, #3232AF, #32C896};

float minx = -4;
float maxx = 4;
float miny = -4;
float maxy = 4;

color myColor;
int max_iter = 15;

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
  size(700, 700);
}

void draw(){
  loadPixels();
  
  //for each pixel
  for (int x=0; x < width; x++){
    for (int y=0; y < height; y++){
      
      //map math to smaller interval
      float a = map(x, 0, width, minx, maxx);
      float b = map(y, 0, height, miny, maxy);
      
      myColor = color(255, 255, 255);
      int n = 0;
      
      //run Newton's method n times
      while (n < max_iter){
        float [] newnum = NewtonsMethod(a,b);
        a = newnum[0];
        b = newnum[1];
        n+=1;
      }
      
      //find closest root
      if ((distance(a, b, root1_real, root1_i) <= distance(a, b, root2_real, root2_i))
      && (distance(a, b, root1_real, root1_i) <= distance(a, b, root3_real, root3_i))){
        myColor = colorPicks[0];
      }else
      
      if ((distance(a, b, root2_real, root2_i) < distance(a, b, root1_real, root1_i))
      && (distance(a, b, root2_real, root2_i) <= distance(a, b, root3_real, root3_i))){
        myColor = colorPicks[1];
      }else
      
      if ((distance(a, b, root3_real, root3_i) < distance(a, b, root1_real, root1_i))
      && (distance(a, b, root3_real, root3_i) < distance(a, b, root2_real, root2_i))){
        myColor = colorPicks[2];
      }
      
      int location = (x + y*width);
      pixels[location] = myColor;
      
    //end of for y  
    }
  //end of for x    
  }  
  updatePixels();
//end of void draw()
}

float distance(float a, float b, float rootreal, float rooti){
  //finds distance between a two points: (a, b) and (rootreal, rooti)
  float dis = sqrt((a - rootreal)*(a - rootreal) + (b - rooti)*(b - rooti));
  return dis;
}

float [] NewtonsMethod(float a, float b){
    //applies Newton's Method to a complex number a + bi
    //Newton's Method: x(n+1) = xn - f(xn)/f'(xn)
    //But broken down into real and imaginary parts
    float denominator = 3*(a*a*a*a + 2*a*a*b*b + b*b*b*b);
    a = a - ((a*a*a*a*a + 2*a*a*a*b*b - a*a + a*b*b*b*b +b*b)/denominator);
    b = b - ((a*a*a*a*b+ 2*a*a*b*b*b + 2*a*b + b*b*b*b*b)/denominator);
    float [] new_num = {a, b};
    return new_num;
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
    miny = yCenter - zoomIn;
    maxy = yCenter + zoomIn;
  }
  //zoom out
  if (key == '_' || key == '-') {
    minx = xCenter - zoomOut;
    maxx = xCenter + zoomOut;
    miny = yCenter - zoomOut;
    maxy = yCenter + zoomOut;
  }
  if (key == 'c' || key == 'C'){
    println("Min X: ", minx, "Max X: ", maxx, "Min Y: ", miny, "Max Y: ", maxy);
  }
}

void mouseDragged(){
  //rate of change of movement based on scale of graph
  float delta = .005 * abs(maxx - minx);
  
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
