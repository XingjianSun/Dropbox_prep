public class SharpnessValue {

    public static int findSharpnessValue(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;

        int[] dp = new int[m];
        for(int i = 0; i < m; i++){
            dp[i] = matrix[i][0];
        }

        //space optimized
        //since the update on each column only depends on the prev column

        for(int j=1; j<n; j++) {
            int prev = dp[0];
            for (int i = 0; i < m; i++) {
                //find the max sharpness from the left, upper left, and lower left path
                int maxPath = dp[i];
                if (i > 0) {
                    maxPath = Math.max (maxPath, prev);
                }
                if (i < m - 1) {
                    maxPath = Math.max (maxPath, dp[i + 1]);
                }

                prev = dp[i];
                dp[i] = Math.min (maxPath, matrix[i][j]);
            }
        }

//        int[][] dp = new int[m][n];
//        for(int i = 0; i < m; i++){
//            dp[i][0] = matrix[i][0];
//        }
//        //move from left to right
//        for(int j = 1; j < n; j++){
//            for(int i = 0; i < m; i++){
//
//                //inherit the left value
//                int prev = dp[i][j - 1];
//                //if valid, compare with the left lower element
//                if(i < m - 1){
//                    prev = Math.max(prev, dp[i + 1][j - 1]);
//                }
//
//                //compare with the left upper element
//                if(i > 0){
//                    prev = Math.max(prev, dp[i - 1][j - 1]);
//                }
//
//                dp[i][j] = Math.min(prev, matrix[i][j]);
//
//            }
//        }

//        int res = Integer.MIN_VALUE;
//        for(int i = 0; i < m; i++){
//            res = Math.max(dp[i][n - 1], res);
//        }

        int res = Integer.MIN_VALUE;
        for(int num: dp){
            res = Math.max(num, res);
        }
        return res;
    }




    public static void main(String[] args){
        int[][] matrix = {{5,7,2},{7,5,8},{9,1,5}};

        System.out.println (findSharpnessValue(matrix));
    }

}
