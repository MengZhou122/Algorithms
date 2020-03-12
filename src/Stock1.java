/**
 * File Name: Stock1.java 
 * Stock1 concrete class
 * 
 * 
 * To Compile: IntUtil.java RandomInt.java Stock1.java Stock1Base.java
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class Stock1 extends Stock1Base{
	//You can have any number of private data members here
	//You can have any number of private functions here
	Stock1() {
		//NOTHING CAN BE CHANGED HERE
		testBench();
	}
	
	@Override
	void NSquareTimeConstantSpace() {
		//NOTHING CAN BE CHANGED HERE
    nsquareTimeConstantSpace() ;
	}
	
	@Override
  void NlognTimeLognSpace() {
		//NOTHING CAN BE CHANGED HERE
    nlognTimelognSpace() ;
	}
	
	@Override
  void NTimeLognSpace() {
		//NOTHING CAN BE CHANGED HERE
    nTimelognSpace() ;
	}
	
	/*
	 * Time: O(n^2)
	 * Space: THETA(1)
	 * All your routine should match this answer
	 * Nothing can be changed in this routine
	 */
	private void nsquareTimeConstantSpace() {
		int gp = 0 ;
		int l = size() ;
		for (int buy = 0; buy < l-1; ++buy) {
			++numDivide;
			for (int sell = buy + 1; sell < l; ++sell) {
				++numConquer;
				int p = profit(sell,buy);
				if (p >= gp) { //So that I can make profit by keeping stock for less time
					gp = p ;
					buyDay = buy ;
					sellDay = sell ;
				}
			}
		}
	}
	
	/*
	 * Time: O(nlogn)
	 * Space: O(logn)
	 */
	private void nlognTimelognSpace() {
		//WRITE YOUR CODE
		int l = size() ;

		++numDivide;
		sellDay = maxHelper( 0,l -1 );
		++numDivide;
		int buy = minHelper( 0, sellDay );

		buyDay = (price( buy ) <= price( sellDay ))? buy : sellDay;

	}

	// use recursion method to get the buy date with global max price, O(nlogn) time
	private int maxHelper(int le, int rig) {
		if (le == rig){
			return rig;
		}
		++numConquer;
		int mid =  le +(rig-le)/2 ;
		int leftMax = maxHelper(le, mid );
		int rightMax = maxHelper(mid+1, rig);
		return price(leftMax)> price(rightMax) ? leftMax : rightMax;
	}

	// use recursion method to get the buy date with global min price, O(nlogn) time
	private int minHelper(int le, int rig) {
		if (le == rig){
			return le;
		}
		++numConquer;
		int mid =  le +(rig-le)/2 ;
		int leftMin = minHelper(le, mid );
		int rightMin = minHelper(mid+1, rig);
		return price(leftMin) < price(rightMin) ? leftMin : rightMin;
	}
	
	private void nTimelognSpace() {
		//NOT required this time
		int tempbuy = 0;
		int gp = 0; //tracking the value of global profit, if get a larger value, update buyDay, sellDay and gp
		int l = size();
		for (int i = 0; i < l; i++){
			++numDivide;
			++numConquer;
			if (price( i ) <= price( tempbuy )){ // keep tracking the global min (sweeping from 0 to l-1)
				tempbuy = i;
			} else if (profit( i, tempbuy ) >= gp){
				buyDay = tempbuy;
				sellDay = i;
				gp = price(i) - price(buyDay);
			}
		}
	}
	
	public static void main(String[] args) {
		//NOTHING CAN BE CHANGED HERE
		System.out.println("Stock1 problem STARTS");
		Stock1 m = new Stock1() ;
		System.out.println("All Stock1 tests passed. Now you can pass interviews");
		System.out.println("Stock1 problem ENDS");
	}
}