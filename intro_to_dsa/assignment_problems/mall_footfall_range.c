#include <stdio.h>

int main() {
    int n, q, i, l, r;
    long long sum[100001] = {0};

    scanf("%d", &n);
    int visitors[n];

    for (i = 0; i < n; i++) {
        scanf("%d", &visitors[i]);
        sum[i + 1] = sum[i] + visitors[i];
    }

    scanf("%d", &q);
    while (q--) {
        scanf("%d %d", &l, &r);
        printf("%lld\n", sum[r + 1] - sum[l]);
    }

    return 0;
}