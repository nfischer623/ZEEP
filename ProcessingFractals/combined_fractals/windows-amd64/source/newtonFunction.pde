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

int newtonCountIterations(float a, float b, int n){
  while (n < maxiter){
      float[] newnum = NewtonsMethod(a,b);
      a = newnum[0];
      b = newnum[1];
      n++;
    if (a*a + b*b > 3) {
      break;
    }
  }
  return n;
}

float distance(float a, float b, float rootreal, float rooti){
  //finds distance between a two points: (a, b) and (rootreal, rooti)
  float dis = sqrt((a - rootreal)*(a - rootreal) + (b - rooti)*(b - rooti));
  return dis;
}
