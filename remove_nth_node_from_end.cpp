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
        cout << head->data << " ";
        head = head->next;
    }
}

Node* removeNthFromEnd(Node* head, int n)
{
    Node dummy(0);
    dummy.next = head;

    Node *fast = &dummy;
    Node *slow = &dummy;

    for(int i = 0; i < n; i++)
    {
        fast = fast->next;
    }

    while(fast->next != NULL)
    {
        fast = fast->next;
        slow = slow->next;
    }

    Node *temp = slow->next;
    slow->next = temp->next;
    delete temp;

    return dummy.next;
}

int main()
{
    int size, n;

    cout << "Enter size: ";
    cin >> size;

    cout << "Enter elements: ";
    Node *head = createList(size);

    cout << "Enter n: ";
    cin >> n;

    head = removeNthFromEnd(head, n);

    cout << "Updated list: ";
    display(head);

    return 0;
}