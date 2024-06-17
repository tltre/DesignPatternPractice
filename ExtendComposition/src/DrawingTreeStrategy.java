public class DrawingTreeStrategy extends DrawingStrategy {
    @Override
    public void drawing(Iterator iterator, String icon) {
        setIcon(icon);

        Node node = iterator.getNext();
        while (node != null) {
            String str = "";

            // 构造prefix
            if (node.level > 1) {
                node.prefix = node.parent.prefix;
                if (!node.parent.isEndNode) {
                    node.prefix += "|  ";
                } else {
                    node.prefix += "   ";
                }
            }
            str += node.prefix;

            // 根据是否为尾结点
            if (node.isEndNode)
                str += "└──";
            else
                str += "├──";

            if (node.getClass().getName().equals("LeafNode")) {
                str += leafIcon;
            } else {
                str += rootIcon;
            }
            str += " ";

            str += node.val;

            System.out.println(str);
            node = iterator.getNext();
        }
    }
}
