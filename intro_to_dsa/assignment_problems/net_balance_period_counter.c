#include <stdio.h>

#define SIZE 400009

long long keys[SIZE], freq[SIZE];
int used[SIZE];

int hashIndex(long long key) {
    int h = key % SIZE;
    if (h < 0) h += SIZE;
    return h;
}

int findSlot(long long key) {
    int h = hashIndex(key);

    while (used[h] && keys[h] != key)
        h = (h + 1) % SIZE;

    return h;
}

int main() {
    int n, i, slot;
    long long k, sum = 0, count = 0;

    scanf("%d", &n);
    int a[n];

    for (i = 0; i < n; i++)
        scanf("%d", &a[i]);

    scanf("%lld", &k);

    slot = findSlot(0);
    used[slot] = 1;
    keys[slot] = 0;
    freq[slot] = 1;

    for (i = 0; i < n; i++) {
        sum += a[i];

        slot = findSlot(sum - k);
        if (used[slot])
            count += freq[slot];

        slot = findSlot(sum);
        used[slot] = 1;
        keys[slot] = sum;
        freq[slot]++;
    }

    printf("%lld\n", count);
    return 0;
}