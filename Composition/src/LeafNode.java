import java.util.Objects;

public class LeafNode extends Node {

    LeafNode(String icon, String val, int level) {
        super(icon, val, level);
    }

    @Override
    public void drawTreeStyle() {
        String str = "";

        // 构造prefix
        if (level > 1) {
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
            str += "♤ ";
        } else if (Objects.equals(icon, "tri-circle")) {
            str += "● ";
        }

        str += val;

        System.out.println(str);
    }

    @Override
    public void drawRectStyle() {
        maxNum = parent.maxNum;
        int postfixNum = maxNum - (3 * level + val.length() + 1);
        String str = "";

        StringBuilder prefix = new StringBuilder();
        if (isStartNode)
            prefix.append("┌─");
        else if (isEndNode) {
            prefix.append("└─");
            for (int i = 1; i < level; i++) {
                prefix.append("─┴─");
            }
        } else {
            for (int i = 1; i < level; i++) {
                prefix.append("│  ");
            }
            prefix.append("├─");
        }
        str += prefix;

        // 根据Icon类型
        if (Objects.equals(icon, "poker-face"))
            str += "♤ ";
        else if (Objects.equals(icon, "tri-circle"))
            str += "● ";

        str += val;
        str += " ";

        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < postfixNum; i++) {
            postfix.append("─");
        }
        if (isStartNode)
            postfix.append("─┐");
        else if (isEndNode)
            postfix.append("─┘");
        else {
            postfix.append("─┤");
        }
        str += postfix;

        System.out.println(str);
    }

}