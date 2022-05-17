/**
 * implementation of the Stopwatch class, with overridden toString
 *  Stopwatch
 *  elapsedTime
 *  toString
 *
 * @author Jonathon Zempel
 * @version 01-29-2022
 */
public class Stopwatch{
    private final long start;

    /**
     * starts the stopwatch
     */
    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    /**
     * stops the stopwatch
     * @return time the stopwatch was running in milliseconds
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) ;
    }

    /**
     * overrides toString to return a message with both milliseconds and seconds stopwatch was running
     * @return run time in milliseconds and seconds
     */
    @Override
    public String toString() {
        return "time to sort = " + elapsedTime() + " milliseconds, or " + elapsedTime()/1000 + " seconds";
    }

}
