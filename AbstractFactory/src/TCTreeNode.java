public class TCTreeNode extends Node {
    private final TCTreeNode parent;
    private String prefix;

    TCTreeNode(String val, boolean isLeafNode, int level, TCTreeNode parent) {
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

        // 根据是否为尾结点
        if (isEndNode)
            str += "└──";
        else
            str += "├──";

        // 根据是否为叶子结点
        if (isLeafNode)
            str += "● ";
        else
            str += "▷ ";

        str += val;

        System.out.println(str);
    }
}
