package nl.teunwillems.huffman.encoders;

import nl.teunwillems.huffman.objects.HuffmanNode;

import java.util.*;

/**
 * Created by Teun on 7-3-2016.
 */
public class HuffmanEncoder {

    private PriorityQueue<HuffmanNode> nodeQueue;
    private HuffmanNode node;

    private String input;

    public HuffmanEncoder(String input) {
        this.input = input;
    }

    public void buildFrequencyQueue() {
        Map<Character, Integer> freq = countCharacters(input);
        nodeQueue = new PriorityQueue<>(HuffmanNode::compareTo);
        freq.forEach(this::addToQueue);
    }

    public HuffmanNode buildTree() {

        System.out.println("Starting with " + nodeQueue.size() + " characters");
        while (nodeQueue.size() > 1) processQueue();

        this.node = nodeQueue.poll();
        return node;
    }

    private void processQueue() {
        Iterator<HuffmanNode> nodeIterator = nodeQueue.iterator();
        HuffmanNode newNode = null;
        List<HuffmanNode> toAdd = new ArrayList<>(nodeQueue.size() / 2);
        while (nodeIterator.hasNext()) {
            HuffmanNode nextNode = nodeIterator.next();
            // Check if previous node is done
            if (newNode == null) {
                newNode = new HuffmanNode(nextNode);
                continue;
            }
            // If right child is not set, this is the second time
            // Add right child and continue
            if (newNode.getRightChild() == null) {
                newNode.setRightChild(nextNode);
                toAdd.add(newNode);
                newNode = null;
            }
        }
        // If right child hasn't been added to newNode
        if (newNode != null && newNode.getRightChild() == null)
            toAdd.add(newNode);

        nodeQueue.clear();
        toAdd.sort(HuffmanNode::compareTo);
        nodeQueue.addAll(toAdd);
        toAdd.clear();
    }

    public Map<Character, String> lookup() {
        HashMap<Character, String> lookup = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (lookup.containsKey(c)) continue;
            String s = "";
            node.lookup(c, s, lookup);
        }
        return lookup;
    }

    private void addToQueue(char c, int f) {
        nodeQueue.add(new HuffmanNode(c, f));
    }

    private Map<Character, Integer> countCharacters(String str) {
        HashMap<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {

            // Go to next character
            char c = str.charAt(i);
            int freq;
            // If character exists, get that frequency
            if (frequencies.containsKey(c)) {
                freq = frequencies.get(c);
            }else{
                // If character doesn't exist, create new
                freq = 0;
            }
            freq += 1;
            frequencies.put(c, freq);
        }
        return frequencies;
    }
}
