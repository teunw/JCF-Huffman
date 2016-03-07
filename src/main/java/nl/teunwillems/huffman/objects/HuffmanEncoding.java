package nl.teunwillems.huffman.objects;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/**
 * Created by Teun on 7-3-2016.
 */
public class HuffmanEncoding {

    private List<BitSet> search;
    private HuffmanNode node;

    public static HuffmanEncoding buildEncoding(HuffmanNode node, String input) {
        return new HuffmanEncoding(node, input);
    }

    private HuffmanEncoding(HuffmanNode node, String input) {
        this.node = node;
        search = new ArrayList<>(node.getFrequency());
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

}
