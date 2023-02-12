import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Cartes {
	private int x;
	private int y;
	private int valeur;
	private Image img;

	public Cartes(int x, int y, Image img) { // constructeur permettant de cr��r une carte avec ses coordon��es et son
												// image;
		super();
		this.x = x;
		this.y = y;
		this.img = img;
	}

	/*
	 * M�thode pour r�ccuperer les attributs de notre carte et les modififi�s.
	 * 
	 */
	public int getValeur() {
		return valeur;
	}

	public void setValeur(int valeur) {
		this.valeur = valeur;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void dessiner(Graphics g) { // methode pour dessiner notre carte avec une image;
		g.drawImage(img, x, y);

	}

	public static boolean memeValeur(Cartes c1, Cartes c2) { // pour v�rifier si deux cartes on meme valeur
		return c1.valeur == c2.valeur;
	}

}
