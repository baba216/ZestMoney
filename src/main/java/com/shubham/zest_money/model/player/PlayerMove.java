package com.shubham.zest_money.model.player;

import com.shubham.zest_money.model.card.Card;

public class PlayerMove {
  private final Player player;
  private final Card card;

  public PlayerMove(Player player, Card card) {
    this.player = player;
    this.card = card;
  }

  public Player getPlayer() {
    return this.player;
  }

  public Card getCard() {
    return this.card;
  }
}
