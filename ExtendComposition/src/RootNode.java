import java.util.ArrayList;
import java.util.List;

public class RootNode extends Node {
    private List<Node> children;

    public List<Node> getChildren() {
        return children;
    }


    RootNode(String icon, String val, int level) {
        super(icon, val, level);
        children = new ArrayList<>();
    }

    public void addChild(Node node) {
        children.add(node);
    }
}
