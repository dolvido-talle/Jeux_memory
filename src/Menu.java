
import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Menu {
	private Image img;
	private Image img1;

	static int lien = 0;

	public Menu() throws SlickException {

		img = new Image("image/menu.png");
		img1 = new Image("image/noir.png");

	}

	public void DessineMenu(Graphics g) {
		g.drawImage(img, 0, 0);
	}

	public void DessineNoir(Graphics g) {
		g.drawImage(img1, 0, 0);
	}

	public void dessinerAide(Graphics g) {
		Font font = new Font("jokerman", Font.BOLD, 40);
		TrueTypeFont ttf = new TrueTypeFont((java.awt.Font) font, true);

		font = new Font("calibri", Font.BOLD, 25);
		ttf = new TrueTypeFont(font, true);

		ttf.drawString(40, 140, "MEMORY CARD est un jeu ou le but est de trouver deux ", Color.white);

		ttf.drawString(100, 170, " cartes egales dans un plateau!!!", Color.white);

		ttf.drawString(150, 230, "Bonne chance!!!", Color.white);

	}
}
