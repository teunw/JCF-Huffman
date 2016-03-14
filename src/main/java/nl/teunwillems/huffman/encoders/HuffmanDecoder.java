package nl.teunwillems.huffman.encoders;

import nl.teunwillems.huffman.objects.HuffmanEncoding;
import nl.teunwillems.huffman.objects.HuffmanNode;

import java.util.List;

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
        HuffmanNode rootNode = huffmanEncoding.getNode();
        for (List<Boolean> treePath : huffmanEncoding.getEncoding()) {
            HuffmanNode node = rootNode;
            for (boolean b : treePath) {
                node = (b) ? node.getRightChild() : node.getLeftChild();
            }
            result += node.getCharacter();
        }
    }

    public String getResult() {
        if (result == null) processResult();
        return result;
    }
}
