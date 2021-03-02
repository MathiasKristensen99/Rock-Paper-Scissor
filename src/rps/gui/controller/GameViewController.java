package rps.gui.controller;

// Java imports
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import rps.bll.game.GameManager;
import rps.bll.game.Move;
import rps.bll.game.Result;
import rps.bll.game.ResultType;
import rps.bll.player.IPlayer;
import rps.bll.player.Player;
import rps.bll.player.PlayerType;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author smsj
 */
public class GameViewController implements Initializable {


    public Button btnRock;
    public Button btnPaper;
    public Button btnScissor;

    public Label txtWL;
    public TextField chatField;
    public TextArea messageField;
    public Button btnSend;

    public Label lblPlayerScoreCount;
    public Label lblBotScoreCount;

    private IPlayer human = new Player("player", PlayerType.Human);
    private  IPlayer bot = new Player("bot", PlayerType.AI);

    private GameManager gameManager = new GameManager(human, bot);

    private int botScore = 0;
    private int playerScore = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void playerMove(String move) {
        gameManager.playRound(Move.valueOf(move));
        setScore(gameManager.getGameState().getLastResult());
        gameManager.getGameState().getHistoricResults().forEach((result) -> {
            txtWL.setText(getResultAsString(result));
            System.out.println(getResultAsString(result));
        });
    }

    public String getResultAsString(Result result) {
        String statusText = result.getType() == ResultType.Win ? "wins over " : "ties ";
        return "Round #" + result.getRoundNumber() + ": " +
                result.getWinnerPlayer().getPlayerName() +
                " (" + result.getWinnerMove() + ") " +
                statusText + result.getLoserPlayer().getPlayerName() +
                " (" + result.getLoserMove() + ")!";
    }

    public void selectRock(ActionEvent actionEvent) {
        playerMove("Rock");
    }

    public void selectPaper(ActionEvent actionEvent) {
        playerMove("Paper");
    }

    public void selectScissor(ActionEvent actionEvent) {
        playerMove("Scissor");
    }

    public void selectSend(ActionEvent actionEvent) {
    }

    public void setScore(Result result) {
        if (result.getType() != ResultType.Tie) {
            if (result.getWinnerPlayer().getPlayerType().equals(PlayerType.AI)) {
                botScore += 1;
                lblBotScoreCount.setText(String.valueOf(Integer.valueOf(botScore)));
            } else {
                playerScore += 1;
                lblPlayerScoreCount.setText(String.valueOf(Integer.valueOf(playerScore)));
            }
        }
    }
}
