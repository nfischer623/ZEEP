color myColor = color(0);

float root1_real = 1;
float root1_i = 0;

float root2_real = -.5;
float root2_i = sqrt(3)/2;

float root3_real = -.5;
float root3_i = -sqrt(3)/2;

int max_iter = 5;

void setup(){
  size(700, 700);
}

void draw(){
  loadPixels();
  
  for (int x=0; x < width; x++){
    for (int y=0; y < height; y++){
      
      float a = map(x, 0, width, -2, 2);
      float b = map(y, 0, height, -2, 2);
      
      myColor = color(255);
      int n = 0;
      
      while (n < max_iter){
        float denominator = 3*(a*a*a*a + 2*a*a*b*b + b*b*b*b);
        a = a + ((a*a*a*a*a - 4*a*a*a*b*b -a*a + 3*a*b*b*b*b + b*b + 3*a*a*a*a*b - 4*a*a*b*b*b + b*b*b*b*b))/(denominator);
        b = b + (2*a*b*(-3*a*a*b + 3*a*b*b + 1))/(denominator);
        n+=1;
      }
      
      if (distance(a,b,root1_real, root1_i)< distance(a,b,root2_real, root2_i)
      && distance(a,b,root1_real, root1_i)< distance(a,b,root3_real, root3_i)){
        myColor = color(255,0,0);
      }else
      if (distance(a,b,root2_real, root2_i)< distance(a,b,root1_real, root1_i)
      && distance(a,b,root2_real, root2_i)< distance(a,b,root3_real, root3_i)){
        myColor = color(0,0,255);
      }else
      if(distance(a,b,root3_real, root3_i)< distance(a,b,root1_real, root1_i)
      && distance(a,b,root3_real, root3_i)< distance(a,b,root2_real, root2_i)){
        myColor = color(0,255,0);
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
  float dis = sqrt((a - rootreal)*(a - rootreal) + (b - rooti)*(b - rooti));
  //println(dis);
  return dis;
}
