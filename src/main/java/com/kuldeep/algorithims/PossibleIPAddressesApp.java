package com.kuldeep.algorithims;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public class PossibleIPAddressesApp {
    public static void main(String[] args) {
        List<String> ips = new PossibleIPAddressesApp().validIPAddresses("3700100");
        System.out.println(ips.size());
        ips.forEach(System.out::println);
    }

    private boolean isValid(String part) {
        if(part.isEmpty()) return false;

        int value = Integer.parseInt(part);
        return value >= 0 && value <= 255
                && part.length() == Integer.toString(value).length(); // check trailing zeros
    }

    private String join(String[] parts) {
        return String.join(".", parts);
    }

    public List<String> validIPAddresses(String str) {
        List<String> validIps = new ArrayList<>();

        for (int i = 1; i < Math.min(str.length(), 4); i++ ) {
            String[] parts = new String[]{"","","",""};

            parts[0] = str.substring(0,i);
            if (!isValid(parts[0])) continue;

            for (int j = i + 1; j < i + Math.min(str.length() - i, 4); j++) {
                parts[1] = str.substring(i, j);
                if(!isValid(parts[1])) continue;

                for (int k = j + 1; k < j + Math.min(str.length() - j, 4); k++) {
                    parts[2] = str.substring(j, k);
                    parts[3] = str.substring(k);
                    if (isValid(parts[2]) && isValid(parts[3])) {
                        validIps.add(join(parts));
                    }
                }
            }

        }

        return validIps;
    }
}

//["1.9.216.80", "1.92.16.80", "1.92.168.0", "19.2.16.80", "19.2.168.0", "19.21.6.80", "19.21.68.0", "19.216.8.0", "192.1.6.80", "192.1.68.0", "192.16.8.0"]