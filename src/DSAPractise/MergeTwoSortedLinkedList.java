package DSAPractise;

public class MergeTwoSortedLinkedList {
    public ListNode merge(ListNode list1, ListNode list2) {
        ListNode head1 = list1;
        ListNode head2 = list2;
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;

        while(head1!=null && head2!=null) {
            if(head1.val == head2.val) {
                ListNode temp1 = head1.next;
                ListNode temp2 = head2.next;
                head1.next = head2;
                head2.next = temp1;
                prev.next = head1;
                head1 = head1.next;
                head2 = temp2;
                prev = prev.next;
            } else if(head1.val> head2.val) {
                ListNode temp2 = head2.next;
                prev.next = head2;
                head2.next = head1;
                prev = head2;
                head2 = temp2;
            } else  {
                prev.next =  head1;
                head1 = head1.next;
                prev = prev.next;
            }
        }
        prev.next = (head1 != null) ? head1 : head2;
//        System.out.println("Finished");
        return dummy.next;
    }
}

