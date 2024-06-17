public class DrawingRectStrategy extends DrawingStrategy {
    @Override
    public void drawing(Iterator iterator, String icon) {
        setIcon(icon);
        Node node = iterator.getNext();
        while (node != null) {
            int postfixNum = node.maxNum - (3 * node.level + node.val.length() + 1);
            String str = "";

            StringBuilder prefix = new StringBuilder();
            if (node.isStartNode)
                prefix.append("┌─");
            else if (node.isEndNode) {
                prefix.append("└─");
                for (int i = 1; i < node.level; i++) {
                    prefix.append("─┴─");
                }
            } else {
                for (int i = 1; i < node.level; i++) {
                    prefix.append("│  ");
                }
                prefix.append("├─");
            }
            str += prefix;

            if (node.getClass().getName().equals("LeafNode")) {
                str += leafIcon;
            } else {
                str += rootIcon;
            }

            str += " ";
            str += node.val;
            str += " ";

            StringBuilder postfix = new StringBuilder();
            for (int i = 0; i < postfixNum; i++) {
                postfix.append("─");
            }
            if (node.isStartNode)
                postfix.append("─┐");
            else if (node.isEndNode)
                postfix.append("─┘");
            else {
                postfix.append("─┤");
            }
            str += postfix;

            System.out.println(str);


            node = iterator.getNext();
        }
    }
}
