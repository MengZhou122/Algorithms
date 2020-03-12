/**
 * File Name: game/SnakeAndLadderBase.java 
 * Java11
 * To Compile: IntUtil.java RandomInt.java game/SnakeAndLadderBase.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

/*
 * YOU CANNOT CHANGE ANYTHING IN THIS FILE. READ ONLY
 */

abstract class SnakeAndLadderBase{
	protected IntUtil u = new IntUtil(); 
	private String title; //Title of the board
	protected final int NUM_FACE_ON_DICE = 6 ;
	protected int numSquare; //I will give number of square on board
	protected int numGames; // How many times you to play the game
	protected boolean display; //If true print all the games.
	protected int [][] ladders;
	protected int [][] snakes ;
	//Populate the following variables from concrete class
	protected int min; //Minimum number of moves
	protected String mins; //Min solution as String
	protected int max; //Max number of moves
	protected String maxs; //Max solution as String

	//I don't know how to write it
	//Override by the concrete class
	abstract void play() ;

	protected void testBench() {
		long startTime = System.nanoTime();
		basic() ;
		long endTime = System.nanoTime();
		double d2 = u.timeInSec(endTime,startTime) ;
		System.out.println("Run time = " + d2 + " secs" );
	}

	private void init(String t, int n, int[][] l, int [][] s, int times) {
		title = t;
		numSquare = n ;
		ladders  = l ;
		snakes = s ;
		numGames = times ;
		display = true ;
		if (times > 20) {
			display = false ;
		}
		min = 0 ;
		mins = new String() ;
		min = 0 ;
		maxs = new String() ;
	}

	private void printResult() {
		System.out.println("---------"  + title + " --------------------") ;
		System.out.println("Number of squares = " + numSquare) ;
		System.out.println("For " + numGames + " rounds: min = " + min + " max = " + max) ;
		System.out.println("--------------Min game  ----------------") ;
		System.out.println(mins) ;
		System.out.println("--------------max game ----------------") ;
		System.out.println(maxs) ;
	}

	private void one(String t, int n, int[][] l, int [][] s,int times) {
		init(t,n,l,s,times);
		play();
		printResult() ;
	}

	private void basic() {
		{
			int[][] l = {
					{29, 38}, 
					{4, 14},
					{9, 31},
					{21, 42},
					{28,84},
					{36,44},
					{51,67},
					{71,91},
					{80,100},
			};
			int[][] s = {
					{98,78},
					{95,75},
					{93,73},
					{87,24},
					{64,60},
					{62,19},
					{56,53},
					{49,11},
					{16,6},
					{47,26}, 
			};
			one("Board 100",100,l,s,500000) ; //Try first for 10
		}

	}

	public static void main(String[] args) {
		System.out.println("SnakeAndLadderBase.java STARTS");
		String version = System.getProperty("java.version");
		System.out.println("Java version used for this program is " + version);
		System.out.println("You cannot instantiate SnakeAndLadderBase class: SnakeAndLadderBase p = new SnakeAndLadderBase() ; ");
		//SnakeAndLadderBase p = new SnakeAndLadderBase() ;
		System.out.println("SnakeAndLadderBase.java ENDS");
	}
}