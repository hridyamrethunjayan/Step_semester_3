#include <stdio.h>

int main() {
    int n, budget, i, left = 0;
    int start = -1, length = 0, maxLength = 0;
    long long sum = 0;

    scanf("%d", &n);
    int costs[n];

    for (i = 0; i < n; i++)
        scanf("%d", &costs[i]);

    scanf("%d", &budget);

    for (i = 0; i < n; i++) {
        sum += costs[i];

        while (sum > budget) {
            sum -= costs[left];
            left++;
        }

        length = i - left + 1;

        if (length > maxLength) {
            maxLength = length;
            start = left;
        }
    }

    printf("(%d, %d)\n", maxLength, start);
    return 0;
}