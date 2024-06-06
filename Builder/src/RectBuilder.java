public class RectBuilder extends Builder {

    private int postfixNum;

    private int level;

    public void setPostfixNum(int postfixNum) {
        this.postfixNum = postfixNum;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void buildPrefix() {
        prefix = "";
    }

    @Override
    public void buildPFMarker(boolean isStart, boolean isEnd, boolean isLeaf) {
        StringBuilder markerBuilder = new StringBuilder();
        if (isStart)
            markerBuilder.append("┌──");
        else if (isEnd) {
            markerBuilder.append("└──");
            for (int i = 1; i < level; i++) {
                markerBuilder.append("─┴─");
            }
        } else {
            for (int i = 1; i < level; i++) {
                markerBuilder.append("│  ");
            }
            markerBuilder.append("├─");
        }

        if (isLeaf)
            markerBuilder.append("♤ ");
        else
            markerBuilder.append("♢ ");

        marker = String.valueOf(markerBuilder);
    }

    @Override
    public void buildTCMarker(boolean isStart, boolean isEnd, boolean isLeaf) {
        StringBuilder markerBuilder = new StringBuilder();
        if (isStart)
            markerBuilder.append("┌──");
        else if (isEnd) {
            markerBuilder.append("└──");
            for (int i = 1; i < level; i++) {
                markerBuilder.append("┴──");
            }
        } else {
            for (int i = 1; i < level; i++) {
                markerBuilder.append("│  ");
            }
            markerBuilder.append("├──");
        }

        if (isLeaf)
            markerBuilder.append("● ");
        else
            markerBuilder.append("▷ ");

        marker = String.valueOf(markerBuilder);
    }

    @Override
    public void buildPostfix(boolean isStart, boolean isEnd) {
        StringBuilder postfixBuilder = new StringBuilder();
        for (int i = 0; i < postfixNum; i++) {
            postfixBuilder.append("─");
        }
        if (isStart)
            postfixBuilder.append("─┐");
        else if (isEnd)
            postfixBuilder.append("─┘");
        else {
            postfixBuilder.append("─┤");
        }
        postfix = String.valueOf(postfixBuilder);
    }
}
