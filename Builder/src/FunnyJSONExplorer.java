import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class FunnyJSONExplorer {
    private static String filePath;
    private static String style;
    private static String iconFamily;
    private static int length;

    private static ArrayList<ArrayList<Integer>> rel;
    private static ArrayList<Integer> levels;
    private static ArrayList<String> values;
    private static ArrayList<Integer> preIndex;

    private static int[] flags;
    private static String[] prefixes;
    private static Node[] nodes;

    private static Builder builder;

    private static Node buildTreeNode(int idx, boolean isLeaf) {
        String parentPrefix = preIndex.get(idx) == -1 ? "" : prefixes[preIndex.get(idx)];
        boolean isParEnd = preIndex.get(idx) != -1 && (flags[preIndex.get(idx)] == -1);
        boolean isStart = (flags[idx] == 1);
        boolean isEnd = (flags[idx] == -1);

        TreeBuilder treeBuilder = (TreeBuilder) builder;
        treeBuilder.setParentPrefix(parentPrefix);
        treeBuilder.setParEnd(isParEnd);
        treeBuilder.setHasParent(preIndex.get(idx) != -1);

        treeBuilder.buildPrefix();

        switch (iconFamily){
            case "poker-face":
                treeBuilder.buildPFMarker(isStart, isEnd, isLeaf);
                break;
            case "tri-circle":
                treeBuilder.buildTCMarker(isStart, isEnd, isLeaf);
                break;
            default:
                System.out.println("Invalid Icon!");
        }

        treeBuilder.buildVal(values.get(idx));
        treeBuilder.buildPostfix(true, false);

        prefixes[idx] = treeBuilder.getPrefix();

        return treeBuilder.getProduct();
    }

    private static Node buildRectNode(int idx, boolean isLeaf) {
        String value = values.get(idx);
        int level = levels.get(idx);
        boolean isStart = (flags[idx] == 1);
        boolean isEnd = (flags[idx] == -1);

        RectBuilder rectBuilder = (RectBuilder) builder;
        rectBuilder.setPostfixNum(length - (value.length() + 3 * level + 1));
        rectBuilder.setLevel(level);

        rectBuilder.buildPrefix();
        switch (iconFamily){
            case "poker-face":
                rectBuilder.buildPFMarker(isStart, isEnd, isLeaf);
                break;
            case "tri-circle":
                rectBuilder.buildTCMarker(isStart, isEnd, isLeaf);
                break;
            default:
                System.out.println("Invalid Icon!");
        }
        rectBuilder.buildVal(values.get(idx));
        rectBuilder.buildPostfix(isStart, isEnd);

        return rectBuilder.getProduct();
    }


    private static int getPreIndex(int level) {
        for (int i = levels.size() - 1; i >= 0; i--) {
            if (levels.get(i) < level) {
                return i;
            }
        }
        return -1;
    }

    private static void init() {
        rel = new ArrayList<>();
        rel.add(new ArrayList<>());
        levels = new ArrayList<>();
        values = new ArrayList<>();
        preIndex = new ArrayList<>();

        switch (style) {
            case "tree":
                builder = new TreeBuilder();
                break;
            case "rect":
                builder = new RectBuilder();
                break;
            default:
                System.out.println("Invalid Style!");
        }
    }

    private static void load() {
        BufferedReader reader = null;
        int max_length = 0;

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
                // 3. 存储Item信息
                preIndex.add(getPreIndex(level));
                rel.get(getPreIndex(level) + 1).add(rel.size());
                rel.add(new ArrayList<>());
                levels.add(level);
                values.add(val);
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
        // 4. 根据style、节点level构造节点
        length = (max_length / 5 + 1) * 5;
        createNodes();
    }

    private static void createNodes() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        nodes = new Node[levels.size()];

        // flags: 0 代表普通节点；1代表头部节点；-1代表尾部节点
        flags = new int[levels.size()];
        prefixes = new String[levels.size()];

        while (!queue.isEmpty()) {
            int idx = queue.poll();
            ArrayList<Integer> nums = rel.get(idx);
            for (int i = 0; i < nums.size(); i++) {
                if (Objects.equals(style, "tree")) {
                    if (i == nums.size() - 1) {
                        flags[nums.get(i) - 1] = -1;
                    }
                } else if (Objects.equals(style, "rect")) {
                    if (nums.get(i) == 1) {
                        flags[nums.get(i) - 1] = 1;
                    } else if (nums.get(i) == levels.size()) {
                        flags[nums.get(i) - 1] = -1;
                    }
                }
                queue.offer(nums.get(i));
            }

            if (idx == 0)
                continue;

            if (nums.size() == 0)
                nodes[idx - 1] = buildNode(idx - 1, true);
            else {
                nodes[idx - 1] = buildNode(idx - 1, false);
            }
        }
    }

    private static Node buildNode(int idx, boolean isLeaf){
        switch (style) {
            case "tree":
                return buildTreeNode(idx, isLeaf);
            case "rect":
                return buildRectNode(idx, isLeaf);
            default:
                System.out.println("Invalid Style!");
                return null;
        }
    }

    private static void show() {
        for (Node node : nodes) {
            node.draw();
        }
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
