package com.shubham.zest_money.model.player;

public class PlayerBattleResult {
  private final Player player;
  private Integer cardsWon;
  private int goldGained;
  private int expGained;

  public PlayerBattleResult(Player player) {
    this.player = player;
    this.cardsWon = 0;
    this.goldGained = 0;
    this.expGained = 0;
  }

  public Integer getCardsWon() {
    return this.cardsWon;
  }

  public void setCardsWon(Integer cardsWon) {
    this.cardsWon = cardsWon;
  }

  public Player getPlayer() {
    return this.player;
  }

  public int getGoldGained() {
    return this.goldGained;
  }

  public void setGoldGained(int goldGained) {
    this.goldGained = goldGained;
  }

  public int getExpGained() {
    return this.expGained;
  }

  public void setExpGained(int expGained) {
    this.expGained = expGained;
  }
}
