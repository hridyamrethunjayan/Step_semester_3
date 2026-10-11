#include <stdio.h>

int main() {
    int n, target, i, j, sum;
    int found = 0;

    printf("Enter number of elements: ");
    scanf("%d", &n);

    int nums[n];

    printf("Enter sorted elements: ");
    for (i = 0; i < n; i++) {
        scanf("%d", &nums[i]);
    }

    printf("Enter target: ");
    scanf("%d", &target);

    i = 0;
    j = n - 1;

    while (i < j) {
        sum = nums[i] + nums[j];

        if (sum == target) {
            printf("(%d, %d)\n", nums[i], nums[j]);
            found = 1;
            break;
        } else if (sum < target) {
            i++;
        } else {
            j--;
        }
    }

    if (found == 0) {
        printf("Not Found\n");
    }

    return 0;
}