package sprite;

/**
 * The type Counter.
 */
public class Counter {
    private int myCounter;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.myCounter = 0;
    }

    /**
     * Increase.
     *
     * @param number the number
     */
// add number to current count.
    public void increase(int number) {
        this.myCounter = this.myCounter + number;
    }

    /**
     * Decrease.
     *
     * @param number the number
     */
// subtract number from current count.
    public void decrease(int number) {
        this.myCounter = this.myCounter - number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
// get current count.
    public int getValue() {
        return this.myCounter;
    }
}
