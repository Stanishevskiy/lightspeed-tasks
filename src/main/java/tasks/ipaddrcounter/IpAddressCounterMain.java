package tasks.ipaddrcounter;

import tasks.ipaddrcounter.utils.ZipFileUtils;

import java.io.IOException;

public class IpAddressCounterMain {

    public static void main(String[] args) {
        String zipFilePath = "ip_addresses.zip";    // should change to correct path_to_file
        try {
            long uniqueCount = ZipFileUtils.countUniqueIPAddresses(zipFilePath);
            System.out.println("Number of unique IP addresses: " + uniqueCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
