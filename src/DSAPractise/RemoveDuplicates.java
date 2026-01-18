package DSAPractise;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();

        for(Integer i : nums) {
            if (map.containsKey(i)) {
                int val = map.get(i);
                map.put(i,val+1);
            } else  {
                map.put(i,1);
            }
        }

        int arrayPointer = 0;
        int pointer = 0;
        int counter = 0;
        while(pointer<nums.length) {
            int count = map.get(nums[pointer]);
            counter++;
            nums[arrayPointer] = nums[pointer];
            arrayPointer++;
            if(count>=2) {
                nums[arrayPointer] = nums[pointer];
                arrayPointer++;
                counter++;
            }
            pointer = pointer+count;

        }


//        for(Integer i : nums) {
//            System.out.print(i+" ");
//        }
        return counter;
    }

    public int optimised(int[] nums)  {
        int arrayPointer = 0;
        int pointer1 = 0;
        int pointer2 = 0;
        int count = 0;
        int ans = 0;

        while(pointer2<nums.length) {
            if(nums[pointer1] == nums[pointer2]) {
                pointer2++;
                count++;
                continue;
            } else  {
                nums[arrayPointer] = nums[pointer1];
                ans++;
                arrayPointer++;
                if(count>=2) {
                    nums[arrayPointer] = nums[pointer1];
                    arrayPointer++;
                    ans++;
                }
                count = 1;
                pointer1 = pointer2;
            }
            pointer2++;
        }

        nums[arrayPointer] = nums[pointer1];
        ans++;
        if (count >= 2) {
            nums[arrayPointer] = nums[pointer1];
            ans++;
        }


        for(Integer i : nums) {
            System.out.print(i+" ");
        }
        return ans;

    }

}
