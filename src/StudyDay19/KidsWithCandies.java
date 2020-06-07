package StudyDay19;

import java.util.ArrayList;
import java.util.List;

public class KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        List<Boolean> res = new ArrayList<>();
        for (int candy : candies) {
            if (candy > max){
                max = candy;
            }
        }
        for (int i = 0; i < candies.length; i++) {
            if (candies[i] + extraCandies >= max){
                res.add(true);
            }else {
                res.add(false);
            }
        }
        return res;
    }
}
