package DSAPractise;

import java.util.Stack;

public class TimeBasedKeyValueStore {
    Stack<DataTimeStamp> timeStampStack;
    public TimeBasedKeyValueStore() {
        timeStampStack = new Stack<>();
    }

    public void set(String key, String value, int timestamp) {
        DataTimeStamp dataTimeStamp = new DataTimeStamp(key,value,timestamp);
        timeStampStack.push(dataTimeStamp);
    }

    public String get(String key, int timestamp) {
        Stack<DataTimeStamp> temp = new Stack<>();
        String ans = "";
        while(!timeStampStack.isEmpty()) {
            DataTimeStamp dataTimeStamp = timeStampStack.peek();
            if(dataTimeStamp.getKey().equals(key)){

                if(dataTimeStamp.getTimeStamp() == timestamp || dataTimeStamp.getTimeStamp()<timestamp) {
                    ans =  dataTimeStamp.getValue();
                    break;
                }
            }
            temp.push(timeStampStack.pop());
        }
        while(!temp.isEmpty()) {
            timeStampStack.push(temp.pop());
        }
        return ans;
    }
}

class DataTimeStamp {
    public DataTimeStamp(String key, String value, int timeStamp) {
        this.key = key;
        this.value = value;
        this.timeStamp = timeStamp;
    }

    public int getTimeStamp() {
        return timeStamp;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    String key;
    String value;
    int timeStamp;
}
