package com.shubham.zest_money.model.player;

import com.shubham.zest_money.model.card.Card;
import java.util.List;

/**
 * Main Player class which sets up the Player object
 */
public class Player {
  private final String name;
  private List<Card> cards; // battle deck
  private int gold; // gold with player
  private int level;
  private int experience;
  private volatile int elixir; // elixir player have in a game

  public Player(String name) {
    this.name = name;
    this.gold = 1000;
    this.level = 1;
    this.elixir = 10;
    this.experience = 0;
  }

  public String getName() {
    return this.name;
  }

  public List<Card> getCards() {
    return this.cards;
  }

  public void setCards(List<Card> cards) {
    this.cards = cards;
  }

  public int getGold() {
    return this.gold;
  }

  public void setGold(int gold) {
    this.gold = gold;
  }

  public int getLevel() {
    return this.level;
  }

  public int getElixir() {
    return this.elixir;
  }

  public void setElixir(int elixir) {
    this.elixir = elixir;
  }

  public int getExperience() {
    return this.experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
    if (this.experience % 1000 == 0) {
      ++this.level;
    }
  }
}
