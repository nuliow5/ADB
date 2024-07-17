package com.gerasimovas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AdbPushAllDevices {

    public static void main(String[] args) {
        // Path to your adb executable
        String adbPath = "path/to/adb"; // Replace with your ADB path

        // File to push
        String localFilePath = "path/to/local/file.txt"; // Replace with the path to your local file

        // Destination path on the device
        String deviceFilePath = "/sdcard/file.txt"; // Replace with the desired path on the device

        // Get list of connected devices
        List<String> devices = getConnectedDevices(adbPath);

        // Push file to each device
        for (String device : devices) {
            pushFileToDevice(adbPath, device, localFilePath, deviceFilePath);
        }
    }

    private static List<String> getConnectedDevices(String adbPath) {
        List<String> devices = new ArrayList<>();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(adbPath, "devices");
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.endsWith("\tdevice")) {
                    String device = line.split("\t")[0];
                    devices.add(device);
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Failed to list devices. Exit code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return devices;
    }

    private static void pushFileToDevice(String adbPath, String device, String localFilePath, String deviceFilePath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    adbPath, "-s", device, "push", localFilePath, deviceFilePath
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("File pushed successfully to device " + device);
            } else {
                System.err.println("Failed to push file to device " + device + ". Exit code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

