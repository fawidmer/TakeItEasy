package takeiteasy.game.players;

import org.apache.commons.lang3.tuple.Pair;

import takeiteasy.game.Playboard;
import takeiteasy.game.cards.PlayingCard;

/**
 * Interface to be implemented by every Player.
 * 
 * @author Fabio Widmer
 */
public interface Player {

	/**
	 * Main player method. Decides upon the next move given.
	 * 
	 * @param currentCard
	 *            The current {@link PlayingCard} to be placed.
	 * @param playboard
	 *            The current state of the {@link Playboard}.
	 * @return A {@link Pair} of {@link Integer} corresponding to the
	 *         coordinates to place the card.
	 */
	Pair<Integer, Integer> decideMove(PlayingCard currentCard, Playboard playboard);

	/**
	 * @return The name of the player.
	 */
	String getName();

}
