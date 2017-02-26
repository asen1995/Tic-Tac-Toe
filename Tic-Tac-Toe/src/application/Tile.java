package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import static application.TicTacToeApp.playable;
import static application.TicTacToeApp.turnX;
import static application.TicTacToeApp.neadNewGame;;

/**
 * 
 * Tile is a single tile of the 9 tiles of the game contains rectangle 200*200,
 * text(X or O or empty) and listener for the user
 * 
 * also imports some static boolean variables(flags).
 * 
 * @author asen
 *
 */
public class Tile extends StackPane {

	private Text text = new Text();
	private Rectangle border;

	public Tile() {
		border = new Rectangle(200, 200);
		border.setFill(null);
		border.setStroke(Color.BLACK);

		getText().setFont(Font.font(90));
		getChildren().addAll(border, getText());

		setOnMouseClicked(event -> {

			if (!playable) {
				neadNewGame = true;
				TicTacToeApp.checkState();
				return;
			}

			if (text.getText().equalsIgnoreCase("X") || text.getText().equalsIgnoreCase("O")) {
				return;
			}
			if (turnX) {
				drawX();

			} else {

				drawO();
			}
			TicTacToeApp.checkState();

		});
	}

	// This method get called when we have a combo
	public void colorTheBorder() {
		if (border != null) {
			border.setFill(Color.GREEN);
		}
	}

	// called after we have combo and winner click,clear this tile
	public void clear() {
		getText().setFont(Font.font(90));
		border.setFill(null);
		text.setText(null);
		text.setFill(Color.BLACK);
	}

	private void drawX() {

		getText().setText("X");
		turnX = false;
	}

	private void drawO() {
		getText().setText("O");
		turnX = true;
	}

	// get Value of text
	public String getValue() {
		return getText().getText();
	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}
}