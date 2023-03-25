import java.util.*;

public class GuitarString {

    private final Queue<Double> buffer;
    private int capacity;
    private static final double ENERGY_DECAY = 0.996;

    /**
     * Constructs a guitar string of the given frequency. It creates a ring buffer of the desired capacity N
     * (sampling rate divided by frequency, rounded to the nearest integer), and initializes it to represent a
     * guitar string at rest by enqueueing N zeros. The sampling rate is specified by the constant
     * StdAudio.SAMPLE_RATE.
     *
     * @param frequency the frequency of the guitar string, in Hz
     * @throws IllegalArgumentException if the frequency is less than or equal to 0, or if the resulting size of the
     *                                  ring buffer would be less than 2
     */
    public GuitarString(double frequency) {
        // Check if frequency is valid
        if (frequency <= 0 || Math.round(StdAudio.SAMPLE_RATE / frequency) < 2) {
            throw new IllegalArgumentException("Invalid frequency for guitar string");
        }

        // Calculate the capacity of the ring buffer
         capacity = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);

        // Initialize the ring buffer to contain N zeros
        buffer = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            buffer.add(0.0);
        }
    }



    /**

     Constructs a GuitarString object with an initial state specified by an array of doubles.
     The length of the array will determine the capacity of the ring buffer.
     Throws an IllegalArgumentException if the length of the array is less than 2.
     @param data an array of doubles representing the initial state of the ring buffer
     @throws IllegalArgumentException if the length of the array is less than 2
     */
    public GuitarString(double[] data) throws IllegalArgumentException {
        if (data.length < 2) {
            throw new IllegalArgumentException("Ring buffer size must be greater than or equal to 2.");
        }
        capacity = data.length;
        buffer = new LinkedList<>();
        for (double value : data) {
            buffer.add(value);
        }
    }


    /**

     This method replaces the current contents of the ring buffer with random values between -0.5 and 0.5.
     The method removes all elements from the buffer and replaces them with new elements.
     The new elements are generated using Math.random() method and a range of -0.5 to 0.5.
     This method has no return value.
     */
    public void pluck() {
        // Remove all the elements in the buffer to get ready for new values
        for (int i = 0; i < capacity; i++) {
            buffer.remove();
        }
        // Generate new values and add them to the buffer
        for (int i = 0; i < capacity; i++) {
            double value = Math.random() - 0.5;
            buffer.add(value);
        }
    }

    /**
     * Advances the simulation one time step by performing a single "tic". A "tic" consists of removing
     * the first sample from the buffer, computing the average of the first and second samples,
     * scaling it by the energy decay factor, and adding the result to the end of the buffer.
     */
    public void tic() {
        // remove the first sample from the buffer
        double first = buffer.remove();
        // retrieve the second sample without removing it
        double second = buffer.peek();
        // compute the new sample value
        double newSample = ENERGY_DECAY * (first + second) / 2;
        // add the new sample to the end of the buffer
        buffer.add(newSample);
    }


    /**
     * Returns the current sample at the front of the ring buffer.
     *
     * @return the current sample at the front of the ring buffer
     */
    public double sample() {
        return buffer.peek();
    }

    /**
     * Returns a string representation of the ring buffer.
     *
     * @return a string representation of the ring buffer
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double value : buffer) {
            sb.append(value).append(" ");
        }
        return sb.toString();
    }



}