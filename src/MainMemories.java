import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainMemories {
	public static void main(String[] args) throws SlickException {
		Memories monJeu = new Memories("Memories TALLE");
		AppGameContainer app = new AppGameContainer(monJeu);
		app.setShowFPS(false);
		// app.setDisplayMode(640, 480, false);
		app.start();

	}

}
