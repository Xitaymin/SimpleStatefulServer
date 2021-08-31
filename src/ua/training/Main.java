package ua.training;

import java.io.IOException;

public class Main {
//    static Map<String, String> params = new HashMap<>();
    public static void main(String[] args) throws IOException {
        SimpleHttpServerLauncher simpleHttpServerLauncher = new SimpleHttpServerLauncher();
        simpleHttpServerLauncher.initial();
    }


}
