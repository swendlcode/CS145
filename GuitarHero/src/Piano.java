import java.util.*;
public class Piano implements Guitar {


    public static final String KEYBOARD =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    public static PianoString[] strings;
    private int time;


    public Piano() {
        strings = new PianoString[KEYBOARD.length()];

        for (int i = 0; i < strings.length; i++) {
            double frequency = 440.0 * Math.pow(2, (i - 24.0) / 12.0);
            strings[i] = new PianoString(frequency);
        }


    }




    public void playNote(int pitch) {
        int index = pitch + 24;
        if (index < 0 || index >= strings.length) {
            return;
        }
        strings[index].pluck();
    }


    public boolean hasString(char key) {
        return KEYBOARD.indexOf(key) >= 0;
    }

    public void pluck(char key) throws IllegalArgumentException {

        int index = KEYBOARD.indexOf(key);

        if (index >= 0) {
            strings[index].pluck();
        } else {
            throw new IllegalArgumentException("Invalid key for plucking guitar string");
        }
    }


    public double sample() {
        double sum = 0.0;
        for (PianoString string : strings) {
            sum += string.sample();
        }
        return sum;
    }


    public void tic() {
        for (PianoString string : strings) {
            string.tic();
        }
        time++;
    }


    public int time() {
        return time;
    }



}
