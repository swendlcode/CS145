
import java.awt.*;
import java.util.Arrays;
import java.util.List;

class Note {
    char key;
    double time;

    public Note(char key, double time) {
        this.key = key;
        this.time = time;
    }
}

public class SimpleGuitarHero {

    private static final String[] KEY_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static final Color[] KEY_COLORS = {Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE, Color.BLACK, Color.WHITE};
    private static final double KEY_WIDTH = 0.05;
    private static final double KEY_HEIGHT = 0.6;

    static List<Note> notes = Arrays.asList(
            new Note('a', 1.0),
            new Note('s', 2.0),
            new Note('d', 3.0),
            new Note('f', 4.0),
            new Note('j', 5.0),
            new Note('k', 6.0),
            new Note('l', 7.0),
            new Note('a', 8.0)
    );

    static void drawNotes(double currentTime) {
        for (Note note : notes) {
            double timeToHit = note.time - currentTime;
            if (timeToHit >= 0 && timeToHit < 10) {
                double y = 1 - timeToHit / 10;
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.filledCircle(note.key - 'a', y, 0.03);
            }
        }
    }

    static void drawInterface() {
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int i = 0; i < 26; i++) {
            StdDraw.text(i, 0, Character.toString((char) ('a' + i)));
        }
    }

    public static void keyboard() {

        StdDraw.setCanvasSize(1200, 800);
        int numKeys = KEY_NAMES.length * 2; // 2 keys per octave (white and black)
        double keySpacing = (1.0 - numKeys * KEY_WIDTH) / (numKeys - 1); // space between keys

        StdDraw.setXscale(0, 1);
        StdDraw.setYscale(0, 1);

        boolean[] keyPlayed = new boolean[37]; // to keep track of played keys
        Color[] keyColors = new Color[numKeys]; // to keep track of the colors of the keys

        for (int i = 0; i < numKeys; i++) {
            double x = i * (KEY_WIDTH + keySpacing);
            double y = 0;
            double width = KEY_WIDTH;
            double height = KEY_HEIGHT;
            Color color = KEY_COLORS[i % 12];

            if (color == Color.BLACK) {
                y = (KEY_HEIGHT - 0.2);
            }

            keyColors[i] = color; // save the original color of the key
            StdDraw.setPenColor(color);
            StdDraw.filledRectangle(x + width / 2.0, y + height / 2.0, width / 2.0, height / 2.0);

            StdDraw.setPenColor(Color.BLACK);
            StdDraw.rectangle(x + width / 2.0, y + height / 2.0, width / 2.0, height / 2.0);

            // Draw key label
            String keyName = KEY_NAMES[i % 12];
            double labelX = x + width / 2.0;
            double labelY = y + height / 2.0 + 0.05;
            if (color == Color.WHITE) {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.text(labelX, labelY - 0.1, keyName);
            } else {
                StdDraw.setPenColor(Color.WHITE);
                StdDraw.text(labelX, labelY, keyName);
            }
        }
    }
}
