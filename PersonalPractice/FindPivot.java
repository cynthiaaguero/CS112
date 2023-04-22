public class FindPivot {
    public static int pivotIndex(int[] nums) {
        int sum = 0;
        int leftsum = 0;
        
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {

            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] array = new int[6];
        array[0] = 1;
        array[1] = 7;
        array[2] = 3;
        array[3] = 6;
        array[4] = 5;
        array[5] = 6;

        System.out.println(pivotIndex(array));
    }
}