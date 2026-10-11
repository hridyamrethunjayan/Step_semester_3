#include <stdio.h>

int main() {
    int n, k, i;
    int sum = 0, maxSum;

    printf("Enter number of days: ");
    scanf("%d", &n);

    int sales[n];

    printf("Enter daily sales: ");
    for (i = 0; i < n; i++) {
        scanf("%d", &sales[i]);
    }

    printf("Enter k: ");
    scanf("%d", &k);

    if (k < 1 || k > n) {
        printf("Invalid k\n");
        return 0;
    }

    for (i = 0; i < k; i++) {
        sum += sales[i];
    }

    maxSum = sum;

    for (i = k; i < n; i++) {
        sum = sum - sales[i - k] + sales[i];

        if (sum > maxSum) {
            maxSum = sum;
        }
    }

    printf("Maximum sum = %d\n", maxSum);

    return 0;
}