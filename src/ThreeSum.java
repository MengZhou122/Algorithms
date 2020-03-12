import java.util.*;

/**
 * File Name: ThreeSum.java 
 * ThreeSum  class
 * 
 * To Compile: IntUtil.java RandomInt.java Tuple.java ThreeSumBase.java ThreeSum.java 
 * 
 * @author Jagadeesh Vasudevamurthy
 * @year 2019
 */

class ThreeSum extends ThreeSumBase{
	ThreeSum() {
		//NOTHING CAN BE CHANGED HERE
		testBench();
	}

	@Override
	protected String inputFileBase() {
		//Where is the input file?
		//Change this to your location
		return "/Users/meng/Desktop/NEU-Courses/6205Algorithm/HW2/3sum/";
	}

	@Override
	protected List<List<Integer>> threeSum(int[] nums, int n, int method) {
		//NOTHING CAN BE CHANGED HERE
		if (method == 1) {
			return N3(nums,n) ;
		}
		if (method == 2) {
			return N2TimeNSpace(nums,n) ;
		}
		if (method == 3) {
			return N2Time(nums,n) ;
		}
		return null ;
	}


	/*
	 * Time complexity O(N^3)
	 * Space complexity O(1)
	 */
	private List<List<Integer>> N3(int[] nums, int n) {
		//WRITE CODE
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		//use three for loops tp check the 3-number combo, compare with target number n
		for(int i = 0; i < nums.length - 2; i ++){
			for (int j = i + 1; j < nums.length - 1; j++){
				for (int k = j + 1; k < nums.length; k++){
					if (nums[i] + nums[j] == n - nums[k] ){ //if equal, add the numbers into list and sort them
						if (nums[i] > n && nums[j] > n && nums[k] > n) {
							continue;
						} else if (nums[i] < n && nums[j] < n && nums[k] < n) {
							continue;
						}
						List<Integer> newCombo = new ArrayList<Integer>();
						newCombo.add(nums[i]);
						newCombo.add(nums[j]);
						newCombo.add(nums[k]);
						Collections.sort(newCombo);
						if (!result.contains( newCombo )){ //check the combo is exist or not, if not add in
							result.add( newCombo );
						}
					}
				}
			}
		}
		return result;
	}


	/*
	 * Time complexity O(N^2)
	 * Space complexity O(N)
	 */
	private List<List<Integer>> N2TimeNSpace(int[] nums, int n) {
		//WRITE CODE
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		/* in the 2nd layer loop, we can consider this problem as a 2sum,
		use a hash map to store the value and its complement, if the complement appears, add in newCombo*/
		for(int i = 0; i < nums.length - 2; i ++){
			HashMap<Integer,Integer> complements = new HashMap<>(  );
			for (int j = i + 1; j < nums.length; j++){
				if( !complements.containsKey(nums[j]) ){ //if current number's complement is not in the hashmap
					complements.put( n - nums[i] - nums[j], nums[j]); //add the
				} else { //if equal, add the numbers into list and sort them
					List<Integer> newCombo = new ArrayList<Integer>();
					newCombo.add(nums[i]);
					newCombo.add(nums[j]);
					newCombo.add(complements.get(nums[j]));
					Collections.sort(newCombo);
					if (newCombo.get(0) > n && newCombo.get(1) > n &&  newCombo.get(2) > n) {
						continue;
					} else if(newCombo.get(0) < n && newCombo.get(1) < n &&  newCombo.get(2) < n) {
						continue;
					}
					if (!result.contains( newCombo )){ //check the combo is exist or not, if not add in
						result.add( newCombo );
					}
				}
			}
		}

		return result;
	}

	/*
	 * Time complexity O(N^2)
	 * Space complexity O(1)
	 */
	private List<List<Integer>> N2Time(int[] nums, int n) {
		//WRITE CODE
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		/* sort the integer array, and run through indices of all possible first element of triplet combo.
		use two pointers sweep the sorted array to approach target*/
		Arrays.sort( nums );
		for (int i = 0; i < nums.length - 2; i++){
			if (i > 0 && nums[i] == nums[i - 1]) { // skip same value
				continue;
			}
			if (nums[i] > n){
				break;
			}
			int left = i+1;
			int right = nums.length -1;
			Long target = Long.valueOf(n) - nums[i];
			while (left < right){
				if (Long.valueOf(nums[left]) + nums[right] == target){
					List<Integer> newCombo = new ArrayList<Integer>();
					newCombo.add(nums[i]);
					newCombo.add(nums[left]);
					newCombo.add(nums[right]);
					result.add( newCombo );
					while (left < right && nums[left] == nums[left+1]){ //skip same value
						left++;
					}
					while (left < right && nums[right] == nums[right-1]){
						right--;
					}
					right--;
					left++;
				} else if (Long.valueOf(nums[left]) + nums[right] > target) {
					right--;
				} else {
					left++;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("ThreeSum.java STARTS");
		String version = System.getProperty("java.version");
		System.out.println("Java version used for this program is " + version);
		ThreeSum p = new ThreeSum() ;
		System.out.println("ThreeSum.java ENDS");
	}
}
