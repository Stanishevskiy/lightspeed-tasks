package tasks.ipaddrcounter.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.zip.ZipInputStream;

public class ZipFileUtils {

    private ZipFileUtils() { }

    public static long countUniqueIPAddresses(String filePath) throws IOException {
        BitSet bitSetPositive = new BitSet(Integer.MAX_VALUE);
        BitSet bitSetNegative = new BitSet(Integer.MAX_VALUE);

        int count = 0;
        try (FileInputStream fis = new FileInputStream(filePath);
             ZipInputStream zis = new ZipInputStream(fis);
             BufferedReader reader = new BufferedReader(new InputStreamReader(zis))) {

            zis.getNextEntry();

            String line;
            while ((line = reader.readLine()) != null) {
                count++;
                if (count % 100000000 == 0) {
                    System.out.println(count);
                }
                int ipInt = ipToInt(line);
                if (ipInt >= 0) bitSetPositive.set(ipInt);
                else bitSetNegative.set(-ipInt);
            }
        }

        return (bitSetPositive.cardinality() + bitSetNegative.cardinality());
    }

    private static int ipToInt(String ipAddress) {
        String[] octets = ipAddress.split("\\.");

        return Integer.parseInt(octets[0]) << 24 |
                Integer.parseInt(octets[1]) << 16 |
                Integer.parseInt(octets[2]) << 8 |
                Integer.parseInt(octets[3]);
    }
}
