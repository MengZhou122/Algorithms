/**
 * Created by IntelliJ IDEA
 *
 * @author Qiang Ma
 * @date 1/30/20 - 6:15 PM
 */
class Search12D extends Search12DBase{

    Search12D() {
        //NOTHING CAN BE CHANGED HERE
        testBench();
    }

    @Override
    protected boolean search12D(int t) {
        //NOTHING CAN BE CHANGED HERE
        return alg(t) ;
    }

    private boolean alg(int t) {
        //WRITE CODE
       /* Search12D a = new Search12D();
        int matrix = a.get(, )*/
//
//        if(matrix == null || matrix.length == 0){
//            return false;
//        }
//
//        if(matrix[0] == null || matrix[0].length == 0){
//            return false;
//        }

        int row = r();
        int column = c();

        int start = 0, end = row * column - 1;
        while(start <= end){
            int mid = start + (end - start) / 2;
            int number = get(mid / column, mid % column);
            if(number == t){
                return true;
            }else if(number > t){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }

        return false;
    }


    public static void main(String[] args) {
        //NOTHING CAN BE CHANGED HERE
        System.out.println("Search12D problem STARTS");
        Search12D m = new Search12D() ;
        System.out.println("All Search12D tests passed. You are genius");
        System.out.println("Search12D problem ENDS");
    }
}
