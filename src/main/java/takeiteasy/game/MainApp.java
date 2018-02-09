package takeiteasy.game;

import takeiteasy.game.players.ComputerPlayer;

public class MainApp {

	public static void main(String[] args) {

		Playmaster master = new Playmaster(Verbosity.silent);
		master.addPlayer(new ComputerPlayer(10000, Verbosity.silent));

		for (int i = 0; i < 10; i++) {

			master.runGame();
			master.reset();
		}

	}

}
