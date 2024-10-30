package com.gerasimovas;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class AppMenu {
    public static final String BLACK_WHITE = "\u001B[37m\u001B[40m";
    public static final String YELLOW_BLACK = "\u001B[30m\u001B[43m";
    public static final String YELLOW = "\u001B[33m";
    private static final String MAIN_MENU_INFO = BLACK_WHITE +
            "╔══════════════╗\n" +
            "   MAIN MENU\n" +
            "╚══════════════╝\n" +
            "#1 Show device list\n" +
            "#2 Script for NEW devices\n" +
            "#3 Script for OLD devices\n" +
            YELLOW + "#0 - for close program";

    private boolean appIsActive = true;

    private Scanner scanner = new Scanner(System.in);

    private AdbDeviceList adbDeviceList = new AdbDeviceList();

    private int setMainMenuValue(String menuNumber) {
        try {
            int mainMenu = Integer.parseInt(menuNumber);
            if (mainMenu >= 0 && mainMenu <= 3) {
                return mainMenu;
            } else {
                System.out.println("E: Bad input");
            }
        } catch (NumberFormatException | NoSuchElementException e) {
            System.out.println("E: Bad input");
        }

        return -1;
    }

    public void startMenu() {
        while (appIsActive) {
            System.out.println(MAIN_MENU_INFO);

            int mainMenu = setMainMenuValue(scanner.next());

            if (mainMenu == 0){
                appIsActive = false;
                System.out.println("App is closed");
            }

            //device list
            if (mainMenu == 1){
                adbDeviceList.aaa();
            }



            //test
            System.out.println("#: " + mainMenu);
        }


    }

}
