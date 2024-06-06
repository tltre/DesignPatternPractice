import java.util.Objects;

public class RectNode extends Node {

    private final int postfixNum;

    RectNode(String icon, String val, boolean isLeafNode, int level, int postfixNum) {
        super(icon, val, isLeafNode, level);
        this.postfixNum = postfixNum;
    }

    @Override
    public void draw() {
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
