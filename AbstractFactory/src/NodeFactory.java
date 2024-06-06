public abstract class NodeFactory {
    protected final String style;
    protected Integer[] levels;
    protected String[] values;
    protected int length;

    NodeFactory(String style) {
        this.style = style;
    }

    public void setLevels(Integer[] levels) {
        this.levels = levels;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public abstract Node createNode(boolean isLeafNode, int idx, Node parent);
}

