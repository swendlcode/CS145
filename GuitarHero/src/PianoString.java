import java.util.*;

public class PianoString {

    private final Queue<Double> buffer;
    private int capacity;
    private static final double ENERGY_DECAY = 0.966;
    private double frequency;

    public PianoString(double frequency) {
        if (frequency <= 0 || Math.round(StdAudio.SAMPLE_RATE / frequency) < 2) {
            throw new IllegalArgumentException("Invalid frequency for guitar string");
        }

        this.frequency = frequency;
        capacity = (int) Math.round(StdAudio.SAMPLE_RATE / frequency);

        buffer = new LinkedList<>();
        for (int i = 0; i < capacity; i++) {
            buffer.add(0.0);
        }
    }

    public PianoString(double[] data) throws IllegalArgumentException {
        if (data.length < 2) {
            throw new IllegalArgumentException("Ring buffer size must be greater than or equal to 2.");
        }
        capacity = data.length;
        buffer = new LinkedList<>();
        for (double value : data) {
            buffer.add(value);
        }
    }

    public void pluck() {
        // Remove all the elements in the buffer to get ready for new values
        for (int i = 0; i < capacity; i++) {
            buffer.remove();
        }
        // Generate new values using a sine wave function for trumpet-like excitation
        for (int i = 0; i < capacity; i++) {
            double value = 0.5 * Math.sin(2 * Math.PI * i / capacity) + 0.1 * Math.sin(4 * Math.PI * i / capacity) + 0.25 * Math.sin(8 * Math.PI * i / capacity);
            buffer.add(value);
        }
    }


    public void tic() {
        double first = buffer.remove();
        double second = buffer.peek();
        double newSample = ENERGY_DECAY * (first + second) / 2;
        buffer.add(newSample);
    }

    public double sample() {
        return buffer.peek();
    }

}
