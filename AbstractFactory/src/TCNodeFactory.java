import java.util.Objects;

/* Produce the Node that with ICON is Tri-Circle */
public class TCNodeFactory extends NodeFactory{
    TCNodeFactory(String style) {
        super(style);
    }

    public Node createNode(boolean isLeafNode, int idx, Node parent) {
        String value = values[idx];
        int level = levels[idx];

        if (Objects.equals(style, "tree"))
            return new TCTreeNode(value, isLeafNode, level, (TCTreeNode) parent);
        else
            return new TCRectNode(value, isLeafNode, level, length - (value.length() + 3 * level + 1));
    }

}
