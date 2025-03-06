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
  if (key == 'f' || key == 'F'){
    filterOn = !filterOn;
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
