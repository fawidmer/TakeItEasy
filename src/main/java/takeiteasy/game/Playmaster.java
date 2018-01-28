package takeiteasy.game;

import java.util.ArrayList;
import java.util.List;

import takeiteasy.game.cards.CardSet;
import takeiteasy.game.cards.PlayingCard;

public class Playmaster {

	private List<Player> players = new ArrayList<>();
	private List<PlayingCard> unplacedCards = CardSet.getNew();
	private List<PlayingCard> placedCards = new ArrayList<>();
	private int turnNumber = 0;

	public void nextTurn() {
		if (turnNumber >= 19)
			throw new UnsupportedOperationException("Game is already finished");

		PlayingCard currentCard = CardSet.drawRandom(unplacedCards);
		players.stream().forEach(player -> player.decideAndPerform(currentCard));
		placedCards.add(currentCard);
		turnNumber++;
	}

	public void addPlayer(Player player) {
		if (turnNumber != 0)
			throw new UnsupportedOperationException("Can only add players before the game starts.");
		players.add(player);
	}

	public void showBoards() {
		players.stream().forEach(player -> player.showBoard());
	}

}
