/**
 * @author Pavel Stepanov
 * @version 1.0
 */

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Giant extends Critter {
    //================= ALL FIELDS =================\\
    private int xyzDirection = 0;
    private int Sound = 0;
    private final ArrayList<String> GiantSounds = new ArrayList<>();



    //================= GIANT OBJECT =================\\
    public Giant() {
        GiantSounds.add("fee"); GiantSounds.add("fie"); GiantSounds.add("foe"); GiantSounds.add("fum");
        Color colorGiant = Color.GRAY;
    }

    /**
     *
     * @param info
     * @return colorGiant
     */


    //================= GIANT OBJECT =================\\
    public Action getMove(CritterInfo info) {
        xyzDirection++;
        if(info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if(info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }
    }

    /**
     *
     * @return action of the object. HOP or INFECT or object WALL or OTHER
     */

    //================= GIANT COLOR =================\\
    public Color getColor() {

        return Color.GRAY;
    }

    /**
     *
     * @return color of the object
     */

    //================= GIANT TO-STRING METHOD =================\\
    public String toString() {
        if(xyzDirection == 6) {
            xyzDirection = 0;

        }
        if (Sound == 3) {
            Sound = 0;
        }
        else {
            Sound++;
        }
        return GiantSounds.get(Sound);
    }
    /**
     * @return final massage of the Giant Object to panel.
     */
}
