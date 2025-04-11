public class Search2DMatrix {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int row = mid / cols, col = mid % cols;
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 7, 11},
            {2, 5, 8, 12},
            {3, 6, 9, 16},
            {10, 13, 14, 17}
        };
        int target = 5;
        System.out.println(searchMatrix(matrix, target));
    }
}
