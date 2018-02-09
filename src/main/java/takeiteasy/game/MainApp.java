package takeiteasy.game;

import takeiteasy.game.players.ComputerPlayer;

public class MainApp {

	public static void main(String[] args) {

		Playmaster master = new Playmaster();
		master.addPlayer(new ComputerPlayer(1));

		for (int i = 0; i < 1; i++) {

			master.runGame();
			master.reset();
		}

	}

}
