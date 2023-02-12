import java.awt.Font;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;

public class Memories extends BasicGame {

	public Memories(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	Plateau p;
	Menu m;

	int t = 0;
	private Music music;
	private Sound sound;

	
	Image menu;

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub

		if (Menu.lien == 0) {
			if (!(p.JeuStart())) {

				m.DessineMenu(g);
			}
		} else if (Menu.lien == 1) {

			if (p.JeuTermine()) {

				p.MessagedeFin(g); // MESSAGE DE FIN

			}

			if (!(p.JeuTermine())) {

				
				g.drawString("SCORE : " + p.getScore(), 0, 1);
				if (t > 4000) {
					p.dessinerFond(gc, g); // retouner les cartes
					t = 0;

				} else {

					p.dessinerPlateau(gc, g);

				}

			}
		} else if (Menu.lien == 2) {

			m.DessineNoir(g);

			m.dessinerAide(g);

		}
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub

		m = new Menu();

		p = new Plateau();
		// menu = new Image("image/menu.png");
		

		// music et son
		music = new Music("image/world.ogg");
		music.setVolume(0.5f);
		music.loop();
		sound = new Sound("image/click1.ogg");

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub

		if (gc.getInput().isKeyPressed(Input.KEY_1) && Menu.lien == 0) { // pour appuyer 1 on doit etre dans le Menu qui
																			// est par defaut 0
			Menu.lien = 1;

		}
		if (gc.getInput().isKeyPressed(Input.KEY_2) && Menu.lien == 0) {
			Menu.lien = 2;

		}
		if (gc.getInput().isKeyPressed(Input.KEY_R)) { // RETOUR AU MENU
			Menu.lien = 0;

		}

		if (!(p.JeuTermine())) {

			Input inp = gc.getInput();
			t = t + delta;

			// generer un son au clic
			if (inp.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
				// p.Afficher();
				p.clicCase(inp.getMouseX(), inp.getMouseY());
				sound.play();

			}

			if (inp.isKeyPressed(Input.KEY_B)) {
				gc.exit();

			}

		} else {
			music.pause();
			if (gc.getInput().isKeyPressed(Input.KEY_B)) {
				gc.exit();
			}
		}

	}

}
