import java.util.Arrays;
class Isomorphic {
    public static boolean isIsomorphic(String s, String t) {
        
        int[] tBuddy = new int[256]; //holds all possible chars 
        int[] sBuddy = new int[256]; //holds all possible chars

        Arrays.fill(sBuddy, -1); //fills all empty matches
        Arrays.fill(tBuddy, -1); //fills all empty matches

        for (int i = 0; i < s.length(); i++){ //for all the letters in s, 
            char soup = s.charAt(i);
            char tale = t.charAt(i);

            //case 1: no mapping in either
            if (sBuddy[soup] == -1 && tBuddy[tale] == -1){ //if both are empty matches
                sBuddy[soup] = tale; //make each other a match
                tBuddy[tale] = soup; //make each other a match
            }
            //case 2: mapping doesn't exist/mapping doesn't match
            else if (sBuddy[soup] != tale && tBuddy[tale] != soup){ //if there is a match but they dont match each other
                return false; 
            }
        }
        return true; 
    }

    public static void main (String[] args){
        String S = "baba";
        String T = "dada";
        System.out.println(isIsomorphic(S, T));
        
        S = "screw";
        T = "scold";
        System.out.println(isIsomorphic(S, T));

        S = "toy";
        T = "boy";
        System.out.println(isIsomorphic(S, T));

        S = "cynthia";
        T = "haiderr";
        System.out.println(isIsomorphic(S, T));


    }
}