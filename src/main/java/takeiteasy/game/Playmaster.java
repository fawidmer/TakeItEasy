package takeiteasy.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import takeiteasy.game.cards.CardSet;
import takeiteasy.game.cards.PlayingCard;
import takeiteasy.game.players.Player;

public class Playmaster {

	private Map<Player, Playboard> players = new HashMap<>();
	private List<PlayingCard> unplacedCards;
	private List<PlayingCard> placedCards;
	private int turnNumber;

	public Playmaster() {
		initialize();
	}

	private void initialize() {
		unplacedCards = CardSet.getNew();
		placedCards = new ArrayList<>();
		turnNumber = 0;
	}

	public void addPlayer(Player player) {
		if (turnNumber != 0)
			throw new UnsupportedOperationException("Can only add players before the game starts.");
		players.put(player, new Playboard());
	}

	public void nextTurn() {
		if (turnNumber >= 19)
			throw new UnsupportedOperationException("Game is already finished");

		PlayingCard currentCard = CardSet.drawRandom(unplacedCards);
		if (!unplacedCards.remove(currentCard))
			throw new InternalError("Could not remove " + currentCard + " from " + unplacedCards);

		players.entrySet().stream().forEach(playerBoardSet -> {
			Pair<Integer, Integer> coordinates = playerBoardSet.getKey().decideMove(currentCard,
					playerBoardSet.getValue().copy());
			playerBoardSet.getValue().set(currentCard, coordinates.getLeft(), coordinates.getRight());
		});

		placedCards.add(currentCard);
		turnNumber++;
	}

	public void showBoards() {
		players.entrySet().stream().forEach(playerBoardSet -> System.out
				.println(playerBoardSet.getKey().getName() + ":\n" + playerBoardSet.getValue()));
	}

	public void runGame() {
		while (turnNumber < 19) {
			nextTurn();
			showBoards();
		}

		showResults();
	}

	private void showResults() {
		players.entrySet().stream().forEach(playerBoardSet -> System.out.println(
				playerBoardSet.getKey().getName() + ": " + BoardCalculations.getScore(playerBoardSet.getValue())));
	}

	public void reset() {
		initialize();
		for (Player player : players.keySet())
			players.put(player, new Playboard());

	}

}
