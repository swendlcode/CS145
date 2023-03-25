/**
 * @author Pavel Stepanov
 * @version 1.0
 */

import java.awt.*;
public class Gator extends Critter {
    private int xyzDirection = 0;

    public Gator() { }

    //================= ACTION getMOVE =================\\
    public Action getMove(CritterInfo info) {
        xyzDirection++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if(info.getFront() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.WALL && info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else {
            return Action.HOP;
        }
    }

    /**
     *
     * @return action of the object. HOP or INFECT or object WALL or OTHER
     */


    //================= COLOR OF THE OBJECT =================\\
    public Color getColor() {
        return Color.GREEN;
    }

    /**
     * @return color of the object
     */


    //================= METHOD TO-STRING =================\\
    public String toString() { return "G";}
    /**
     * @return letter of the object.
     */
}
