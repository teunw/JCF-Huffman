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

    public HuffmanEncoder buildNode() {
        Map<Character, Integer> freq = countCharacters(input);
        nodeQueue = new PriorityQueue<>(HuffmanNode::compareTo);
        freq.forEach(this::addToQueue);
        return this;
    }

    public HuffmanNode processNodes() {

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
            if (newNode == null) {
                newNode = new HuffmanNode(nextNode);
                continue;
            }
            if (newNode.getRightChild() == null) {
                newNode.setRightChild(nextNode);
                toAdd.add(newNode);
                newNode = null;
            }
        }
        nodeQueue.clear();

        System.out.println("Cleared, " + toAdd.size() + " left");

        toAdd.sort(HuffmanNode::compareTo);
        nodeQueue.addAll(toAdd);
        toAdd.clear();
    }

    private void addToQueue(char c, int f) {
        nodeQueue.add(new HuffmanNode(c, f));
    }

    private Map<Character, Integer> countCharacters(String str) {
        HashMap<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int freq;
            if (frequencies.containsKey(c)) {
                freq = frequencies.get(c);
            }else{
                freq = 0;
            }
            freq += 1;
            frequencies.put(c, freq);
        }
        return frequencies;
    }
}
