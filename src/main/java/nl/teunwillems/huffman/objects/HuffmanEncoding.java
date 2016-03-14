package nl.teunwillems.huffman.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Teun on 7-3-2016.
 */
public class HuffmanEncoding implements Serializable {

    private HuffmanNode node;
    private List<List<Boolean>> encoding;

    public HuffmanEncoding(HuffmanNode node, String input, Map<Character, String> lookup) {
        this.node = node;
        encoding = new ArrayList<>(input.length());
        for (Character c : input.toCharArray()) {
            String characterLookup = lookup.get(c);
            List<Boolean> convertResult = convertEncoding(characterLookup);
            encoding.add(convertResult);
        }
    }

    public HuffmanNode getNode() {
        return node;
    }

    public List<List<Boolean>> getEncoding() {
        return encoding;
    }

    public List<Boolean> convertEncoding(String l) {
        ArrayList<Boolean> result = new ArrayList<>(l.length());
        for (char c : l.toCharArray()) {
            if (c == HuffmanNode.LEFT_CHAR) {
                result.add(HuffmanNode.LEFT);
            } else if (c == HuffmanNode.RIGHT_CHAR) {
                result.add(HuffmanNode.RIGHT);
            }
        }
        return result;
    }
}
