package com.shubham.zest_money;

import com.shubham.zest_money.game.GamePlay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Application {

  private static Scanner scanner = new Scanner(System.in);


  public static void main(String[] args) {
    gameInfo();
    GamePlay gamePlay = new GamePlay(scanner);
    gamePlay.start();
  }

  private static void gameInfo() {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("game_info.txt");
    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    String S = null;
    try {
      while ((S = reader.readLine()) != null) {
        System.out.println(S);
      }
    } catch (IOException e) {
      System.out.println("Unable to read game info file");
    }
  }

}
