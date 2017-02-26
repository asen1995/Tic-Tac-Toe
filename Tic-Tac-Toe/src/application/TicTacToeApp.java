package application;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * This is the main class where we create the content and logic
 * 
 * @author asen
 *
 */
public class TicTacToeApp extends Application {

	// Flag
	protected static boolean turnX = true;

	protected static boolean playable = true;

	protected static boolean neadNewGame = false;

	private static Tile[][] board = new Tile[3][3];
	// list of winning combo's
	private static List<Combo> combos = new ArrayList<>();
	private Pane root = new Pane();

	private static byte xWins, oWins;

	private static ExitWindow exitWindow = new ExitWindow();

	private Parent createContent() {

		root.setPrefSize(600, 600);

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {
				Tile tile = new Tile();
				tile.setTranslateX(i * 200);
				tile.setTranslateY(j * 200);
				root.getChildren().add(tile);
				board[j][i] = tile;
			}

		}
		// horizontal combo's
		for (int y = 0; y < 3; y++) {
			combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
		}

		// vertical combo's

		for (int x = 0; x < 3; x++) {
			combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
		}

		// diagonals
		// left combo
		combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
		// right combo
		combos.add(new Combo(board[2][0], board[1][1], board[0][2]));

		return root;
	}

	/**
	 * 
	 * this method first check if there is no empty tile - restart the game get
	 * called after each turn,and after we have a winner - to restart the game
	 */

	protected static void checkState() {

		byte countOfEmptyPlace = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				if (board[i][j].getText().getText().isEmpty()) {
					countOfEmptyPlace++;
				}
			}
		}

		// if all tiles are not empty
		if (countOfEmptyPlace == 0) {
			neadNewGame = true;
		}

		if (neadNewGame) {

			new TicTacToeApp().clearTheCurrentGame();

		} else {

			for (Combo combo : combos) {

				if (combo.isComplete()) {
					playable = false;

					playWinnerAnimation(combo);

					break;
				}
			}
		}
	}

	/**
	 * clear the current game and start new game
	 */
	private void clearTheCurrentGame() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j].clear();

			}
		}
		neadNewGame = false;
		playable = true;
	}

	/**
	 * Color the winning combo in green ,and text in yellow
	 * 
	 * @param combo
	 *            object contains the 3 tile's with same text
	 */
	private static void playWinnerAnimation(Combo combo) {

		if (combo.tiles[0].getText().getText().equalsIgnoreCase("X")) {
			xWins++;
		} else {
			oWins++;
		}

		for (int i = 0; i < combo.tiles.length; i++) {
			combo.tiles[i].colorTheBorder();
			combo.tiles[i].getText().setFill(Color.YELLOW);
		}

	}

	/**
	 * called by main function,to create content and show it;
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setScene(new Scene(createContent()));
		primaryStage.show();
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			exitWindow.display("Are you really wanna exit the game", xWins, oWins,primaryStage);
			System.out.println("x win + " + xWins + "o Wins : " + oWins);

		
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
