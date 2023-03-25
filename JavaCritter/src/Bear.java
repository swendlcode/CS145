/**
 * @author Pavel Stepanov
 * @version 1.0
 */

import java.awt.*;
public class Bear extends Critter {
    private final Color ColorType;
    private int xyzDirection = 0;
    Color Polar = Color.WHITE;
    Color Bear = Color.BLACK;



    //================= GET MOVE METHOD =================\\
    public Action getMove(CritterInfo info) {
        xyzDirection++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        }
        if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        }
        else { return super.getMove(info); }
    }

    /**
     * @param TypeColor
     * @return action of the object. HOP or INFECT or object WALL or OTHER
     */

    //================= SET COLOR =================\\
    public Bear (Boolean TypeColor) {
        if(TypeColor) { ColorType = Polar; }
        else { ColorType = Bear; }
    }

    /**
     *
     * @return Color Type
     */


    //================= GET COLOR =================\\
    public Color getColor() { return ColorType; }

    /**
     * @return getColor -> Color Type
     */

    public String toString() {
        if (xyzDirection % 2 == 0) {
            return "\\";
        }
        else { return "/"; }
    }
    /**
     * @return / or \\ (Should alternate on each different move between a slash character (/) anda backslash character (\) startingwith a slash.)
     */
}
