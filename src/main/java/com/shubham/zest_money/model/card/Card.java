package com.shubham.zest_money.model.card;

import com.shubham.zest_money.model.card.CardName;

import java.io.PrintStream;
import java.util.Random;

public abstract class Card implements Cloneable {

  private CardName name;
  private int elixir;
  private int hitPoint;
  private int level;
  private int damage;
  private int defense;

  public Card(CardName name, int level, int damage, int defense, int hitPoint, int elixir) {
    this.name = name;
    this.level = level;
    this.damage = damage;
    this.defense = defense;
    this.hitPoint = hitPoint;
    this.elixir = elixir;
  }

  public CardName name() {
    return this.name;
  }

  public int damage() {
    return this.damage;
  }

  public int defend() {
    return this.defense;
  }

  public int level() {
    return this.level;
  }

  public int hitPoiny() {
    return this.hitPoint;
  }

  public int elixir() {
    return this.elixir;
  }

  public final void changeDamageBy(int attack) {
    int newAttack = this.damage + attack;
    if (newAttack <= 0) {
      System.out.println("");
      throw new IllegalArgumentException("Invalid Attack Value");
    }
    this.damage += attack;
  }

  public void changeDefenseBy(int defend) {
    int newDefend = this.defense + defend;
    if (newDefend <= 0) {
      System.out.println("");
      throw new IllegalArgumentException("Invalid Defend Value");
    }
    this.defense = defend;
  }

  public void changeLevelBy(int level) {
    int newLevel = this.level + level;
    if (newLevel <= 0) {
      System.out.println();
      throw new IllegalArgumentException("");
    }
    this.level = newLevel;
  }

  public void changeHitPointBy(int hitPoint) {
    int newHitPoint = this.hitPoint + hitPoint;
    if (newHitPoint <= 0) {
      System.out.println("");
      throw new IllegalArgumentException("");
    }
    this.hitPoint = hitPoint;
  }

  public final Card fight(Card opponentCard) {
    System.out.println("\n\n Fight Between Card:" + (Object) this.name + " and Card: "
        + (Object) opponentCard.name);
    Random random = new Random();
    while (this.hitPoint >= 1 && opponentCard.hitPoint >= 1) {
      int netDamage;
      int firstPlayRandomOp = random.nextInt(1) + 1;
      int secondPlayerRandomOp = random.nextInt(1) + 1;
      if (firstPlayRandomOp == 1 && secondPlayerRandomOp == 2) {
        System.out.println(
            "\n\n" + (Object) this.name + " card opted for damage: " + this.damage + " == "
                + (Object) this.name + " opted for defense: " + opponentCard.defense);
        netDamage = this.damage - opponentCard.defense;
        if (netDamage <= 0)
          continue;
        opponentCard.hitPoint -= netDamage;
        continue;
      }
      if (firstPlayRandomOp == 1 && secondPlayerRandomOp == 1) {
        System.out.println(
            "\n\n" + (Object) this.name + " card opted damage: " + this.damage + " == "
                + (Object) this.name + " card opted damage: " + opponentCard.damage);
        this.hitPoint -= opponentCard.damage;
        opponentCard.hitPoint -= this.damage;
        continue;
      }
      if (firstPlayRandomOp != 2 || secondPlayerRandomOp != 1)
        continue;
      System.out.println(
          "\n\n" + (Object) this.name + " card opted defense: " + this.damage + " == "
              + (Object) this.name + " card opted damage: " + opponentCard.defense);
      netDamage = opponentCard.damage - this.defense;
      if (netDamage <= 0)
        continue;
      this.hitPoint -= netDamage;
    }
    if (this.hitPoint <= 0 && opponentCard.hitPoint <= 0) {
      return null;
    }
    return this.hitPoint > 0 ? this : opponentCard;
  }

  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
