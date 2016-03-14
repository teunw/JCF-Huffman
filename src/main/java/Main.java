import nl.teunwillems.huffman.encoders.HuffmanDecoder;
import nl.teunwillems.huffman.encoders.HuffmanEncoder;
import nl.teunwillems.huffman.objects.HuffmanEncoding;
import nl.teunwillems.huffman.objects.HuffmanNode;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Teun on 7-3-2016.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // <editor-fold desc="Parameter specification">
        System.out.println("Enter the path of the file you want to encode or decode");
        String fileInput = reader.readLine();
        if (Objects.equals(fileInput, ""))
            fileInput = "C:\\Users\\Teun\\Documents\\LoremIpsum.txt";

        System.out.println("Opening " + fileInput);
        File file = new File(fileInput);
        if (!file.exists()) throw new FileNotFoundException("Could not find file");

        System.out.println("Specify encoding or decoding (e/d)");
        String action = reader.readLine();
        System.out.println((Objects.equals(action, "e")) ? "e" : "d");

        // </editor-fold>
        if (action.startsWith("e")) {
            // Read text file
            String fileContent = FileUtils.readFileToString(file);

            // Encode result
            HuffmanEncoder encoder = new HuffmanEncoder(fileContent);
            encoder.buildFrequencyQueue();
            HuffmanNode finalNode = encoder.buildTree();

            Map<Character, String> lookup = encoder.lookup();
            HuffmanEncoding encoding = new HuffmanEncoding(finalNode, fileContent, lookup);

            // Write output
            System.out.println("Specify output file");
            String output = reader.readLine();
            if (Objects.equals(output, ""))
                output = "C:\\Users\\Teun\\Documents\\HuffmanEncoding.huff";
            System.out.println("Output " + output);

            // Save encoding
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(output));
            objectOutputStream.writeObject(encoding);
            objectOutputStream.flush();
            objectOutputStream.close();
        } else if (action.startsWith("d")) {
            // Read object input
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            HuffmanEncoding encoding = (HuffmanEncoding) objectInputStream.readObject();
            HuffmanDecoder decoder = new HuffmanDecoder(encoding);
            decoder.processResult();

            // Save result
            System.out.println("Where to save result?");
            String decoded = reader.readLine();
            if (Objects.equals(decoded, ""))
                decoded = "C:\\Users\\Teun\\Documents\\Decoded.txt";

            System.out.println("Saving to " + decoded);
            File resultFile = new File(decoded);
            FileUtils.write(resultFile, decoder.getResult());
            System.out.println("Done");
        } else {
            System.out.println("Invalid action");
            main(new String[]{});
        }
    }

}
