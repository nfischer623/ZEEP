int mandelbrot (float a, float b, int n){
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
  return n;
}
