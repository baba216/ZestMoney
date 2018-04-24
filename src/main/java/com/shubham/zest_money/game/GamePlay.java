package com.shubham.zest_money.game;

import com.shubham.zest_money.exception.IllegalGameException;
import com.shubham.zest_money.model.card.Card;
import com.shubham.zest_money.model.card.CardName;
import com.shubham.zest_money.model.player.Player;
import com.shubham.zest_money.model.player.PlayerBattleResult;
import com.shubham.zest_money.model.player.PlayerMove;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public final class GamePlay {

  private Scanner scanner;
  private Player firstPlayer;
  private Player secondPlayer;
  private List<PlayerMove> moveHistory;
  private Long time;

  public GamePlay(Scanner scanner) {
    this.scanner = scanner;
    this.moveHistory = new LinkedList<PlayerMove>();
  }

  public void start() {
    this.firstPlayer = this.getPlayer();
    this.secondPlayer = this.getPlayer();
    int chance = 3;
    ArrayList<CardFightResult> cardFightResults = new ArrayList<CardFightResult>(3);
    this.time = System.currentTimeMillis();
    ElixirControl elixirControl = new ElixirControl(this.firstPlayer, this.secondPlayer);
    elixirControl.managerElixir();
    while (--chance >= 0) {
      Card playerOneCard = this.selectCard(this.firstPlayer);
      Card playerTwoCard = this.selectCard(this.secondPlayer);
      this.firstPlayer.setElixir(this.firstPlayer.getElixir() - playerOneCard.elixir());
      this.secondPlayer.setElixir(this.secondPlayer.getElixir() - playerTwoCard.elixir());
      PlayerMove firstPlayerMove = new PlayerMove(this.firstPlayer, playerOneCard);
      PlayerMove secondPlayerMove = new PlayerMove(this.secondPlayer, playerTwoCard);
      this.moveHistory.add(firstPlayerMove);
      this.moveHistory.add(secondPlayerMove);
      CardFightResult cardFightResult = this.fight(firstPlayerMove, secondPlayerMove);
      cardFightResults.add(cardFightResult);
    }
    elixirControl.shutDown();
    BattleResult battleResult = new BattleResult(cardFightResults);
    battleResult.evaluate();
    if (battleResult.getPlayerBattleResult() == null) {
      System.out.println("Processing Error");
      throw new IllegalGameException("Invalid Game ");
    }
    this.time = this.time - System.currentTimeMillis();
    this.showFinalBattleResult(battleResult.getPlayerBattleResult(), this.time);
  }

  private void showFinalBattleResult(Map<Player, PlayerBattleResult> playerBattleResult,
      Long time) {
    System.out.println("\n\n\n\n ========= Result =========\n");
    playerBattleResult.forEach((player, playerBattleResult1) -> {
      System.out.println("player Name:" + player.getName());
      System.out.println("Card Won:" + playerBattleResult1.getCardsWon());
      System.out.println("Gold Gained:" + playerBattleResult1.getGoldGained());
      System.out.println("Experience Gained:" + playerBattleResult1.getExpGained());
      System.out.println("\n====\n");
    });
  }

  private CardFightResult fight(PlayerMove firstPlayerMove, PlayerMove secondPlayerMove) {
    Card winingCard = firstPlayerMove.getCard().fight(secondPlayerMove.getCard());
    if (winingCard == firstPlayerMove.getCard()) {
      return new CardFightResult(this.firstPlayer, 10, 10);
    }
    if (winingCard == secondPlayerMove.getCard()) {
      return new CardFightResult(this.secondPlayer, 10, 10);
    }
    return new CardFightResult(null, 0, 0);
  }

  private Card selectCard(Player player) {
    System.out.println("\n=========== player: " + player.getName() + " ========\n");
    System.out.println(" Hi " + player.getName()
        + " !! Which card you would like to you choose. Please select the id of the card. You have:");
    player.getCards().forEach(card -> {
      System.out.println(card.name());
    });
    while (true) {
      System.out.println("Please select your card. Please select the id:");
      try {
        int cardId = Integer.parseInt(this.scanner.next());
        System.out.println(
            player.getName() + " have selected card:" + CardName.getNameById((int) cardId));
        Card cardSelected = CardName.cardById.get(cardId);
        int playerElixir = player.getElixir();
        if (cardSelected.elixir() > playerElixir) {
          System.out.println(
              "You are running short on elixir. Please select some other card. Your current elixir:"
                  + player.getElixir());
          continue;
        }
        return cardSelected;
      } catch (Exception e) {
        System.out.println("You have provided an invalid input. Please re-try");
      }
    }
  }

  private void assignCardsToPlayer(Player player) {
    System.out.println(
        "\nHi !! " + player.getName() + ", Please select any 3 cards. Your options are:\n");
    for (CardName cardName : CardName.values()) {
      System.out.println((Object) cardName);
    }
    System.out.println("You can select the id of cards.....");
    int numberOfCards = 3;
    ArrayList<Card> cards = new ArrayList<Card>(3);
    while (numberOfCards != 0) {
      System.out.println("\n Please select the card:");
      String id = this.scanner.next();
      Card cardSelected = (Card) CardName.cardById.get(Integer.parseInt(id));
      System.out.println("You have selected : " + (Object) cardSelected.name());
      System.out.println("Continue ? (Press Y/y) :");
      String shouldContinue = this.scanner.next();
      if (!shouldContinue.equalsIgnoreCase("Y"))
        continue;
      if (cards.contains((Object) cardSelected)) {
        System.out.println("You have already selected this card.");
        continue;
      }
      cards.add(cardSelected);
      --numberOfCards;
    }
    player.setCards(cards);
  }

  private Player getPlayer() {
    System.out.println("\n\n==== player Setting ====\n\n");
    System.out.println("Enter you name:");
    String name = this.scanner.next();
    Player player = new Player(name);
    this.assignCardsToPlayer(player);
    return player;
  }
}
