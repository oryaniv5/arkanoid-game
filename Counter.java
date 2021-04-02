//ID:205444805

/**
 *
 */
public class Counter {
    private int value;

    /**
     * basic constructor method.
     *
     * @param value int
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * add number to current count.
     *
     * @param number to increase
     */
    public void increase(int number) {
        this.value = this.value + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number decrease
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * get current count.
     *
     * @return value of count
     */
    public int getValue() {
        return this.value;
    }
}
