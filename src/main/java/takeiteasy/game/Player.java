package takeiteasy.game;

import org.apache.commons.lang3.tuple.Pair;

import takeiteasy.game.cards.PlayingCard;

public interface Player {

	Pair<Integer, Integer> decideMove(PlayingCard currentCard, Playboard playboard);

	String getName();

}
