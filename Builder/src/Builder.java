public abstract class Builder {
    protected String prefix;
    protected String marker;
    protected String val;
    protected String postfix;

    public abstract void buildPrefix();

    public abstract void buildPFMarker(boolean isStart, boolean isEnd, boolean isLeaf);

    public abstract void buildTCMarker(boolean isStart, boolean isEnd, boolean isLeaf);

    public void buildVal(String val) {
        this.val = val;
    }

    public abstract void buildPostfix(boolean isStart, boolean isEnd);

    public String getPrefix() {
        return prefix;
    }

    public Node getProduct() {
        return new Node(prefix + marker + val + postfix);
    }
}
