public class PFTreeNode extends Node {
    private final PFTreeNode parent;
    private String prefix;

    PFTreeNode(String val, boolean isLeafNode, int level, PFTreeNode parent) {
        super(val, isLeafNode, level);
        this.parent = parent;
        prefix = "";
    }

    @Override
    public void draw() {
        String str = "";

        // 构造prefix
        if (parent != null) {
            prefix = parent.prefix;
            if (!parent.isEndNode) {
                prefix += "|  ";
            } else {
                prefix += "   ";
            }
        }
        str += prefix;

        if (isEndNode)
            str += "└──";
        else
            str += "├──";

        if (isLeafNode)
            str += "♤ ";
        else
            str += "♢ ";

        str += val;

        System.out.println(str);
    }
}
