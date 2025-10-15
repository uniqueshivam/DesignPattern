package DSAPractise;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    public boolean getCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast!=null) {
            fast = fast.next;
            if(fast == null){
                return false;
            }
            fast = fast.next;
            slow = slow.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}
