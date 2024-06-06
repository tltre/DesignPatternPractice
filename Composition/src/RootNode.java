import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RootNode extends Node {
    private List<Node> children;

    public List<Node> getChildren() {
        return children;
    }


    RootNode(String icon, String val, int level) {
        super(icon, val, level);
        children = new ArrayList<>();
    }

    public void addChild(Node node) {
        children.add(node);
    }

    @Override
    public void drawTreeStyle() {
        if (level != 0) {
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
                str += "♢ ";
            } else if (Objects.equals(icon, "tri-circle")) {
                str += "▷ ";
            }

            str += val;

            System.out.println(str);
        }

        for (Node child : children) {
            child.drawTreeStyle();
        }
    }

    @Override
    public void drawRectStyle() {
        if (level != 0) {
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

            if (Objects.equals(icon, "poker-face"))
                str += "♢ ";
            else if (Objects.equals(icon, "tri-circle"))
                str += "▷ ";

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

        for (Node child : children) {
            child.drawRectStyle();
        }
    }

}
