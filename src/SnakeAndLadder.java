import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

/**
 * File Name: SnakeAndLadder.java 
 * SnakeAndLadder concrete class
 * 
 * 
 * To Compile: IntUtil.java RandomInt.java SnakeAndLadder.java SnakeAndLadderBase.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class SnakeAndLadder extends SnakeAndLadderBase{
	//You can have any number of private data or private functions here
	SnakeAndLadder() {
		//NOTHING CAN BE CHANGED HERE
		testBench();
	}
	
	@Override
  void play() {
		//NOTHING CAN BE CHANGED HERE
		//WRITE YOUE CODE in private procedure alg()
		//YOU CAN HAVE ANY NUMBER OF PRIVATE functions and variables
		alg() ;
	}

	/*-------------------------WTITE CODE BELOW-----------------------------*/
	//YOU CAN HAVE ANY NUMBER OF PRIVATE functions and variables

	private void alg() {

		connections = getConnections( ladders , snakes);

		Random random = new Random(  );

		min = (int)Integer.MAX_VALUE;

		int round = 0;

		while(round < numGames){

			//game begin, set values for coin and dice
			int position = 1;
			int count = 0;

			StringBuffer route = new StringBuffer( "1->" );

			//loop till coin arrive at the destination 100
			while (true) {

				//roll the dice and count the times rolled
				int dice = random.nextInt(NUM_FACE_ON_DICE) + 1;
				count += 1;

				//get value of dice, and then update the position by plus the value of dice
				if (position + dice > 100) {
					position = position;
				} else{
					position = position + dice;
				}

				// check whether the coin is on the head of a ladder or a snake
				if (connections.containsKey( position )) {
						route.append( "(" + position + ")" );
						position = positionAfterLSUpdate( position );
				}

				if (position == 100){
					route.append( "100 Num of dice rolled " + count );
					break;
				}

				route.append( position + "->" );
			}

			if (count < min) {
				min = count;
				mins = route.toString();
			}

			if (count > max) {
				max = count;
				maxs = route.toString();
			}

			if (display){
				System.out.println(route);
			}

			round++;
		}

	}

	private int positionAfterLSUpdate(int position){
		return (int)connections.get( position );
	}

	private HashMap connections;

	private HashMap getConnections(int[][] m1, int[][] m2){
		HashMap connections = new HashMap();
		for (int[] pair: m1) {
			connections.put( pair[0],pair[1] );
		}
		for (int[] pair: m2) {
			connections.put( pair[0],pair[1] );
		}
		return connections;
	}


	public static void main(String[] args) {
		//NOTHING CAN BE CHANGED HERE
		System.out.println("SnakeAndLadder problem STARTS");
		SnakeAndLadder m = new SnakeAndLadder() ;
		System.out.println("SnakeAndLadder problem ENDS");
	}
}