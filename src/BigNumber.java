import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.sound.midi.Soundbank;
import java.awt.print.Book;

/**
 * File Name: BigNumber.java 
 * Infinite capacity Unsigned Number
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */
/*
 * To compile you require: CharArray.java, IntUtil.java RandomInt.java Cstring.java BigNumber.java 
 */

class BigNumber {
  private Cstring d; //data
  static IntUtil u = new IntUtil();
  //YOU CANNOT add any data members
  //YOU CAN add any public or private function so that all the tests will pass

  public BigNumber() { this.d = new Cstring( 10 );}

  public BigNumber(int i) {
    this.d = new Cstring( String.valueOf( i ) );
  }

  public BigNumber(char c){
    this.d = new Cstring( String.valueOf( c ) );
  }

  public BigNumber(String s){
    this.d = new Cstring( s );
  }

  public int size() {
    return this.d.size();
  }

  public void pLn(String s) {
    this.d.pLn( s );
  }

  public BigNumber clone() {
    BigNumber res = new BigNumber();
    res.d = this.d.clone();
    res.d.pLn( "resd = ");
    return res;
  }

  public boolean isEqual(BigNumber in) {
    return this.d.isEqual( in.d );
  }

  public boolean isEqual(int i) {
    BigNumber in = new BigNumber( i );
    return this.isEqual( in );
  }

  public boolean isEqual(String s) {
    BigNumber in = new BigNumber( s );
    return this.isEqual( in );
  }

  public BigNumber add(BigNumber in) {
    if (this.isnn(in)) return this.addnn(in);
    else if (this.d.get( 0 ) == '-') return in.sub(this);
    else if (in.d.get( 0 ) == '-') return this.sub(in);
    return this.addpp(in);
  }

  public BigNumber addpp(BigNumber in) {
    int s1 = this.size();
    int s2 = in.size();
    if (s1 < s2) return in.add(this);

    BigNumber res = new BigNumber( );

    this.d.reverse();
    in.d.reverse();

    int ca = 0;
    for (int i = 0; i < s2; i++) {
      int a = this.d.get( i ) - '0';
      int b = in.d.get( i ) - '0';
      res.d.append((a + b + ca) % 10);
      ca = (a + b + ca) / 10;
    }

    for (int j = s2; j < s1; j++) {
      int a = this.d.get( j ) - '0';
      res.d.append((a + ca) % 10);
      ca = (a + ca) / 10;
    }

    if (ca == 1) res.d.append( 1 );
    res.d.reverse();
    this.d.reverse();
    in.d.reverse();

    return res;
  }

  public BigNumber addnn(BigNumber in) {
    BigNumber pthis = new BigNumber(  );
    pthis.d = this.d.cloneToP();
    pthis.pLn( "pthis " );

    BigNumber pin = new BigNumber(  );
    pin.d = in.d.cloneToP();

    BigNumber res = pthis.addpp( pin );
    res.d.reverse();
    res.d.append( "-" );
    res.d.reverse();

    return res;
  }

  public boolean ispp(BigNumber in) {
    if (this.d.get( 0 ) != '-' && in.d.get( 0 ) != '-') return true;
    else return false;
  }

  public boolean isnn(BigNumber in) {
    if (this.d.get( 0 ) == '-' && in.d.get( 0 ) == '-') return true;
    else return false;
  }

  public boolean ispn(BigNumber in) {
    if (this.d.get( 0 ) != '-' && in.d.get( 0 ) == '-') return true;
    else return false;
  }

  public boolean isnp(BigNumber in) {
    if (this.d.get( 0 ) == '-' && in.d.get( 0 ) != '-') return true;
    else return false;
  }

  public boolean lessThan(BigNumber in) {
    if (this.isnp( in )) return true;
    else if (this.ispn( in )) return false;
    else if (this.ispp( in )) return this.ppLessThan( in );
    else return this.nnLessThan(in);
  }

  public boolean ppLessThan(BigNumber in) {
    if (this.size() < in.size()) return true;
    else if (this.size() > in.size()) return false;
    for (int i = 0; i < this.size(); i++) {
      if(this.d.get( i ) == in.d.get( i )) continue;
      else return this.d.get( i ) < in.d.get( i );
    }
    return false;
  }

  public boolean nnLessThan(BigNumber in) {
    if (this.size() > in.size()) return true;
    else if (this.size() < in.size()) return false;
    for (int i = 1; i < this.size(); i++) {
      if(this.d.get( i ) == in.d.get( i )) continue;
      else return this.d.get( i ) < in.d.get( i );
    }
    return false;
  }

  public BigNumber sub(BigNumber in) {
    if (this.isEqual( in )) return new BigNumber( 0 );
    else if (this.lessThan( in )) {
      BigNumber res = in.sub(this);

      res.d.reverse();
      res.d.append( '-' );
      res.d.reverse();
      return res;
    }

    int s1 = this.size();
    int s2 = in.size();
    if (s1 < s2) return in.add(this);

    Cstring res1 = new Cstring( );

    this.d.reverse();
    in.d.reverse();

    int ca = 0;
    for (int i = 0; i < s2; i++) {
      int a = this.d.get( i ) - '0';
      int b = in.d.get( i ) - '0';

      if (a >= b + ca) {
        res1.append(a - b - ca);
        ca = 0;
      } else {
        res1.append(10 + a - b - ca);
        ca = 1;
      }
    }

//    if (ca == 0) {
//      res1.append(this.d.get( s2 ));
//    } else {
    for (int j = s2; j < s1; j++) {
      int a = this.d.get( j ) - '0';
      if (a >= ca) {
        res1.append(a - ca);
        ca = 0;
      } else {
        res1.append(10 + a - ca);
        ca = 1;
      }
    }

    res1.reverse();
    this.d.reverse();
    in.d.reverse();

    BigNumber res = new BigNumber();
    int l = res1.size();
    int i = 0;
    while (i < l) {
      if (res1.get( i ) != '0') break;
      i++;
    }
    while (i < l) {
      res.d.append( res1.get( i++ ) );
    }

    return res;
  }

  public BigNumber mult(BigNumber in) {
    if ((this.size() == 1 && this.d.get( 0 ) == '0') ||(in.size() == 1 && in.d.get( 0 ) == '0')) { return new BigNumber( 0 ); }
    if ((this.size() == 1 && this.d.get( 0 ) == '1')) { return in; }
    if ((in.size() == 1 && in.d.get( 0 ) == '1')) { return this; }

    int s2 = in.size();

    BigNumber res = new BigNumber( );

    for (int i = 0; i < s2; i++) {
      int digi = in.d.get( i ) - '0';
      BigNumber temp = this.mult(  digi );
      res = res.add( temp );
      if (i < s2 - 1 )res.d.append( 0 );
    }

    in.d.reverse();

    return res;
  }

  public BigNumber mult(int in) {
      if (in == 0) return new BigNumber( 0 );
      if (in == 1) return this;

      int l = this.size();
      this.d.reverse();
      BigNumber res = new BigNumber();
      int r = 0;

      for (int i = 0; i < l; i++) {
        int a = this.d.get( i ) - '0';
        res.d.append((a * in + r) % 10);
        r = (a * in + r) / 10;
      }
      if (r > 0) res.d.append( r );

      this.d.reverse();
      res.d.reverse();

      return res;
  }

//  public static BigNumber factorial(int i) {
//    if (i <= 2) return new BigNumber(i);
//
//    else if (i <= 10) {
//      int res = 1;
//      while (i > 1) {
//        res *= i;
//        i--;
//      }
//      return  new BigNumber( res );
//    } else {
//      BigNumber res = factorial( 10 );
//      while (i > 10) {
//        res = res.mult( new BigNumber( i ) );
//        i--;
//      }
//      return res;
//    }
  public static BigNumber factorial(int i) {
    if (i <= 2) return new BigNumber(i);
    else if (i <= 10) {
      int res = 1;
      while (i > 1) {
        res *= i;
        i--;
      }
      return  new BigNumber( res );
    } else {
      BigNumber res = new BigNumber( i );
      for (int j = 1; j <= i /2; j++) {
        if(j == i / 2 && i % 2 == 0) {
          res = res.mult( new BigNumber( j ) );
          break;
        }
        res = res.mult( new BigNumber( j * (i - j)));
      }
      return res;
    }
  }

  private static void testBench() {
    System.out.println("------------test1---------------------");
//    test1();
  }
  
  public static void main(String[] args) {
  	String version = System.getProperty("java.version");
		System.out.println("Java version used for this program is " + version);
    System.out.println("BigNumber.java starts");
    testBench();
    System.out.println("BigNumber.java ends");
  } 
}