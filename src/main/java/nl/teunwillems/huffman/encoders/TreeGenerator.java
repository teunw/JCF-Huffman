package nl.teunwillems.huffman.encoders;

import nl.teunwillems.huffman.objects.HuffmanNode;

import java.util.*;

/**
 * Created by Teun on 14-3-2016.
 */
public class TreeGenerator {

    private PriorityQueue<HuffmanNode> nodes;

    public TreeGenerator(Map<Character, Integer> freq) {
        nodes = new PriorityQueue<>(freq.size());
        freq.forEach((k, v) -> {
            nodes.add(new HuffmanNode(k, v));
        });
    }

    public HuffmanNode buildTree() {

        System.out.println("Starting with " + nodes.size() + " characters");
        while (nodes.size() > 1) processQueue();

        return nodes.poll();
    }

    private void processQueue() {
        Iterator<HuffmanNode> nodeIterator = nodes.iterator();
        HuffmanNode newNode = null;
        List<HuffmanNode> toAdd = new ArrayList<>((nodes.size() / 2) + 1);
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

        nodes.clear();
        toAdd.sort(HuffmanNode::compareTo);
        nodes.addAll(toAdd);
        toAdd.clear();
    }

}
