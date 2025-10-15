package DSAPractise;

public class AddTwoLinkedListNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead = null;
        ListNode pointer = null;
        int value = 0;
        int carry = 0;
        /**
         * the case where both numbers are of same length
         */
        while(l1!=null && l2!=null){
            int sum = l1.val+l2.val+carry;
            value = sum;
            if(sum>=10){
                 carry = sum/10;
                 value = sum%10;
            } else {
                carry = 0;
            }
            ListNode temp = new ListNode(value,null);
            if(pointer == null){
                newHead = temp;
                pointer = temp;
            } else {
                pointer.next = temp;
                pointer = pointer.next;
            }
            l1 = l1.next;
            l2 = l2.next;
            if(l1 == null && l2 == null && carry>0) {
                ListNode last = new ListNode(carry,null);
                pointer.next = last;
                pointer = pointer.next;
            }
        }
        /**
         * l1 is greater than the l2
         */
        while(l1!=null){
            int sum = l1.val+carry;
            value = sum;
            if(sum>=10){
                carry = sum/10;
                value = sum%10;
            } else {
                carry = 0;
            }
            ListNode temp = new ListNode(value,null);
            pointer.next = temp;
            pointer = pointer.next;
            l1 = l1.next;
            if(l1 == null && carry>0){
                ListNode last = new ListNode(carry,null);
                pointer.next = last;
                pointer = pointer.next;
            }
        }
        /**
         * l2 is greater that l1
         */
        while(l2!=null){
            int sum = l2.val+carry;
            value = sum;
            if(sum>=10){
                carry = sum/10;
                value = sum%10;
            } else {
                carry = 0;
            }
            ListNode temp = new ListNode(value,null);
            pointer.next = temp;
            pointer = pointer.next;
            l2 = l2.next;
            if(l2 == null && carry>0) {
                ListNode last = new ListNode(carry,null);
                pointer.next = last;
                pointer = pointer.next;
            }
        }
        return newHead;
    }

    public ListNode optimizedCode(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;
            int sum = val1 + val2 + carry;

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }
}
