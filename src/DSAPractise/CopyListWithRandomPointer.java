package DSAPractise;

import java.util.*;

public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        Map<Node,Integer> nodeCounter = new HashMap<>();
        Map<Node,Integer> randomNodePosition = new HashMap<>();
        Map<Integer,Node> nodeAtPosition = new HashMap<>();
        Node tempHead = head;
        int count = -1;
        while (tempHead!=null) {
            count++;
            nodeCounter.put(tempHead,count);
            tempHead  = tempHead.next;
        }

        Node newHead = null;
        Node tempNewHead = newHead;
        int counter = -1;
        while (head!=null) {
            counter++;
            Node random = head.random;
            Node newNode = new Node(head.val ,null,null);
            if(random != null) {
                int position = nodeCounter.get(random);
                randomNodePosition.put(newNode,position);
            }
            nodeAtPosition.put(counter,newNode);
            if(newHead == null) {
                newHead = newNode;
                tempNewHead = newHead;
            } else {
                tempNewHead.next = newNode;
                tempNewHead = tempNewHead.next;
            }
            head = head.next;
        }

        tempNewHead = newHead;
        while(tempNewHead!=null) {
            if(randomNodePosition.containsKey(tempNewHead)) {
                int position = randomNodePosition.get(tempNewHead);
                tempNewHead.random = nodeAtPosition.get(position);
            } else{
                tempNewHead.random = null;
            }
            tempNewHead = tempNewHead.next;
        }
        return newHead;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}
