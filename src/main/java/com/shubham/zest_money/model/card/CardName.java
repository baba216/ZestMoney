package com.shubham.zest_money.model.card;

import com.shubham.zest_money.model.card.Archer;
import com.shubham.zest_money.model.card.Barbarian;
import com.shubham.zest_money.model.card.Card;
import com.shubham.zest_money.model.card.Goblin;
import com.shubham.zest_money.model.card.Golem;
import com.shubham.zest_money.model.card.Knight;
import com.shubham.zest_money.model.card.Musketeer;
import com.shubham.zest_money.model.card.SwordsMan;
import com.shubham.zest_money.model.card.Witch;
import com.shubham.zest_money.model.card.Wizard;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public enum CardName {
  KNIGHT(1, "Knight"), SWORDSMAN(2, "Swordsman"), ARCHER(3, "Archer"), WIZARD(4, "Wizard"), WITCH(5,
      "Witch"), GOBLIN(6, "GOBLIN"), BARBARIAN(7, "Barbarian"), MUSKETEER(8, "MUSKETEER"), GOLEM(9,
      "GOLEM");

  private String card;
  private int id;
  public static Map<Integer, Card> cardById;
  public static Map<Integer, CardName> cardNameById;

  private CardName(int id, String card) {
    this.card = card;
    this.id = id;
  }

  public String getCard() {
    return this.card;
  }

  public int getId() {
    return this.id;
  }

  public static String getNameById(int id) {
    return cardNameById.get(id).getCard();
  }

  public String toString() {
    return "Card = '" + this.card + '\'' + ", Id= " + this.id;
  }

  static {
    cardById =
        Arrays.stream(CardName.values()).collect(Collectors.toMap(CardName::getId, cardName -> {
          switch (cardName.id) {
            case 1: {
              return new Knight();
            }
            case 2: {
              return new SwordsMan();
            }
            case 3: {
              return new Archer();
            }
            case 4: {
              return new Wizard();
            }
            case 5: {
              return new Witch();
            }
            case 6: {
              return new Goblin();
            }
            case 7: {
              return new Barbarian();
            }
            case 8: {
              return new Musketeer();
            }
            case 9: {
              return new Golem();
            }
          }
          throw new IllegalArgumentException("Invalid Player");
        }));
    cardNameById = Arrays.stream(CardName.values())
        .collect(Collectors.toMap(CardName::getId, Function.identity()));
  }
}
