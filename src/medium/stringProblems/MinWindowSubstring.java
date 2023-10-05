package medium.stringProblems;

import java.util.*; 
import java.io.*;

class Main {
  static boolean[] isSubLetterCounted;
  
  public static String MinWindowSubstring(String[] strArr) {
    // code goes here  
    String N = strArr[0];
    String K = strArr[1];

    isSubLetterCounted = new boolean[K.length()];
    presetToSubLetterCounter(isSubLetterCounted);  

    int count=0;
    int[][] indexOfFirstAndLast = new int[N.length()][2];
    int[] check = new int[2];

    finish: for(int i=0; i<N.length(); i++){
    	for(int j=0; j<K.length(); j++){
    		if(K.charAt(j)== N.charAt(i)) {
        		  check = subString(N, K, i);
        		  if(check[1]!=-1) {
        			  indexOfFirstAndLast[count] = check;
        			  count++;
            		  break;
        		  }else {
        			  indexOfFirstAndLast[count] = check;
        			  break finish;
        		  }  
        	  }
        }
    }
    
    int[] result= new int[2];
    System.out.println("liste: ");
    for(int i=0; i<indexOfFirstAndLast.length; i++) {
    	
    	System.out.println(indexOfFirstAndLast[i][0]);
    	System.out.println(indexOfFirstAndLast[i][1]);
    	System.out.println();
    }
    System.out.println("smallest:");
    
    result = findSmallestSubStringIndex(indexOfFirstAndLast);
    System.out.println("sonuc: " + result[0] + " " + result[1]);
    String res = "";
    for(int i=result[0]; i<result[1]+1; i++) {
    	res += N.charAt(i);
    }
    System.out.println(res);
    return  res;
  }

  private static int[] findSmallestSubStringIndex(int[][] indexOfFirstAndLast) {
	  int smallestDifference=indexOfFirstAndLast[0][1]-indexOfFirstAndLast[0][0];
	  int temp;
	  int[]	 indexOfSmallestBetween = new int[2];
	  int i=0;
	  
	  while(indexOfFirstAndLast[i][1]!=-1) {
		  temp = indexOfFirstAndLast[i][1]-indexOfFirstAndLast[i][0];
		  if(temp <= smallestDifference) {
			  indexOfSmallestBetween[0] = indexOfFirstAndLast[i][0];
			  indexOfSmallestBetween[1] = indexOfFirstAndLast[i][1];
			  
			  smallestDifference = temp;
		  }
		  i++;
	  }
	  
	  return indexOfSmallestBetween;

  }

public static int[] subString(String N, String K, int startIndex){
	  int counter=0;
	  int[] result = new int[2];
	  result[0] = startIndex;
	  
	  for(int i=startIndex; i<N.length(); i++) {		 
		  for(int j=0; j<K.length(); j++) {
			  if(!isSubLetterCounted[j] && (N.charAt(i) == K.charAt(j))) {
				  isSubLetterCounted[j]=true;				  
				  counter++;
				  if(counter == isSubLetterCounted.length) {					  
					  result[1] = i;
					  presetToSubLetterCounter(isSubLetterCounted);
					  return result;
				  }	
				  break;
			  }
		  } 
	  }	  
	  // if cant find subString return the last index -1 so client understand that there is no last index. 
	  result[1] = -1; 
	  return result;

  }
 
  public static void presetToSubLetterCounter(boolean[] b){
    for(int i=0; i<b.length; i++){
      b[i]= false;
    }
  }

  public static void main (String[] args) {  
    // keep this function call here    
	 String[] test = {"ahffaksfajeeubsne", "jefaa"};
	 System.out.println(test[0]);
	 System.out.println(test[1]);
	 System.out.println();
    //Scanner s = new Scanner(System.in);
    MinWindowSubstring(test);
    //System.out.print(MinWindowSubstring(s.nextLine())); 
  }

}