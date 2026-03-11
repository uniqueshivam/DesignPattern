package DSAPractise;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int counter = 0;
        List<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals) {
            if (interval[1] < newInterval[0]) {
                ans.add(interval);
                counter++;
            } else {
                if(interval[0]>newInterval[1]) {
                    counter--;
                    continue;
                }
                newInterval[0] = Math.min(interval[0], newInterval[0]);
                newInterval[1] = Math.max(interval[1], newInterval[1]);
                break;
            }
        }
        if(counter == 0) {
            counter = -1;
        }

        for(int i = counter+1;i<intervals.length;i++) {
            if(intervals[i][0]<=newInterval[1]) {
                newInterval[1] = Math.max(intervals[i][1],newInterval[1]);
            } else {
                ans.add(newInterval);
                newInterval = intervals[i];
            }
        }
        ans.add(newInterval);
        return ans.toArray(new int[ans.size()][]);
    }
}
