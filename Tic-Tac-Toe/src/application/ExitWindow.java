package application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ExitWindow extends Stage {

	public void display(String title, byte xWins, byte oWins, Stage gameStage) {

		Pane pane =       new Pane();
		Button yes = new Button("Yes");
		yes.setTranslateX(350-90);
		yes.setTranslateY(50);
		
		yes.setPrefWidth(80);
		Button no = new Button("No");
		no.setTranslateX(350-180);
		no.setTranslateY(50);
		no.setPrefWidth(80);

		Text text = new Text();
		text.setText("X - " + xWins + " wins\nO - " + oWins + " wins");
		text.setFont(new Font(30));
		text.setTranslateX(10);
		text.setTranslateY(40);
		pane.getChildren().addAll(yes, no, text);
		Scene scene = new Scene(pane, 350, 100);

		setTitle(title);

		setScene(scene);
		show();

		yes.setOnAction(click -> {

			close();
			gameStage.close();
		});

		no.setOnAction(click -> {
			close();
		});

	}
}
