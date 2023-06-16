import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class List1 {
    static Node head;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes: ");
        int n = scanner.nextInt();

        System.out.print("Enter the values of the nodes: ");
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            appendNode(value);
        }

        System.out.print("Enter the position of the node to create a loop (0 for no loop): ");
        int x = scanner.nextInt();

        if (x > 0 && x <= n) {
            createLoop(x);
            System.out.println("Loop created at position " + x);
        }

        if (detectAndRemoveLoop()) {
            System.out.println("Loop detected and removed from the linked list.");
        } else {
            System.out.println("No loop detected in the linked list.");
        }

        System.out.println("Linked list after removing the loop:");
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

    static void createLoop(int x) {
        Node currentNode = head;
        Node loopNode = null;
        int count = 1;

        while (currentNode.next != null) {
            if (count == x) {
                loopNode = currentNode;
            }

            currentNode = currentNode.next;
            count++;
        }

        currentNode.next = loopNode;
    }

    static boolean detectAndRemoveLoop() {
        if (head == null || head.next == null) {
            return false;
        }

        Node slowPtr = head;
        Node fastPtr = head;

        // Move slowPtr by one node and fastPtr by two nodes
        slowPtr = slowPtr.next;
        fastPtr = fastPtr.next.next;

        while (fastPtr != null && fastPtr.next != null) {
            if (slowPtr == fastPtr) {
                removeLoop(slowPtr);
                return true;
            }

            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        return false;
    }

    static void removeLoop(Node loopNode) {
        Node ptr1 = head;
        Node ptr2 = loopNode;

        // Find the number of nodes in the loop
        int count = 1;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            count++;
        }

        // Move ptr1 'count' nodes ahead
        ptr1 = head;
        for (int i = 0; i < count; i++) {
            ptr1 = ptr1.next;
        }

        // Move both pointers at the same pace, they will meet at the starting node of the loop
        ptr2 = head;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // Reach the last node of the loop
        while (ptr1.next != ptr2) {
            ptr1 = ptr1.next;
        }

        // Unlink the last node to remove the loop
        ptr1.next = null;
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
