package DSAPractise;


import java.util.Stack;

public class ReorderList {
    public void reorder(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode temp = head;
        ListNode slowPointer = head;
        ListNode fastPointer = head;
        ListNode last = slowPointer;
        while (fastPointer != null && fastPointer.next != null) {
            last = slowPointer;
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        last.next = null;
        ListNode mid =  reverseList(slowPointer);

        while(temp!=null && mid!=null) {
            ListNode nextTemp = temp.next;
            temp.next = mid;
            ListNode midNext = mid.next;
            mid.next = nextTemp;
            temp = nextTemp;
            if(temp == null){
                mid.next = midNext;
                break;
            }
            mid = midNext;
        }

        while(head!=null){
            System.out.print(head.val+" ");
            head = head.next;
        }



        //This solution is having o(n) space complexity
//        Stack<ListNode> stack = new Stack<>();
//        ListNode temp = head;
//        while(temp!=null) {
//            stack.push(temp);
//            temp = temp.next;
//        }
//
//        if(stack.size()<3){
//            return;
//        }
//
//        temp = head;
//        while(temp.next!=null && temp.next.next!=null) {
//            ListNode tempNext = temp.next;
//            ListNode end = stack.pop();
//            ListNode endPrev = stack.peek();
//            endPrev.next = null;
//            temp.next = end;
//            end.next = tempNext;
//            temp = end.next;
//        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev  = null;
        while(head!=null) {
            ListNode tempNext  = head.next;
            head.next = prev;
            prev = head;
            head = tempNext;
        }
        return prev;
    }
}
