import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Cartes {
	private int x;
	private int y;
	private int valeur;
	private Image img;

	public Cartes(int x, int y, Image img) { // constructeur permettant de créér une carte avec ses coordonéées et son
												// image;
		super();
		this.x = x;
		this.y = y;
		this.img = img;
	}

	/*
	 * Méthode pour réccuperer les attributs de notre carte et les modififiés.
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

	public static boolean memeValeur(Cartes c1, Cartes c2) { // pour vérifier si deux cartes on meme valeur
		return c1.valeur == c2.valeur;
	}

}
