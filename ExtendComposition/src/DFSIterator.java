import java.util.ArrayList;

public class DFSIterator implements Iterator{
    private int curPos;
    private ArrayList<Node> cache;

    private void dfs(Node root) {
        if (root == null) {
            return;
        }

        if (root.level > 0)
            root.maxNum = root.parent.maxNum;

        cache.add(root);


        if (root.getClass().getName().equals("RootNode")) {
            for (Node child : ((RootNode) root).getChildren()) {
                dfs(child);
            }
        }
    }

    @Override
    public void initIterator(Node root) {
        curPos = 0;
        cache = new ArrayList<>();

        dfs(root);
        cache.remove(0);
    }

    @Override
    public boolean hasNext() {
        return curPos < cache.size();
    }

    @Override
    public Node getNext() {
        Node res = null;
        if(hasNext()){
            res = cache.get(curPos);
            curPos++;
        }
        return res;
    }
}
