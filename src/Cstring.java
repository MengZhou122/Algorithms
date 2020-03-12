import java.util.concurrent.Callable;

/**
 * File Name: Cstring.java 
 * Implements C String
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */
/*
 * To compile you require: CharArray.java Cstring
 */

class Cstring {
    private CharArray d; //Infinite array of char
    static IntUtil u = new IntUtil();

    public Cstring(int l){
        this.d = new CharArray( l );
    }

    public Cstring(){ this(16); }

    public Cstring(char c) { this(String.valueOf( c )); }

    public Cstring(String s) {
        int l = s.length();
        this.d = new CharArray(l + 1);

        for (int i = 0; i < l; i++) this.d.set( i, s.charAt( i ) );
        this.d.set( l + 1, '\0' );
    }

    public void pLn(String s) {
        System.out.print(s);
        int l = this.size();
        for (int i = 0; i < l; i++) System.out.print(this.d.get( i ));
        System.out.println();
    }

    public char get(int i) {
        return this.d.get( i );
    }

    public int size(){
        int res = 0;
        while (this.d.get( res ) != '\0') res++;
        return res;
    }

    public Cstring clone() {
        int s = this.size();
        Cstring res = new Cstring(s + 1);
        for (int i = 0; i < s; i++) res.d.set( i, this.d.get( i ) );
        res.d.set( s, '\0' );
        return res;
    }

    public Cstring cloneToP() {
        int s = this.size();
        Cstring res = new Cstring(s);
        for (int i = 0; i < s; i++) res.d.set( i, this.d.get( i + 1) );
        res.d.set( s, '\0' );
        return res;
    }

    public boolean isEqual(Cstring in) {
        if (this.size() != in.size()) return false;

        int s = this.size();
        for (int i = 0; i < s; i++) {
            if (this.d.get( i ) != in.d.get( i )) return false;
        }
        return true;
    }

    public void reverse() {
        int s = this.size();
        for (int i = 0; i < s/2; i++) {
            this.d.swap(i, s - 1 - i);
        }
    }

    public void append(Cstring in) {
        int s1 = this.size();
        int s2 = in.size();

        for (int i = 0; i < s2; i++) this.d.set( s1 + i, in.d.get( i ) );
        this.d.set( s1 + s2 + 1, '\0' );
    }

    public void append(String in) {
        int s1 = this.size();
        int s2 = in.length();

        for (int i = 0; i < s2; i++) this.d.set( s1 + i, in.charAt( i ) );
        this.d.set( s1 + s2 + 1, '\0' );
    }

    public void append(int i) {
        this.append( String.valueOf( i ) );
    }

    public void append(char c) {
        this.append( String.valueOf( c ) );
    }

    public Cstring add(Cstring in) {
        int s1 = this.size();
        int s2 = in.size();
        Cstring res = new Cstring(s1 + s2 + 1);

        for (int i = 0; i < s1; i++) res.d.set( i, this.d.get( i ) );
        for (int j = 0; j < s2; j++) res.d.set( s1 + j, in.d.get( j ));
        res.d.set( s1 + s2 + 1, '\0' );

        return res;
    }

    public Cstring add(String in) {
        int s1 = this.size();
        int s2 = in.length();
        Cstring res = new Cstring(s1 + s2 + 1);

        for (int i = 0; i < s1; i++) res.d.set( i, this.d.get( i ) );
        for (int j = 0; j < s2; j++) res.d.set( s1 + j, in.charAt( j ));
        res.d.set( s1 + s2 + 1, '\0' );

        return res;
    }

    public Cstring add(int i) {
        return add(String.valueOf( i ));
    }

    private static void testBasic() {
        Cstring a = new Cstring('b');
        a.pLn( "a = " );
        Cstring b = new Cstring( '7' );
        b.pLn( "b = " );
        Cstring c = new Cstring("1234567890123456789012345678901234567890123456789012345678901234567890");
        c.pLn( "c = " );
        Cstring d = c.clone();
        d.pLn( "d = " );
        Cstring e = new Cstring("A quick brown fox jumped over a lazy dog");
        e.pLn( "e = " );
        Cstring f = new Cstring("Gateman sees name garageman sees nametag");
        f.pLn( "f = " );
        f.reverse();
        f.pLn( "f = " );
    }

    private static void testAdd() {
        Cstring a = new Cstring("USCS");
        Cstring b = new Cstring( "Extension" );
        Cstring c = a.add(b);
        a.pLn( "a = " );
        b.pLn( "b = " );
        c.pLn( "c = " );
        Cstring d = c.add( "USA" );
        d.pLn( "d = " );
        a.append( b );
        a.pLn( "a + b = " );
        a.append( "World" );
        a.pLn( "a + b + World = " );
        a.append( 5 );
        a.pLn( "a = " );
    }

    private static void testEqual() {
        Cstring a = new Cstring("1234567890123456789012345678901234567890123456789012345678901234567890");
        a.pLn( "a = " );
        Cstring b = new Cstring("1234567890123456789012345678901234567890123456789012345678901234567890");
        b.pLn( "b = " );
        u.myassert( a.isEqual(b) );
        Cstring c = new Cstring("-123456789012345678901234567890123456789012345678901234567890123456789");
        c.pLn( "c = " );
        u.myassert( a.isEqual(c) == false);
    }

    private static void testBench(){
        testBasic();
        System.out.println();
        testAdd();
        System.out.println();
        testEqual();
    }

    public static void main(String[] args) {
  	String version = System.getProperty("java.version");
		System.out.println("Java version used for this program is " + version);
    System.out.println("Cstring.java starts");
    testBench();
    System.out.println("Cstring.java ends");
  }
  
}