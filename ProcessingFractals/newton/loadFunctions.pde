//Julia constant not needed for newton, but needed to save .zeep
float juliaReal = 0;
float juliaImag = 0;

void saveImage(){
  //creates "unique" file name based on date and time
  String fileName = "myFractal" + String.valueOf(month()) + "_" + String.valueOf(day()) + "_" 
                    + String.valueOf(hour()) + String.valueOf(minute()) + String.valueOf(second())+ ".png";
  //saves to sketch folder
  save(fileName);
  println("Image saved as " + fileName);
}

void saveZeep(){
  //saves fractal info to a .zeep file
  String [] fracFacts = {variety, str(minX), str(maxX), str(minY), str(maxY), str(juliaReal), str(juliaImag)};
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
 
 variety = loadedFrac[0];
 
 minX = float(loadedFrac[1]);
 maxX = float(loadedFrac[2]);
 minY = float(loadedFrac[3]);
 maxY = float(loadedFrac[4]);
 
 juliaReal = float(loadedFrac[5]);
 juliaImag = float(loadedFrac[6]);
 
 color [] loadColors = {};
 for (int i = 7; i < loadedFrac.length; i++){
   loadColors = (color[]) append(loadColors, color(int(loadedFrac[i])));
 }
 colorPicks = loadColors;
 
 println("'fractal.zeep' loaded");
}
