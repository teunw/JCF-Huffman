package nl.teunwillems.huffman.encoders;

import nl.teunwillems.huffman.objects.HuffmanNode;

import java.util.*;

/**
 * Created by Teun on 7-3-2016.
 */
public class HuffmanEncoder {

    private TreeGenerator treeGenerator;
    private Map<Character, Integer> frequencies;
    private HuffmanNode node;

    private String input;

    public HuffmanEncoder(String input) {
        this.input = input;
    }

    public void buildFrequencyQueue() {
        frequencies = countCharacters(input);
        treeGenerator = new TreeGenerator(frequencies);
    }

    public HuffmanNode buildTree() {
        return (node = treeGenerator.buildTree());
    }

    public Map<Character, String> lookup() {
        HashMap<Character, String> lookup = new HashMap<>();
        for (char c : input.toCharArray()) {
            // Continue if character is already in lookup table
            if (lookup.containsKey(c)) continue;
            String s = "";
            node.lookup(c, s, lookup);
        }
        return lookup;
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

    public TreeGenerator getTreeGenerator() {
        return treeGenerator;
    }

    public Map<Character, Integer> getFrequencies() {
        return frequencies;
    }

    public HuffmanNode getNode() {
        return node;
    }

    public String getInput() {
        return input;
    }
}
