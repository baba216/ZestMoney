package com.shubham.zest_money.game;

import com.shubham.zest_money.exception.IllegalGameException;
import com.shubham.zest_money.model.player.Player;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class is responsible to control the elixir level for player in a game
 */
public final class ElixirControl {

  private final Player firstPlayer;
  private final Player secondPlayer;

  // using double lock mechanism so as to make sure that elixir control for both the player starts at same time
  private AtomicBoolean firstPlayerLock = new AtomicBoolean(false);
  private AtomicBoolean secondPlayerLock = new AtomicBoolean(false);

  /**
   * Method to kill Elixir Control
   */
  public void shutDown() {
    System.out.println("Going to Kill Elixir Controller");
    this.firstPlayerLock.set(Boolean.FALSE);
    this.secondPlayerLock.set(Boolean.FALSE);
  }

  public ElixirControl(Player firstPlayer, Player secondPlayer) {
    this.firstPlayer = firstPlayer;
    this.secondPlayer = secondPlayer;
  }

  public void managerElixir() {
    Runnable firstPlayerElixirRunnable = () -> {
      // only when the both the locks have been acquired then only start the elixir management of Player
      this.firstPlayerLock.set(Boolean.TRUE);
      while (this.firstPlayerLock.get()) {
        while (this.secondPlayerLock.get()) {
          int firstPlayerElixir = this.firstPlayer.getElixir();
          if (firstPlayerElixir < 10) {
            this.firstPlayer.setElixir(++firstPlayerElixir);
          }
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            System.out.println("Killing First player Elixir Controller");
            throw new IllegalGameException("Processing Exception");
          }
        }
      }
    };
    Runnable secondPlayerRunnable = () -> {
      // only when the both the locks have been acquired then only start the elixir management of Player
      this.secondPlayerLock.set(Boolean.TRUE);
      while (this.secondPlayerLock.get()) {
        while (this.firstPlayerLock.get()) {
          int secondPlayerElixir = this.secondPlayer.getElixir();
          if (secondPlayerElixir < 10) {
            this.secondPlayer.setElixir(++secondPlayerElixir);
          }
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            System.out.println("Killing Second player Elixir Controller");
            throw new IllegalGameException("Processing Exception");
          }
        }
      }
    };
    Thread firstPlayerElixirController = new Thread(firstPlayerElixirRunnable);
    Thread secondPlayerElixirController = new Thread(secondPlayerRunnable);
    firstPlayerElixirController.start();
    secondPlayerElixirController.start();
  }
}

