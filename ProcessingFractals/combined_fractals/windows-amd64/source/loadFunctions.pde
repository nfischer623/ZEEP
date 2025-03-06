void saveImage(){
  //creates "unique" file name based on date and time
  String fileName = "myFractal" + String.valueOf(month()) + "_" + String.valueOf(day()) + "_" 
                    + String.valueOf(hour()) + String.valueOf(minute()) + ".png";
  //saves to sketch folder
  save(fileName);
  println("Image saved as " + fileName);
}

void saveZeep(){
  //saves fractal info to a .zeep file
  String [] fracFacts = {variety, str(minX), str(maxX), str(minY), str(maxY)};
  for (int i = 0; i < colorPicks.length; i++){
    String strColor = str(colorPicks[i]);
    fracFacts = append(fracFacts, strColor);
  }
  saveStrings("fractal.zeep", fracFacts);
  println("Fractal saved as 'fractal.zeep'");
}


void loadZeep(){
  //loads .zeep file to regenerate fractal
 String [] loadedFrac = loadStrings("fractal.zeep"); 
 //variety = loadedFrac[0];
 
 minX = float(loadedFrac[1]);
 maxX = float(loadedFrac[2]);
 minY = float(loadedFrac[3]);
 maxY = float(loadedFrac[4]);
 
 /*
 color [] loadColors = {};
 for (int i = 5; i < loadedFrac.length-5; i++){
   loadColors = append(loadColors, unhex(loadedFrac[i]));
 }
 colorPicks = loadColors;
 */
 println("'fractal.zeep' loaded");
}
