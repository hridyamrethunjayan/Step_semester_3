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

Node* getTail(Node *head)
{
    if(head == NULL)
        return NULL;

    while(head->next != NULL)
        head = head->next;

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

Node* findIntersection(Node *a, Node *b)
{
    int lenA = 0;
    int lenB = 0;

    Node *temp = a;

    while(temp != NULL)
    {
        lenA++;
        temp = temp->next;
    }

    temp = b;

    while(temp != NULL)
    {
        lenB++;
        temp = temp->next;
    }

    Node *p = a;
    Node *q = b;
    if(lenA > lenB)
    {
        for(int i = 0; i < lenA - lenB; i++)
            p = p->next;
    }
    else
    {
        for(int i = 0; i < lenB - lenA; i++)
            q = q->next;
    }

    while(p != NULL && q != NULL)
    {
        if(p == q)
            return p;

        p = p->next;
        q = q->next;
    }

    return NULL;
}

int main()
{
    int n1, n2, n3;

    cout << "Enter size of first list (before intersection): ";
    cin >> n1;

    cout << "Enter first list elements: ";
    Node *a = createList(n1);

    cout << "Enter size of second list (before intersection): ";
    cin >> n2;

    cout << "Enter second list elements: ";
    Node *b = createList(n2);

    cout << "Enter size of common list: ";
    cin >> n3;

    cout << "Enter common list elements: ";
    Node *common = createList(n3);

    if(a == NULL)
        a = common;
    else
        getTail(a)->next = common;

    if(b == NULL)
        b = common;
    else
        getTail(b)->next = common;

    cout << "First list: ";
    display(a);

    cout << "\nSecond list: ";
    display(b);

    Node *result = findIntersection(a, b);

    if(result != NULL)
        cout << "\nIntersection node: " << result->data;
    else
        cout << "\nNo intersection";

    return 0;
}