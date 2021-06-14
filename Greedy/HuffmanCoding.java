import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {

    int frequency;
    char data;
    HuffmanNode left, right;

    public int compareTo(HuffmanNode node) {

        return frequency - node.frequency;
    }
}

public class HuffmanCoding {

    private static Map<Character, String> prefixHashMap = new HashMap<>();
    static HuffmanNode root;
    public static Object build(String text) {

        if (text == null || text.length() == 0) {
            return root;
        }

        int n = text.length();
        Map<Character, Integer> freq = new HashMap<>();
        for (char c: text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        char[] charArray = text.toCharArray();

        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();
        Set<Character> keySet = freq.keySet();
        for (Character c : keySet) {

            HuffmanNode huffmanNode = new HuffmanNode();
            huffmanNode.data = c;
            huffmanNode.frequency = freq.get(c);
            huffmanNode.left = null;
            huffmanNode.right = null;

            priorityQueue.offer(huffmanNode);

        }

        while (priorityQueue.size() > 1) {

            HuffmanNode x = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode y = priorityQueue.peek();
            priorityQueue.poll();

            HuffmanNode sum = new HuffmanNode();

            sum.frequency = x.frequency + y.frequency;
            sum.data = '!';

            sum.left = x;

            sum.right = y;
            root = sum;

            priorityQueue.offer(sum);
        }


        return priorityQueue;
    }

    private static void setCodes(HuffmanNode node, StringBuilder prefix) {

        if (node != null) {
            if (node.left == null && node.right == null) {
                prefixHashMap.put(node.data, prefix.toString());

            } else {
                prefix.append('0');
                setCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length() - 1);

                prefix.append('1');
                setCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }

    }


    public static String encode(Object dic, String text) {


        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        pq = (PriorityQueue<HuffmanNode>) dic;
        HuffmanNode root = pq.peek();
        setCodes(root, new StringBuilder());

        StringBuilder s = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            s.append(prefixHashMap.get(c));
        }

        return s.toString();

    }

    public static String decode(Object dic, String text) {

        StringBuilder stringBuilder = new StringBuilder();
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        pq = (PriorityQueue<HuffmanNode>) dic;
        HuffmanNode root1 = pq.peek();
        HuffmanNode temp = root1;

        for (int i = 0; i < text.length(); i++) {
            int j = Integer.parseInt(String.valueOf(text.charAt(i)));

            if (j == 0) {
                temp = temp.left;
                if (temp.left == null && temp.right == null) {
                    stringBuilder.append(temp.data);
                    temp = root1;
                }
            }
            if (j == 1) {
                temp = temp.right;
                if (temp.left == null && temp.right == null) {
                    stringBuilder.append(temp.data);
                    temp = root1;
                }
            }
        }

        return  stringBuilder.toString();
    }
}

