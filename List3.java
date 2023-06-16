class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class List3 {
    static Node head;

    public static void main(String[] args) {
        // Creating the linked list from the given input
        int[] values = {2, 4, 7, 8, 9};
        int k = 3;

        for (int value : values) {
            appendNode(value);
        }

        System.out.print("Original Linked List: ");
        printLinkedList();

        leftShiftLinkedList(k);
        
        System.out.print("Left Shifted Linked List: ");
        printLinkedList();
    }

    static void appendNode(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node currentNode = head;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = newNode;
    }

    static void leftShiftLinkedList(int k) {
        if (head == null || k == 0)
            return;

        int length = getLength();
        k %= length; // Adjust k if it's larger than the length of the list

        if (k == 0)
            return;

        Node newHead = head;
        Node currentNode = head;
        Node prevNode = null;

        int count = 0;
        while (count < k && currentNode != null) {
            prevNode = currentNode;
            currentNode = currentNode.next;
            count++;
        }

        if (currentNode == null)
            return;

        prevNode.next = null; // Break the list at the k-th node
        newHead = currentNode;

        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }

        currentNode.next = head;
        head = newHead;
    }

    static int getLength() {
        int length = 0;
        Node currentNode = head;

        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }

        return length;
    }

    static void printLinkedList() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }
}
