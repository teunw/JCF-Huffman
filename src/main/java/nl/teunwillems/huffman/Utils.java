package nl.teunwillems.huffman;

import java.util.*;

/**
 * Created by Teun on 7-3-2016.
 */
public class Utils {

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue( Map<K, V> map )
    {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        Collections.sort( list, (o1, o2) -> (o1.getValue()).compareTo( o2.getValue() ));

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list)
        {
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

}
