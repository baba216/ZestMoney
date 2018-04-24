package com.shubham.zest_money.game;

import com.shubham.zest_money.model.player.Player;

public final class CardFightResult {
  private final Player winningSide;
  private final int goldFound;
  private final int expGained;

  public CardFightResult(Player winningSide, int goldFound, int expGained) {
    this.winningSide = winningSide;
    this.goldFound = goldFound;
    this.expGained = expGained;
  }

  public Player getWinningSide() {
    return this.winningSide;
  }

  public int getGoldFound() {
    return this.goldFound;
  }

  public int getExpGained() {
    return this.expGained;
  }
}
