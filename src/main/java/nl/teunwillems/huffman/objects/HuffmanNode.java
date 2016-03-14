package nl.teunwillems.huffman.objects;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by Teun on 7-3-2016.
 */
public class HuffmanNode implements Comparable<HuffmanNode>, Comparator<HuffmanNode>, Serializable {
    public static final boolean LEFT = false;
    public static final boolean RIGHT = true;

    public static final char LEFT_CHAR = '0';
    public static final char RIGHT_CHAR = '1';

    private Character character;
    private int frequency;
    private HuffmanNode leftChild, rightChild;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(HuffmanNode leftChild) {
        this.leftChild = leftChild;
    }

    public HuffmanNode() {}

    public char getCharacter() {
        return character;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }

    public HuffmanNode getLeftChild() {
        return leftChild;
    }

    public HuffmanNode getRightChild() {
        return rightChild;
    }

    public void setLeftChild(HuffmanNode leftChild) {
        this.leftChild = leftChild;
        updateFrequency();
    }

    public void setRightChild(HuffmanNode rightChild) {
        this.rightChild = rightChild;
        updateFrequency();
    }
    public Map<Character, String> lookup(Character c, String current, Map<Character, String> map) {
        if (c == character) {
            map.put(c, current);
        }
        if (getLeftChild() != null) {
            getLeftChild().lookup(c, current + LEFT_CHAR, map);
        }
        if (getRightChild() != null) {
            getRightChild().lookup(c, current + RIGHT_CHAR, map);
        }
        return map;
    }

    public void updateFrequency() {
        frequency = leftChild.frequency + rightChild.frequency;
    }

    public int compareTo(HuffmanNode o) {
        Integer i = new Integer(frequency);
        Integer i2 = new Integer(o.frequency);
        return i.compareTo(i2);
    }

    @Override
    public int compare(HuffmanNode o1, HuffmanNode o2) {
        return o1.compareTo(o2);
    }

}
