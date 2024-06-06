public class TreeBuilder extends Builder {
    private boolean hasParent;
    private String parentPrefix;
    private boolean isParEnd;

    public void setHasParent(boolean hasParent) {
        this.hasParent = hasParent;
    }

    public void setParEnd(boolean isParEnd) {
        this.isParEnd = isParEnd;
    }

    public void setParentPrefix(String parentPrefix) {
        this.parentPrefix = parentPrefix;
    }

    @Override
    public void buildPrefix() {
        prefix = "";
        if (hasParent) {
            prefix = parentPrefix;
            if (!isParEnd) {
                prefix += "|  ";
            } else {
                prefix += "   ";
            }
        }
    }

    @Override
    public void buildPFMarker(boolean isStart, boolean isEnd, boolean isLeaf) {
        marker = "";
        if (isEnd)
            marker += "└──";
        else
            marker += "├──";

        if (isLeaf)
            marker += "♤ ";
        else
            marker += "♢ ";

    }

    @Override
    public void buildTCMarker(boolean isStart, boolean isEnd, boolean isLeaf) {
        marker = "";
        if (isEnd)
            marker += "└──";
        else
            marker += "├──";

        if (isLeaf)
            marker += "● ";
        else
            marker += "▷ ";
    }

    @Override
    public void buildPostfix(boolean isStart, boolean isEnd) {
        postfix = "";
    }
}
