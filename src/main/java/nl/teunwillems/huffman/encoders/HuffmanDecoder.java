package nl.teunwillems.huffman.encoders;

import nl.teunwillems.huffman.objects.HuffmanEncoding;
import nl.teunwillems.huffman.objects.HuffmanNode;

/**
 * Created by Teun on 7-3-2016.
 */
public class HuffmanDecoder {

    private HuffmanEncoding huffmanEncoding;
    private String result;

    public HuffmanDecoder(HuffmanEncoding encoding){
        this.huffmanEncoding = encoding;
        this.result = "";
    }

    public void processResult() {
        // Regenerate the tree from frequency encoding
        TreeGenerator treeGenerator = new TreeGenerator(huffmanEncoding.getFrequencies());
        HuffmanNode rootNode = treeGenerator.buildTree();
        // Decode characters from boolean path
        for (boolean[] treePath : huffmanEncoding.getEncodingArray()) {
            HuffmanNode node = rootNode;
            for (boolean b : treePath) {
                // Go to next node, depending on the boolean
                node = (b) ? node.getRightChild() : node.getLeftChild();
            }
            // Append character to result
            result += node.getCharacter();
        }
    }

    public String getResult() {
        if (result == null) processResult();
        return result;
    }
}
