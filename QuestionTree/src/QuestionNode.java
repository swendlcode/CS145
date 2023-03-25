public class QuestionNode {
    // Fields question or answer data
    public String data;

    // Left child representing "yes" subtree
    public QuestionNode left;

    // Right child representing "no" subtree
    public QuestionNode right;

    /**

     Creates a new QuestionNode with the given data and null left and right subtrees.
     @param data the data to be stored in the node
     */
    public QuestionNode(String data) {
        this(data, null, null);
    }

    /**

     Constructs a new instance of QuestionNode with the given data, left and right nodes.
     @param data the data held in the node.
     @param left the left child node of the current node.
     @param right the right child node of the current node.
     */
    public QuestionNode(String data, QuestionNode left, QuestionNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**

     Returns a boolean indicating whether the current node is a leaf node,
     which is defined as a node with no left or right child nodes.
     @return a boolean indicating whether the current node is a leaf node
     */
    public boolean leafNode() {

        return left == null && right == null;
    }
}
