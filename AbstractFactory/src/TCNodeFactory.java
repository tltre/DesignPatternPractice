/* Produce the Node that with ICON is Tri-Circle */
public class TCNodeFactory extends NodeFactory{
    TCNodeFactory(String style) {
        super(style);
    }

    public Node createNode(boolean isLeafNode, int idx, Node parent) {
        String value = values[idx];
        int level = levels[idx];

        switch (style) {
            case "tree":
                return new TCTreeNode(value, isLeafNode, level, (TCTreeNode) parent);
            case "rect":
                return new TCRectNode(value, isLeafNode, level, length - (value.length() + 3 * level + 1));
            default:
                System.out.println("Invalid Style!");
                return null;
        }
    }
}
