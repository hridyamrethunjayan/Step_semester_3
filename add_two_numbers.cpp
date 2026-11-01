#include <iostream>
using namespace std;

struct Node
{
    int data;
    Node *next;

    Node(int value)
    {
        data = value;
        next = NULL;
    }
};

Node* createList(int n)
{
    Node *head = NULL;
    Node *tail = NULL;

    for(int i = 0; i < n; i++)
    {
        int value;
        cin >> value;

        Node *newNode = new Node(value);

        if(head == NULL)
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            tail->next = newNode;
            tail = newNode;
        }
    }

    return head;
}

void display(Node *head)
{
    while(head != NULL)
    {
        cout << head->data;

        if(head->next != NULL)
            cout << " -> ";

        head = head->next;
    }
}

Node* addNumbers(Node *a, Node *b)
{
    Node dummy(0);
    Node *tail = &dummy;

    int carry = 0;

    while(a != NULL || b != NULL || carry != 0)
    {
        int sum = carry;

        if(a != NULL)
        {
            sum = sum + a->data;
            a = a->next;
        }

        if(b != NULL)
        {
            sum = sum + b->data;
            b = b->next;
        }

        carry = sum / 10;

        Node *newNode = new Node(sum % 10);

        tail->next = newNode;
        tail = tail->next;
    }

    return dummy.next;
}

int main()
{
    int n1, n2;

    cout << "Enter size of first list: ";
    cin >> n1;

    cout << "Enter first number: ";
    Node *a = createList(n1);

    cout << "Enter size of second list: ";
    cin >> n2;

    cout << "Enter second number: ";
    Node *b = createList(n2);

    Node *result = addNumbers(a, b);

    cout << "Sum: ";
    display(result);

    return 0;
}