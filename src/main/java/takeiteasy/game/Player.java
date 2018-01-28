package takeiteasy.game;

import takeiteasy.game.cards.PlayingCard;

public interface Player {

	void decideAndPerform(PlayingCard currentCard);

	void showBoard();

}
