public class Doops {
    public static boolean containsDuplicate(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++){
            temp = nums[i];
            for (int j = 1; j < nums.length; j++){
                if (temp == nums[j]){
                    return false;
                }
            }
        }
        return false; 
    }
    public static void main (String[] args){
        int[] nums = {1,2,3,1};
        System.out.println(containsDuplicate(nums));
    }
}
