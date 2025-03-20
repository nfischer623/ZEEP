void addColor(int n, int location){
  
  //grayscale color scheme if no given colors
  if (colorPicks.length == 0) {
    myColor = color(map(n, 0, maxiter, 0, 250));
    
  //one color given
  }else if (colorPicks.length == 1){
    myColor = colorPicks[0];
    myColor = color(hue(myColor), saturation(myColor), map(n, 0, maxiter, 0, 255));
    
  //multiple colors given
  }else{
    int numColors = colorPicks.length;
    for (int i=0; i<numColors; i++){
      //divides number of iterations into groups based on number of colors
      if (n >= (i*maxiter)/numColors && n <= ((i+1)*maxiter)/numColors) {
        myColor=colorPicks[i];
      }
    }
  }
  if (filterOn){ 
    //https://stackoverflow.com/questions/369438/smooth-spectrum-for-mandelbrot-set-rendering
    myColor = color(.95*hue(myColor) + 10*nsmooth, .6*saturation(myColor), brightness(myColor));
  }
    
  //sets pixel color
  pixels[location]=color(myColor);
}

void rootColor(float a, float b, int location){
  //find closest root
  if ((distance(a, b, root1_real, root1_i) <= distance(a, b, root2_real, root2_i))
  && (distance(a, b, root1_real, root1_i) <= distance(a, b, root3_real, root3_i))){
    myColor = colorPicks[0];
  }else
  
  if ((distance(a, b, root2_real, root2_i) < distance(a, b, root1_real, root1_i))
  && (distance(a, b, root2_real, root2_i) <= distance(a, b, root3_real, root3_i))){
    myColor = colorPicks[1];
  }else{
    myColor = colorPicks[2];
  }
  pixels[location] = myColor;
}
