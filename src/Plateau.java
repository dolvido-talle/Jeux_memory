
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class Plateau {
	private Image img = new Image("image/black.png");
	private int compteur = 0;// pour compter le nombre de fois que je clique sur une carte du plateau.
	private Cartes[] carteselectionne = new Cartes[2]; // creation des 2 cartes selectionnees.
	private int[] caseSelect = new int[2];// //tableau pour stocker les indices des cases sélectionnées.

	Cartes cartes[][] = new Cartes[4][4]; // on créé notre matrice de 16 cartes.
	Image imgfond[][] = new Image[4][4]; //// on créé notre matrice de 16 images.
	private int score = 0;

	public void dessinerPlateau(GameContainer gc, Graphics g) {// Methode pour dessiner le fond des cartes sur le plateau

		int a = 0, b = 0;
		for (int i = 10; i <= (gc.getWidth() - 150); i += 160) { // 160 ecart entre les cartes sur la ligne.
			b = 0;
			for (int j = 15; j <= (gc.getHeight() - 105); j += 120) { // 120 ecart sur colonne

				g.drawImage(imgfond[a][b], i, j);
				b++;
			}
			a++;
		}

	}

	public Plateau() throws SlickException {// quand on cree le plateau, on genere automatiquemment les cartes
		/*
		 * String [] pays =
		 * {"image/pays1.png","image/pays2.png","image/pays3.png","image/pays4.png",
		 * "image/pays5.png","image/pays6.png",
		 * "image/pays7.png","image/pays8.png","image/pays9.png","image/pays10.png",
		 * "image/pays11.png","image/pays12.png",
		 * "image/pays13.png","image/pays14.png","image/pays15.png","image/pays16.png"};
		 */
		int a = 0, b = 0;// ,i,j;
		boolean test;
		this.carteselectionne[0] = null;
		this.carteselectionne[1] = null;
		caseSelect[0] = -1; // ce n'est pas nul car ce n'est pas une référence
		caseSelect[1] = -1;
		int[] indiceAleatoire = new int[16];

		do {
			test = true;
			for (int i = 0; i < indiceAleatoire.length; i++) {
				indiceAleatoire[i] = (int) (Math.random() * 16) + 1;// je rempli mon tableau de [0,16]
			}
			for (int i = 0; i < indiceAleatoire.length; i++) { // je place mes images de facon aleatoires sur une ligne
				for (int j = 0; j < indiceAleatoire.length; j++) {
					if (indiceAleatoire[i] == indiceAleatoire[j] && i != j) { // on genère les indices aleatoires de
						// facon à ne pas avoir de meme indices
						// successivement.
						test = false;
						break;
					}
				}
				if (test == false)
					break;
			}
		} while (test == false);

		int k = 0;
		for (int i = 10; i <= (640 - 150); i += 160) {
			b = 0;

			for (int j = 15; j <= (480 - 105); j += 120) {
				Image img = new Image("image/pays" + (indiceAleatoire[k]) + ".png");
				/*
				 * ici pour chaque image je l'associe à une carte et j'attribu une meme valeur
				 * aux cartes qui ont meme les images ou se ressemblant .
				 */

				this.cartes[a][b] = new Cartes(i, j, img);
				if (indiceAleatoire[k] == 1 || indiceAleatoire[k] == 9)
					this.cartes[a][b].setValeur(1);
				if (indiceAleatoire[k] == 2 || indiceAleatoire[k] == 10)
					this.cartes[a][b].setValeur(2);
				if (indiceAleatoire[k] == 3 || indiceAleatoire[k] == 11)
					this.cartes[a][b].setValeur(3);
				if (indiceAleatoire[k] == 4 || indiceAleatoire[k] == 12)
					this.cartes[a][b].setValeur(4);
				if (indiceAleatoire[k] == 5 || indiceAleatoire[k] == 13)
					this.cartes[a][b].setValeur(5);
				if (indiceAleatoire[k] == 6 || indiceAleatoire[k] == 14)
					this.cartes[a][b].setValeur(6);
				if (indiceAleatoire[k] == 7 || indiceAleatoire[k] == 15)
					this.cartes[a][b].setValeur(7);
				if (indiceAleatoire[k] == 8 || indiceAleatoire[k] == 16)
					this.cartes[a][b].setValeur(8);
				this.imgfond[a][b] = new Image("image/coupe.png"); // ici je place juste les images de face.
				k++;
				b++;
			}
			a++;
		}
	}

	public void dessinerFond(GameContainer gc, Graphics g) { // methode permettant de dessiner les images.
		int a = 0, b = 0;
		Image img2 = null;
		try {
			img2 = new Image("image/coupe.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 10; i <= (gc.getWidth() - 150); i += 160) {
			b = 0;
			for (int j = 15; j <= (gc.getHeight() - 105); j += 120) {
				if (imgfond[a][b].equals(img)) { // si les images des pays sont les memes que les images noirs on
					// dessine les images noirs en fond.

					g.drawImage(img, i, j); // on met l'image noir si les 2 cartes séléctionnées sont egales.
					b++;

				} else {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					imgfond[a][b] = img2; // on remet les images de face
					b++;
				}
			}
			a++;
		}
	}

	public void dessinerCartes(Graphics g) { // methode pour dessiner nos cartes avec une image sur le plateau
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.cartes[i][j].dessiner(g);
			}
		}

	}

	public int[] quelleCase(int x, int y) { // pour savoir la position de la case ou on a cliqué en fonction du clic.
		int a = 0, b; /*
						 * on reccupère les abscisses et ordonées d'un clique(point) et on verifie si le
						 * clique est contenu dans les dimensions de la carte.
						 */

		for (int i = 10; i <= (640 - 150); i += 160) {
			a++;
			b = 0;
			for (int j = 15; j <= (480 - 105); j += 120) {
				b++;
				if ((i < x && x < (140 + i)) && (j < y && y <= (82 + j))) {
					int[] t = { a, b }; // ceci me permet de stocker les cordonnées (i,j) de ma case.
					return t;

				}

			}
		}
		return null;
	}

	public int[] getPositionCarte(int valeur) { // methode pour retourner la position de la carte en fonction de sa
		// valeur
		int[] tab = new int[4]; // tableau contenant les lignes et colonnes des cases possedant la valeur passee
		// en parametre
		int cpt = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (this.cartes[i][j].getValeur() == valeur) { // methode pour retourner la position de la carte en
					// fonction de sa valeur
					// remplir les valeurs de tab en fonction des indices ou poistion i et j de la
					// carte
					tab[cpt] = i;
					tab[cpt + 1] = j;
					cpt += 2;

					/*
					 * il faut garder à l'esprit ici qu'on veut savoir les positions(i1,j1,i2,j2)
					 * pour les quelles 2 cartes ont la meme valeur .
					 */
				}
			}

		}
		return tab;
	}


	 
	public void clicCase(int x, int y) throws SlickException {

		try {
			Image imgf = imgfond[this.quelleCase(x, y)[0] - 1][this.quelleCase(x, y)[1] - 1]; // mettre les iamges de
																								// fond que la carte
																								// avait en fonction de
																								// la case on l'on a
																								// cliqué(quelle case)
			if ((caseSelect[0] == -1 && caseSelect[1] == -1)) { // si on a pas encore cliqué sur une carte

				caseSelect[0] = this.quelleCase(x, y)[0]; // on affecte le premier clic à la carte select
				caseSelect[1] = this.quelleCase(x, y)[1];

				int a = this.quelleCase(x, y)[0];
				int b = this.quelleCase(x, y)[1];
				imgfond[a - 1][b - 1] = this.cartes[a - 1][b - 1].getImg(); // reccupère image associé à la carte

			} else {
				int a = this.quelleCase(x, y)[0];
				int b = this.quelleCase(x, y)[1];
				imgfond[a - 1][b - 1] = imgf;
				caseSelect[0] = -1;
				caseSelect[1] = -1;

			}

			// insertion des valeurs des 2 cartes selectionn�es dans un tableau
			for (int i = 0; i < 2; i++) {
				if (carteselectionne[i] == null) {
					int a = this.quelleCase(x, y)[0];
					int b = this.quelleCase(x, y)[1];
					carteselectionne[i] = cartes[a - 1][b - 1];
					break;
				}
			}

			// Comparons les valeurs des cartes selectionnees
			if (carteselectionne[0] != null && carteselectionne[1] != null) {
				if (Cartes.memeValeur(carteselectionne[0], carteselectionne[1])) { // on verifie la valeur des cartes

					if (!(carteselectionne[1].equals(carteselectionne[0]))) { // on verifie leur posisition en suite
						// pour remettre les images à leur
						// place.

						// si elles sont bonnes, on modifie l'image de fond en noir
						/*
						 * on recuppère la position des cartes séléctionnées et on l'attribu une l'image
						 * de fond noir avec a,b=i1,J1 etant la position de carte que l'on a cliqué.
						 * pour la carte 1 et et c,d pour la carte 2.
						 * 
						 **/
						int a = this.getPositionCarte(carteselectionne[0].getValeur())[0];
						int b = this.getPositionCarte(carteselectionne[0].getValeur())[1];
						int c = this.getPositionCarte(carteselectionne[1].getValeur())[2];
						int d = this.getPositionCarte(carteselectionne[1].getValeur())[3];

						this.cartes[a][b].setImg(img);
						this.cartes[c][d].setImg(img);
						imgfond[a][b] = img;
						imgfond[c][d] = img;

					}
					score += 2;
				}
				// lorsque les 2 cartes ont meme valeur, on remet les cartes selectionné à nul
				// pour faire continuer la partie.
				this.carteselectionne[0] = null;
				this.carteselectionne[1] = null;

				compteur++; // incremente le nombre de tentative

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public int getCompteur() { // retourne nbre de tentative
		return this.compteur;
	}

	public int getScore() { // retourne mon score
		return score;
	}

	// Methode pour savoir si le jeux est termine
	public boolean JeuTermine() { // on parcour toute les cartes du tableau et si une carte n'est pas noir il
		// retourne faux.
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!(cartes[i][j].getImg().equals(img)))
					return false;
			}
		}
		return true;
	}

	public boolean JeuStart() { // on parcour toute les cartes du tableau et si une carte n'a pas d'image de
		// fond il retourne faux.
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (!(cartes[i][j].getImg().equals(imgfond[i][j])))
					return false;
			}
		}
		return true;
	}

	public void MessagedeFin(Graphics g) {

		if (getCompteur() <= 30 && getScore() >= 16) {

			Font font = new Font("Calibri", Font.BOLD, 25);
			TrueTypeFont t = new TrueTypeFont(font, true);
			t.drawString(5, 30, "FELICITIATIONS!!! VOUS  AVEZ GAGNEZ AVEC :" + getCompteur() + " TENTATIVES",
					Color.red);

		} else {
			Font font = new Font("Calibri", Font.BOLD, 25);
			TrueTypeFont t = new TrueTypeFont(font, true);

			t.drawString(5, 30, "VOUS  AVEZ PERDU AVEC :" + getCompteur() + " TENTATIVES", Color.red);

		}

		Font font1 = new Font("Calibri", Font.BOLD, 25);

		TrueTypeFont t1 = new TrueTypeFont(font1, true);

		TrueTypeFont t2 = new TrueTypeFont(font1, true);

		t2.drawString(15, 115, "QUITTER = Touche B", Color.red);

		t1.drawString(10, 80, "VOTRE SCORE EST DE : " + getScore(), Color.red);
	}

}
