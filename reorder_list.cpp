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

Node* reverseList(Node *head)
{
    Node *prev = NULL;
    Node *curr = head;
    Node *next = NULL;

    while(curr != NULL)
    {
        next = curr->next;
        curr->next = prev;
        prev = curr;
        curr = next;
    }

    return prev;
}

Node* reorderList(Node *head)
{
    if(head == NULL || head->next == NULL)
        return head;

    Node *slow = head;
    Node *fast = head;

    while(fast->next != NULL && fast->next->next != NULL)
    {
        slow = slow->next;
        fast = fast->next->next;
    }

    Node *second = reverseList(slow->next);
    slow->next = NULL;

    Node *first = head;

    while(second != NULL)
    {
        Node *temp1 = first->next;
        Node *temp2 = second->next;

        first->next = second;
        second->next = temp1;

        first = temp1;
        second = temp2;
    }

    return head;
}

int main()
{
    int size;

    cout << "Enter size: ";
    cin >> size;

    cout << "Enter elements: ";
    Node *head = createList(size);

    cout << "Original list: ";
    display(head);

    head = reorderList(head);

    cout << "\nReordered list: ";
    display(head);

    return 0;
}