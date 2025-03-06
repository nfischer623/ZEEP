//julia set variables
boolean juliaPaused = false;
float juliaX;
float juliaY;

int julia(float a, float b, int n){
  /*Function to render Julia set fractals
  Takes in complex number a+bi, max number of iterations, n
  Returns how many iterations were completed, n
  */
  float realc;
  float imagc;
  
  if (juliaPaused==false){
    //set Julia constant based on mouse
    realc= map(mouseX, 0, width, -1, 1);
    imagc= map(mouseY, 0, height, -1, 1);
  }else{
    realc= map(juliaX, 0, width, -1, 1);
    imagc=map(juliaY, 0, height, -1, 1);
  }
  
  while (n < maxiter) {
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
  return n;
}
