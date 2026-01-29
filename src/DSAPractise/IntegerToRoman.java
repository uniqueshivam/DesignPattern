package DSAPractise;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {
    public String intToRoman(int num) {
        StringBuilder ans = new StringBuilder();
        Map<Integer,Character> map = new HashMap<>();
        map.put(1,'I');
        map.put(5,'V');
        map.put(10,'X');
        map.put(50,'L');
        map.put(100,'C');
        map.put(500,'D');
        map.put(1000,'M');
        int[] arr = new int[] {1,5,10,50,100,500,1000};
        while(num>0) {
            int q;
            if(num>=1000) {
                q = num/1000;
                ans.append("M".repeat(q));
                num = num%1000;
            } else if(num>=100) {
                q = num/100;
                if(q == 4 || q ==9) {
                   fun(arr,ans,num,map);
                } else  {
                    for(int i = 0;i<arr.length;i++) {
                        if(num>=arr[i]) {
                            continue;
                        }
                        ans.append(map.get(arr[i-1]));
                        int diff = num-arr[i-1];
                        if(diff<100) {
                            num = num-arr[i-1];
                        } else  {
                            ans.append("C".repeat(diff/100));
                        }

                        break;
                    }
                }
                num = num%100;
            } else {
                q = num/10;
                if(q == 4 || q ==9 || num ==4 || num ==9) {
                    for (int j : arr) {
                        if (num > j) {
                            continue;
                        }
                        if (num > 10) {
                            ans.append('X');
                            num = num - (j - 10);
                        } else {
                            ans.append('I');
                            num = num - (j - 1);
                        }
                        ans.append(map.get(j));
                        break;


                    }
                } else  {
                    for(int i = 0;i<arr.length;i++) {
                        if(num>=arr[i]) {
                            continue;
                        }
                        ans.append(map.get(arr[i-1]));
                        num = num-arr[i-1];
                        break;
                    }
                }

            }
        }
        return ans.toString();
    }

    private static void fun(int[] arr, StringBuilder ans,int num,Map<Integer,Character> map) {
        for (int j : arr) {
            if (num > j) {
                continue;
            }

            if(num>100) {
                ans.append('C');
                num = num-(j-100);
            }
            else if (num > 10) {
                ans.append('X');
                num = num - (j - 10);
            } else {
                ans.append('I');
                num = num - (j - 1);
            }
            ans.append(map.get(j));
            break;


        }
    }
}
