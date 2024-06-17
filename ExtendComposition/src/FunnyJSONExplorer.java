import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class FunnyJSONExplorer {
    private static String filePath;
    private static String style;
    private static String iconFamily;

    private static Stack<Node> stack;

    private static Iterator iter;
    private static DrawingStrategy strategy;

    private static void init() {
        stack = new Stack<>();
        iter = new DFSIterator();

        switch (style) {
            case "tree":
                strategy = new DrawingTreeStrategy();
                break;
            case "rect":
                strategy = new DrawingRectStrategy();
                break;
            default:
                System.out.println("Error Style!");
        }
    }

    private static void load() {
        BufferedReader reader = null;

        int max_length = 0;
        String preVal = "";
        int preLevel = 0;

        // 1. 读取json文件
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(":");
                if (items.length != 2) {
                    continue;
                }
                // 2. 获取level值和value值
                String[] words = items[0].split("\"");
                int level = words[0].length() / 2;
                String val = words[1];
                if (items[1].contains("\"")) {
                    val += ": ";
                    val += items[1].split("\"")[1];
                }

                // 3. 构造Node节点
                switch (style) {
                    case "tree":
                        createNodeInTree(level, preLevel, preVal);
                        break;
                    case "rect":
                        createNodeInRect(level, preLevel, preVal);
                        break;
                    default:
                        System.out.println("Invalid Style!");
                }

                preLevel = level;
                preVal = val;
                max_length = Math.max(max_length, 1 + 3 * level + val.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 4. 处理最后一个节点
        switch (style) {
            case "tree":
                createNodeInTree(0, preLevel, preVal);
                break;
            case "rect":
                createNodeInRect(0, preLevel, preVal);
                break;
            default:
                System.out.println("Invalid Style!");
        }
        RootNode root = (RootNode) stack.firstElement();
        root.setMaxNum((max_length / 5 + 1) * 5);
        iter.initIterator(root);
    }

    private static void createNodeInTree(int level, int preLevel, String preVal) {
        Node newNode;
        if (level <= preLevel)
            newNode = new LeafNode(iconFamily, preVal, preLevel);
        else
            newNode = new RootNode(iconFamily, preVal, preLevel);

        if (preLevel > 0) {
            while (stack.peek().level >= preLevel) {
                if (stack.peek().level == preLevel) {
                    stack.peek().setEndNode(false);
                }
                stack.pop();
            }
            newNode.setParent(stack.peek());
            ((RootNode) stack.peek()).addChild(newNode);
        }

        stack.add(newNode);
    }

    private static void createNodeInRect(int level, int preLevel, String preVal) {
        Node newNode;
        if (level <= preLevel)
            newNode = new LeafNode(iconFamily, preVal, preLevel);
        else
            newNode = new RootNode(iconFamily, preVal, preLevel);

        if (preLevel > 0) {
            while (stack.peek().level >= preLevel) {
                stack.pop();
            }
            newNode.setParent(stack.peek());
            ((RootNode) stack.peek()).addChild(newNode);
        }

        newNode.setStartNode(false);
        newNode.setEndNode(false);

        if (preLevel == 1 && ((RootNode) stack.firstElement()).getChildren().size() == 1)
            newNode.setStartNode(true);
        if (level == 0)
            newNode.setEndNode(true);

        stack.add(newNode);
    }

    private static void show() {
        strategy.drawing(iter, iconFamily);
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "-f":
                    filePath = args[i + 1];
                case "-s":
                    style = args[i + 1].toLowerCase();
                case "-i":
                    iconFamily = args[i + 1];
                default:
            }
        }

        System.out.println("Hello, FJE!");
        System.out.println("Style: " + style);
        System.out.println("Icon-Family: " + iconFamily);
        System.out.print("\n");
        System.out.println("----------------------------");
        System.out.print("\n");

        init();
        load();
        show();
    }
}
