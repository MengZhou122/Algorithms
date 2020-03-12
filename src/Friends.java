import com.sun.xml.internal.xsom.impl.scd.Iterators;
import sun.awt.SunHints;

import java.security.Key;
import java.sql.SQLOutput;
import java.util.*;

/**
 * File Name: Friends.java 
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 * 
 * To compile: RandomInt.java IntUtil.java Friends.java
 */
public class Friends {
  private int max;
  static IntUtil u = new IntUtil();
  //You can have any number of private variables
  //You can have any number of private functions

  /*
   * Constructor
   */
  Friends(int n) {
    //YOU CANNOT CHANGE ANYTHING IN THIS PROCEDURE
    max = n;
    System.out.println( "The following are friends" );
    long startTime = System.nanoTime();
    alg();
    long endTime = System.nanoTime();
    double d2 = u.timeInSec( endTime, startTime );
    System.out.println( "Run time  n = " + max + " is " + d2 + " secs" );
  }

  private void alg() {
    //WRITE YOUR CODE
    //YOU CAN HAVE ANY NUMBER OF PRIVATE data members and function
    int[] sumOfDivisors = getSums( max );
    printPairs( sumOfDivisors );
  }

  private int[] getSums(int n){
    int[] nums = new int[n+1]; //int array from 0 to n, store and update their sumOfDivisor value, default value is 0

    for (int i = 2; i <= n; i++){ // set all value from 2 to n as 1; keep sumOfDivisors of 0 and 1 as 0
      nums[i] = 1;
    }

    for (int i = 2; i <= n/2; i++){ //from 2 to 2/n, all of these value can be divided
      for (int j = 2; j * i <= n; j++){ //if  a number is a multiple of i, add i to its sumOfDivisor value
          nums[j * i] += i;
      }
    }

    return nums;
  }

  private void printPairs(int[] sums){
    int count = 0;

    for (int i = 2; i < sums.length; i++){
      int goal = sums[i];

      if (goal > i &&  goal < sums.length && sums[goal] == i){
        System.out.println(count + " : " + i + " and " + goal);
        count++;
      }
    }
  }

  private static void test() {
  	//test first for n = 1000
  	//Then test for n =  100000000
    //int n = 1000 ;
    int n =  100000000 ;
    Friends a = new Friends(n) ;
  }
  
  public static void main(String[] args) {
  	//NOTHING CAN BE CHANGED BELOW
    System.out.println("Friends.java starts") ;
    test() ;
    System.out.println("If you can do in less than 20 secs, you will get FULL points") ;
    System.out.println("Attach Friends.java, output of your program as a pdf file") ;
    System.out.println("Attach a word file, explaining why your method is fast") ;
    System.out.println("Friends.java ends") ;
  }
}

