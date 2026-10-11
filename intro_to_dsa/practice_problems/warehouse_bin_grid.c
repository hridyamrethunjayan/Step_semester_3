#include <stdio.h>

int main() {
    int m, n, i, j;
    int total = 0, max, maxRow = 0, maxCol = 0;

    printf("Enter rows and columns: ");
    scanf("%d %d", &m, &n);

    int grid[m][n];

    printf("Enter grid elements:\n");
    for (i = 0; i < m; i++) {
        for (j = 0; j < n; j++) {
            scanf("%d", &grid[i][j]);
        }
    }

    max = grid[0][0];

    for (i = 0; i < m; i++) {
        for (j = 0; j < n; j++) {
            total += grid[i][j];

            if (grid[i][j] > max) {
                max = grid[i][j];
                maxRow = i;
                maxCol = j;
            }
        }
    }

    printf("Total items = %d\n", total);
    printf("Maximum bin value = %d\n", max);
    printf("Maximum coordinate = (%d, %d)\n", maxRow, maxCol);

    return 0;
}