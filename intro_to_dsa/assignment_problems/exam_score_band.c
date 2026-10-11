#include <stdio.h>

int main() {
    int n, i, low, high;
    int left, right, mid, first, last;

    scanf("%d", &n);
    int scores[n];

    for (i = 0; i < n; i++)
        scanf("%d", &scores[i]);

    scanf("%d %d", &low, &high);

    left = 0;
    right = n;

    while (left < right) {
        mid = left + (right - left) / 2;

        if (scores[mid] < low)
            left = mid + 1;
        else
            right = mid;
    }

    first = left;
    left = 0;
    right = n;

    while (left < right) {
        mid = left + (right - left) / 2;

        if (scores[mid] <= high)
            left = mid + 1;
        else
            right = mid;
    }

    last = left;
    printf("%d\n", last - first);

    return 0;
}