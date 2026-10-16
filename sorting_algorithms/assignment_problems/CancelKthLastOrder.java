
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CancelKthLastOrder {

    public Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node lead = dummy;
        Node trail = dummy;

        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        trail.next = trail.next.next;

        return dummy.next;
    }

    public static void display(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data);

            if (temp.next != null) {
                System.out.print(" -> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        CancelKthLastOrder obj = new CancelKthLastOrder();

        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        System.out.println("Original orders:");
        display(head);

        head = obj.removeKthFromEnd(head, 2);

        System.out.println("After cancelling 2nd order from end:");
        display(head);
    }
}
