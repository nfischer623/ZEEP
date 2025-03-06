void addColor(int n, int location){
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
