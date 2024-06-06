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
    private static NodeFactory factory;

    private static ArrayList<ArrayList<Integer>> rel;
    private static ArrayList<Integer> levels;
    private static ArrayList<String> values;
    private static ArrayList<Integer> preIndex;
    private static Node[] nodes;

    private static int getPreIndex(int level) {
        for (int i = levels.size() - 1; i >= 0; i--) {
            if (levels.get(i) < level) {
                return i;
            }
        }
        return -1;
    }

    private static void getNodes(int max_length) {
        int length = (max_length / 5 + 1) * 5;

        // 配置工厂
        factory.setLevels(levels.toArray(new Integer[0]));
        factory.setValues(values.toArray(new String[0]));
        factory.setLength(length);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);

        nodes = new Node[levels.size()];

        // flags: 0 代表普通节点；1代表头部节点；-1代表尾部节点
        int[] flags = new int[levels.size()];

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

            Node parent = null;
            if (preIndex.get(idx - 1) != -1) {
                parent = nodes[preIndex.get(idx - 1)];
            }

            Node newNode;
            if (nums.size() == 0)
                newNode = factory.createNode(true, idx - 1, parent);
            else {
                newNode = factory.createNode(false, idx - 1, parent);
            }

            if (flags[idx - 1] == 1) {
                newNode.setStartNode(true);
            } else if (flags[idx - 1] == -1) {
                newNode.setEndNode(true);
            }
            nodes[idx - 1] = newNode;
        }
    }

    private static void init() {
        rel = new ArrayList<>();
        rel.add(new ArrayList<>());
        levels = new ArrayList<>();
        values = new ArrayList<>();
        preIndex = new ArrayList<>();

        switch (iconFamily){
            case "poker-face":
                factory = new PFNodeFactory(style);
                break;
            case "tri-circle":
                factory = new TCNodeFactory(style);
                break;
            default:
                System.out.println("Invalid Icon!");
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
        getNodes(max_length);
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
