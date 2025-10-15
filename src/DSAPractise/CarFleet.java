package DSAPractise;

import java.util.*;

public class CarFleet {
    public int getNumberOfFleet(int target, int[] position, int[] speed) {
        if(position.length == 1){
            return 1;
        }
        List<Data> dataList = new ArrayList<>();
        for(int i = 0;i<position.length;i++) {
            Data data =  new Data(position[i],(double)(target - position[i]) /speed[i]);
            dataList.add(data);
        }

       Collections.sort(dataList, new Comparator<Data>() {
           @Override
           public int compare(Data o1, Data o2) {
               return Integer.compare(o2.getPosition(),o1.getPosition());
           }
       });

        Deque<Double> stack = new ArrayDeque<>();
        for(int i = 0;i<dataList.size();i++) {
            double time = dataList.get(i).getTimeToReach();
            if(stack.isEmpty() || time>stack.peek()) {
                stack.push(time);
            }
        }
        return stack.size();
    }
}

class Data  {
    public int position;
    public double timeToReach;

    public Data(int position, double timeToReach) {
        this.position = position;
        this.timeToReach = timeToReach;
    }

    public int getPosition() {
        return position;
    }

    public double getTimeToReach() {
        return timeToReach;
    }
}
