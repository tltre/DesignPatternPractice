/* Produce the Node that with ICON is Tri-Circle */
public class PFNodeFactory extends NodeFactory {
    PFNodeFactory(String style) {
        super(style);
    }

    public Node createNode(boolean isLeafNode, int idx, Node parent) {
        String value = values[idx];
        int level = levels[idx];

        switch (style) {
            case "tree":
                return new PFTreeNode(value, isLeafNode, level, (PFTreeNode) parent);
            case "rect":
                return new PFRectNode(value, isLeafNode, level, length - (value.length() + 3 * level + 1));
            default:
                System.out.println("Invalid Style!");
                return null;
        }
    }
}
