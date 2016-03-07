import com.sun.javaws.exceptions.InvalidArgumentException;
import nl.teunwillems.huffman.encoders.HuffmanDecoder;
import nl.teunwillems.huffman.encoders.HuffmanEncoder;
import nl.teunwillems.huffman.objects.HuffmanEncoding;
import org.apache.commons.io.FileUtils;

import java.io.*;

/**
 * Created by Teun on 7-3-2016.
 */
public class Main {

    public static void main(String[] args) throws IOException, InvalidArgumentException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the path of the file you want to buildNode or decode");
        String fileInput = reader.readLine();

        File file = new File(fileInput);
        if (!file.exists()) throw new FileNotFoundException("Could not find file");

        System.out.println("Specify encoding or decoding (e/d)");
        String action = reader.readLine();

        String fileContent = FileUtils.readFileToString(file);

        if (action.startsWith("e")) {
            HuffmanEncoder encoder = new HuffmanEncoder(fileContent);
            encoder.buildNode();
            HuffmanEncoding encoding = HuffmanEncoding.buildEncoding(encoder.processNodes(), fileContent);
        } else if (action.startsWith("d")) {
            HuffmanDecoder decoder = new HuffmanDecoder(fileContent);
        } else {
            System.out.println("Invalid action");
            main(new String[]{});
        }
    }

}
