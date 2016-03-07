package nl.teunwillems.huffman.objects;

import java.util.Comparator;

/**
 * Created by Teun on 7-3-2016.
 */
public class HuffmanNode implements Comparable<HuffmanNode>, Comparator<HuffmanNode> {

    private char character;
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

    public HuffmanNode find(char c) {
        if (getRightChild() != null && getRightChild().getCharacter() == c) {
            return this;
        }else{
            if (getLeftChild() != null)
                return getLeftChild().find(c);
        }
        return null;
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
