class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class List4 {
    public static void main(String[] args) {
        // Creating the linked list from the given input
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(-3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(1);

        System.out.print("Original Linked List: ");
        printLinkedList(head);

        ListNode result = deleteZeroSumSublists(head);

        System.out.print("Modified Linked List: ");
        printLinkedList(result);
    }

    public static ListNode deleteZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode current = dummy;
        while (current != null) {
            int sum = 0;
            ListNode inner = current.next;
            while (inner != null) {
                sum += inner.val;
                if (sum == 0) {
                    current.next = inner.next;
                    break;
                }
                inner = inner.next;
            }
            if (inner == null) {
                current = current.next;
            }
        }

        return dummy.next;
    }

    public static void printLinkedList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
}
