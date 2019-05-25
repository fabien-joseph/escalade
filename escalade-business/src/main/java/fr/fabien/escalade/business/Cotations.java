package fr.fabien.escalade.business;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cotations {
    public static final Map<Integer, String> cotations = new LinkedHashMap<>(30);

    static {
        cotations.put(1, "1");
        cotations.put(2, "2");
        cotations.put(3, "3");
        cotations.put(4, "4");
        cotations.put(5, "5a");
        cotations.put(6, "5b");
        cotations.put(7, "5c");
        cotations.put(8, "6a");
        cotations.put(9, "6a+");
        cotations.put(10, "6b");
        cotations.put(11, "6b+");
        cotations.put(12, "6c");
        cotations.put(13, "6c+");
        cotations.put(14, "7a");
        cotations.put(15, "7a+");
        cotations.put(16, "7b");
        cotations.put(17, "7b+");
        cotations.put(18, "7c");
        cotations.put(19, "7c+");
        cotations.put(20, "8a");
        cotations.put(21, "8a+");
        cotations.put(22, "8b");
        cotations.put(23, "8b+");
        cotations.put(24, "8c");
        cotations.put(25, "8c+");
        cotations.put(26, "9a");
        cotations.put(27, "9a+");
        cotations.put(28, "9b");
        cotations.put(29, "9b+");
        cotations.put(30, "9c");
    }

    public String convertIntString(Integer key) {
        if (key == null || key == 0)
            return ("0");
        return cotations.get(key);
    }
}
