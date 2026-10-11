#include <stdio.h>

int main() {
    int m, n, i, j;
    int top = 0, bottom, left = 0, right;

    scanf("%d %d", &m, &n);
    int grid[m][n];

    for (i = 0; i < m; i++)
        for (j = 0; j < n; j++)
            scanf("%d", &grid[i][j]);

    bottom = m - 1;
    right = n - 1;

    while (top <= bottom && left <= right) {
        for (j = left; j <= right; j++)
            printf("%d ", grid[top][j]);
        top++;

        for (i = top; i <= bottom; i++)
            printf("%d ", grid[i][right]);
        right--;

        if (top <= bottom) {
            for (j = right; j >= left; j--)
                printf("%d ", grid[bottom][j]);
            bottom--;
        }

        if (left <= right) {
            for (i = bottom; i >= top; i--)
                printf("%d ", grid[i][left]);
            left++;
        }
    }

    printf("\n");
    return 0;
}