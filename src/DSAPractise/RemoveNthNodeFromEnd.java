package DSAPractise;

public class RemoveNthNodeFromEnd {
    public ListNode remove(ListNode head, int n) {
        int count = 1;
        ListNode toRemove = head;
        ListNode prev = head;
        ListNode temp = head;

        while(temp.next!=null) {
            if(count >= n) {
                prev = toRemove;
                toRemove = toRemove.next;
            }
            temp = temp.next;
            count++;
        }

        ListNode next = toRemove.next;
        prev.next = next;
        if(toRemove == head) {
            head = next;
        }
        toRemove.next = null;
        return head;
    }
}
