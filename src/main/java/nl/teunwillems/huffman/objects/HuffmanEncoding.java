package nl.teunwillems.huffman.objects;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Teun on 7-3-2016.
 */
public class HuffmanEncoding implements Serializable {

    private Map<Character, Integer> frequencies;
    private boolean[][] encodingArray;

    public HuffmanEncoding(Map<Character, Integer> frequencies, String input, Map<Character, String> lookup) {
        this.frequencies = frequencies;
        encodingArray = new boolean[input.length()][];
        // Encoding to booleans
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            // Lookup character code inside map
            String characterLookup = lookup.get(c);
            // Convert result to array of booleans
            boolean[] convertResult = convertEncoding(characterLookup);
            encodingArray[i] = convertResult;
        }
    }

    public boolean[][] getEncodingArray() {
        return encodingArray;
    }

    public Map<Character, Integer> getFrequencies() {
        return frequencies;
    }

    public boolean[] convertEncoding(String l) {
        boolean[] result = new boolean[l.length()];
        char[] chars = l.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == HuffmanNode.LEFT_CHAR) {
                result[i] = HuffmanNode.LEFT;
            } else if (c == HuffmanNode.RIGHT_CHAR) {
                result[i] = HuffmanNode.RIGHT;
            }
        }
        return result;
    }
}
