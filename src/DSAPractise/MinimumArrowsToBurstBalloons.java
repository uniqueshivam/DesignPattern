package DSAPractise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumArrowsToBurstBalloons {
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points,(a,b)-> {
            return Integer.compare(a[1],b[1]);
        });
        int count = 1;
        int[] current = points[0];
        for(int i  = 1;i<points.length;i++) {
            if(points[i][0]<=current[1]) {
                current[0] = points[i][0];
                current[1] = Math.min(current[1],points[i][1]);
            } else {
                count++;
                current = points[i];
            }
        }


        return count;
    }
}
