package com.shubham.zest_money.game;

import com.shubham.zest_money.model.player.Player;
import com.shubham.zest_money.model.player.PlayerBattleResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BattleResult {
  private Map<Player, PlayerBattleResult> playerBattleResult;
  private final List<CardFightResult> cardFightResults;

  public BattleResult(List<CardFightResult> cardFightResults) {
    this.cardFightResults = cardFightResults;
  }

  public Map<Player, PlayerBattleResult> getPlayerBattleResult() {
    return this.playerBattleResult;
  }

  public void setPlayerBattleResult(Map<Player, PlayerBattleResult> playerBattleResult) {
    this.playerBattleResult = playerBattleResult;
  }

  public void evaluate() {
    HashMap<Player, PlayerBattleResult> playerBattleResult = new HashMap<>();
    this.cardFightResults.forEach(cardFightResult -> {
          Player winningPlayer = cardFightResult.getWinningSide();
          if (winningPlayer != null) {
            int goldGained = cardFightResult.getGoldFound();
            int expGained = cardFightResult.getExpGained();
            winningPlayer.setGold(winningPlayer.getGold() + goldGained);
            winningPlayer.setExperience(winningPlayer.getExperience() + expGained);
            PlayerBattleResult playerResult = playerBattleResult.getOrDefault((Object)winningPlayer, new PlayerBattleResult(winningPlayer));
            playerResult.setExpGained(playerResult.getExpGained() + expGained);
            playerResult.setGoldGained(playerResult.getGoldGained() + goldGained);
            playerResult.setCardsWon(playerResult.getCardsWon() + 1);
            playerBattleResult.put(winningPlayer, playerResult);
          }
        }
    );
    this.playerBattleResult = playerBattleResult;
  }
}
