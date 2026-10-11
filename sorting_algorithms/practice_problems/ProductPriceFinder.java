public class ProductPriceFinder {
    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int low = 150, high = 300;
        int left = 0, right = prices.length, mid;

        while (left < right) {
            mid = left + (right - left) / 2;

            if (prices[mid] < low)
                left = mid + 1;
            else
                right = mid;
        }

        int lower = left;
        left = 0;
        right = prices.length;

        while (left < right) {
            mid = left + (right - left) / 2;

            if (prices[mid] <= high)
                left = mid + 1;
            else
                right = mid;
        }

        int upper = left;

        System.out.println("Lower bound index " + lower);
        System.out.println("Upper bound index " + upper);
        System.out.println("Count " + (upper - lower));
    }
}