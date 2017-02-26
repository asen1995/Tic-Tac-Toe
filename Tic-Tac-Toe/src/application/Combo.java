package application;

/**
 * 
 * this class contains the winning combos in the game
 * 
 * @author asen
 *
 */
public class Combo {
	public Tile[] tiles;

	public Combo(Tile... tiles) {
		this.tiles = tiles;
	}

	/**
	 * This method checks if the array of combos contains 3 same identificators
	 * 
	 * @return
	 */
	public boolean isComplete() {

		if (tiles[0].getValue().isEmpty()) {
			return false;
		}

		return tiles[0].getValue().equals(tiles[1].getValue()) && tiles[0].getValue().equals(tiles[2].getValue());

	}

}