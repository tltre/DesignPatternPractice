public abstract class Node {
    protected final String icon;
    protected final String val;
    protected final boolean isLeafNode;
    protected final int level;

    protected boolean isStartNode;
    protected boolean isEndNode;


    Node(String icon, String val, boolean isLeafNode, int level) {
        this.icon = icon;
        this.val = val;
        this.isLeafNode = isLeafNode;
        this.level = level;
        isStartNode = false;
        isEndNode = false;
    }

    public void setStartNode(boolean startNode) {
        isStartNode = startNode;
    }

    public void setEndNode(boolean endNode) {
        isEndNode = endNode;
    }

    public abstract void draw();
}
