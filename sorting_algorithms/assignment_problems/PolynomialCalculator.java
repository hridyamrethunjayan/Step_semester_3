class Node {
    int coefficient, exponent;
    Node next;

    Node(int c, int e) {
        coefficient = c;
        exponent = e;
        next = null;
    }
}

public class PolynomialCalculator {
    static Node addPolynomials(Node p, Node q) {
        Node head = null, temp = null;

        while (p != null && q != null) {
            int c, e;

            if (p.exponent > q.exponent) {
                c = p.coefficient;
                e = p.exponent;
                p = p.next;
            } else if (p.exponent < q.exponent) {
                c = q.coefficient;
                e = q.exponent;
                q = q.next;
            } else {
                c = p.coefficient + q.coefficient;
                e = p.exponent;
                p = p.next;
                q = q.next;
            }

            if (c != 0) {
                Node newNode = new Node(c, e);

                if (head == null) {
                    head = newNode;
                    temp = newNode;
                } else {
                    temp.next = newNode;
                    temp = newNode;
                }
            }
        }

        while (p != null) {
            if (p.coefficient != 0) {
                Node newNode = new Node(p.coefficient, p.exponent);
                if (head == null) {
                    head = newNode;
                    temp = newNode;
                } else {
                    temp.next = newNode;
                    temp = newNode;
                }
            }
            p = p.next;
        }

        while (q != null) {
            if (q.coefficient != 0) {
                Node newNode = new Node(q.coefficient, q.exponent);
                if (head == null) {
                    head = newNode;
                    temp = newNode;
                } else {
                    temp.next = newNode;
                    temp = newNode;
                }
            }
            q = q.next;
        }

        return head;
    }

    static void display(Node head) {
        boolean first = true;

        while (head != null) {
            if (!first && head.coefficient > 0)
                System.out.print(" + ");
            else if (!first)
                System.out.print(" - ");
            else if (head.coefficient < 0)
                System.out.print("-");

            int c = Math.abs(head.coefficient);

            if (head.exponent == 0)
                System.out.print(c);
            else {
                if (c != 1)
                    System.out.print(c);
                System.out.print("x");
                if (head.exponent != 1)
                    System.out.print("^" + head.exponent);
            }

            first = false;
            head = head.next;
        }

        if (first)
            System.out.print("0");

        System.out.println();
    }

    public static void main(String[] args) {
        Node p = new Node(5, 3);
        p.next = new Node(4, 2);
        p.next.next = new Node(2, 0);

        Node q = new Node(5, 2);
        q.next = new Node(-3, 1);
        q.next.next = new Node(1, 0);

        Node result = addPolynomials(p, q);

        System.out.print("Sum: ");
        display(result);
    }
}