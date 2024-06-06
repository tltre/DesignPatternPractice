import java.util.Objects;

public class TreeNode extends Node {
    private final TreeNode parent;
    private String prefix;

    TreeNode(String icon, String val, boolean isLeafNode, int level, TreeNode parent) {
        super(icon, val, isLeafNode, level);
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

        // 根据Icon类型
        if (Objects.equals(icon, "poker-face")) {
            if (isLeafNode)
                str += "♤ ";
            else
                str += "♢ ";
        } else if (Objects.equals(icon, "tri-circle")) {
            if (isLeafNode)
                str += "● ";
            else
                str += "▷ ";
        }

        str += val;

        System.out.println(str);
    }
}
