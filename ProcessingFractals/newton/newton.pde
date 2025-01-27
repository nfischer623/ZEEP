void setup() {
  size(1600, 1200);
}

color[] colorPicks = {#006699, #ae82fa, #f261b1};

//max iterations
int maxiter=100;

//roots for x^3 + 1
float root1_real= -1;
float root1_imag= 0;

float root2_real= 1/2;
float root2_imag= pow(3,1/2)/2;

float root3_real= 1/2;
float root3_imag= -(pow(3,1/2)/2);

void draw() {
  loadPixels();
  
  //for each pixel
  for (int x = 0; x < width; x++) {
    for (int y = 0; y < height; y++) {
      
      //puts math on a smaller interval
      //a is real
      var a = map(x, 0, width, -2, 2);
      //b is imaginary
      var b = map(y, 0, height, -2, 2);
      
      var n = 0;
      while (n < maxiter) {
        //x1=x0-f(x0)/f'(x0)
        //x1=x0-(x^3+1)/(3x^2)
        
        
        a = a-(a*a*a + 1)/(3*(a*a));
        b = b-(b*b*b + 1)/(3*(b*b));
        
        //increments iteration counter
        n++;
      }


      


      color rootColor = (#000000);
      
      float disfromr1=pow(((a - root1_real)*(a - root1_real) + (b-root1_imag)*(b-root1_imag)),(1/2));
      float disfromr2=pow(((a - root2_real)*(a - root2_real) + (b-root2_imag)*(b-root2_imag)),(1/2));
      float disfromr3=pow(((a - root3_real)*(a - root3_real) + (b-root3_imag)*(b-root3_imag)),(1/2));
      

      if (disfromr1<disfromr2 && disfromr1<disfromr3){
        rootColor= colorPicks[0];}
        
      else if (disfromr2<disfromr1 && disfromr2<disfromr3){
        rootColor= colorPicks[1];}
        
      else if (disfromr3<=disfromr1 && disfromr3<=disfromr2){
        rootColor= colorPicks[2];}
      
      //pixel location within a 1d array
      int location= (x + y*width);
      //sets pixel color
      pixels[location]=color(rootColor);
        }       

      }
      updatePixels();
    }
