#include <stdio.h>
#include <string.h>

int main() {
    int n, i, low, high, mid, result;
    char target[20];
    char isbn[100][20];
    char title[100][100];

    printf("Enter number of books: ");
    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        printf("Enter ISBN: ");
        scanf("%19s", isbn[i]);

        printf("Enter title: ");
        scanf(" %99[^\n]", title[i]);
    }

    printf("Enter ISBN to search: ");
    scanf("%19s", target);

    low = 0;
    high = n - 1;

    while (low <= high) {
        mid = (low + high) / 2;
        result = strcmp(isbn[mid], target);

        if (result == 0) {
            printf("Book title: %s\n", title[mid]);
            return 0;
        } else if (result < 0) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    printf("Not Found\n");

    return 0;
}