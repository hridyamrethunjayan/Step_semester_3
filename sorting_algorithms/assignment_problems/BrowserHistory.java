
class Node {
    String url;
    Node prev, next;

    Node(String url) {
        this.url = url;
        this.prev = null;
        this.next = null;
    }
}

public class BrowserHistory {
    Node current;

    BrowserHistory(String homepage) {
        current = new Node(homepage);
    }

    public void visit(String url) {
        Node newNode = new Node(url);

        current.next = null;
        newNode.prev = current;
        current.next = newNode;
        current = newNode;
    }

    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }

        return current.url;
    }

    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }

        return current.url;
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));

        browser.visit("d.com");

        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
}
