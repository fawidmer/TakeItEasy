package takeiteasy.game;

import java.time.Duration;

import takeiteasy.game.players.ComputerPlayer;

public class MainApp {

	public static void main(String[] args) {

		Playmaster master = new Playmaster(Verbosity.verbose);
		master.addPlayer(new ComputerPlayer(Duration.ofSeconds(10), Verbosity.verbose));

		for (int i = 0; i < 1; i++) {

			master.runGame();
			master.reset();
		}

	}

}
