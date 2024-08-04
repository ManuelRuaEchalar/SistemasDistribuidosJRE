public public class AreSame {
	
	public static boolean comp(int[] a, int[] b) {
    for (int num : a){
      int dobles = 0;
      int counter = 0;
      for (int otro : a){
        if (num==otro){
          dobles += 1;
        }
      }
      for (int cuadrado : b){
        if (num*num ==cuadrado){
          counter += 1;
        }
      }
      if (dobles > 1){
        if (counter != dobles){
          return false;
      } else if (counter !=1){
          return false;
      }
    }
    
    }
    for (int num : b) {
      int vuelta=0;
      for (int otro:a){
        if (num == otro * otro){
          vuelta+=1;
        }
      }
      if (vuelta==0){
        return false;
      }
    }
    return true;
  }
} {
    
}
