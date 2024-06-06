import java.util.Objects;

public class NodeFactory {
    private final String style;
    private final String icon;
    private final Integer[] levels;
    private final String[] values;
    private final int length;

    NodeFactory(String style, String icon, Integer[] levels, String[] values, int length) {
        this.style = style;
        this.icon = icon;
        this.levels = levels;
        this.values = values;
        this.length = length;
    }

    public Node createNode(boolean isLeafNode, int idx, Node parent) {
        String value = values[idx];
        int level = levels[idx];

        if (Objects.equals(style, "tree"))
            return new TreeNode(icon, value, isLeafNode, level, (TreeNode) parent);
        else if (Objects.equals(style, "rect"))
            return new RectNode(icon, value, isLeafNode, level, length - (value.length() + 3 * level + 1));
        else {
            System.out.println("Invalid Style!");
            return null;
        }
    }
}

