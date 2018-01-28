package takeiteasy.game;

public class MainApp {

	public static void main(String[] args) {

		Playmaster master = new Playmaster();
		master.addPlayer(new HumanPlayer("Fabio"));

		master.runGame();

	}

}
