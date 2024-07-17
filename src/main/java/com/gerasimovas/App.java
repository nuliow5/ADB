package com.gerasimovas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // Path to your adb executable
        String adbPath = "C:/adb";

        // ADB command to execute
        String command = "adb devices";

        List<String> deviceList = new ArrayList<>();

        // Execute the command
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
            Process process = processBuilder.start();

            // Capture the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.equals("List of devices attached")) {
                    continue;
                }

                if (takeDeviceName(line) != null) {
                    deviceList.add(takeDeviceName(line));
                }


            }
            //info
            System.out.println(deviceList);
            System.out.println(deviceList.size());

            //--info


            // Capture any errors
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);

            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String takeDeviceName(String s) {
        if (s.isEmpty()) {
            return null;
        }
        String[] deviceName = s.split("\t");
        return deviceName[0];
    }

}
