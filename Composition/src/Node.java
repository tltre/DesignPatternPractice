public abstract class Node {
    protected final String icon;
    protected final String val;
    protected final int level;
    protected String prefix;
    protected int maxNum;

    protected boolean isStartNode;
    protected boolean isEndNode;

    protected Node parent;

    Node(String icon, String val, int level) {
        this.icon = icon;
        this.val = val;
        this.level = level;
        isStartNode = true;
        isEndNode = true;
        prefix = "";
    }

    public void setStartNode(boolean startNode) {
        isStartNode = startNode;
    }

    public void setEndNode(boolean endNode) {
        isEndNode = endNode;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void draw(String style, int maxNum) {
        this.maxNum = maxNum;

        switch (style) {
            case "tree":
                drawTreeStyle();
                break;
            case "rect":
                drawRectStyle();
                break;
            default:
                System.out.println("Invalid Style!");
        }

    }

    public abstract void drawTreeStyle();
    public abstract void drawRectStyle();
}
