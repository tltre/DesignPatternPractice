public class TCRectNode extends Node {
    private final int postfixNum;

    TCRectNode(String val, boolean isLeafNode, int level, int postfixNum) {
        super(val, isLeafNode, level);
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


        if (isLeafNode)
            str += "● ";
        else
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
}
