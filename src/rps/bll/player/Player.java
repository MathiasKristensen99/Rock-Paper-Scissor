package rps.bll.player;

//Project imports
import rps.bll.game.*;

//Java imports
import java.util.*;

/**
 * Example implementation of a player.
 *
 * @author smsj
 */
public class Player implements IPlayer {

    private String name;
    private PlayerType type;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        this.name = name;
        this.type = type;

    }


    @Override
    public String getPlayerName() {
        return name;
    }

    @Override
    public PlayerType getPlayerType() {
        return type;
    }


    public ArrayList<Result> last3results (ArrayList<Result> list) {
        ArrayList last3Results = new ArrayList();
        if (list.size() >= 3) {
            for (int i = 3; i >= 1; i--) {
                last3Results.add(list.get(list.size() - i));
                System.out.println(last3Results);
            }
        }
        return last3Results;
    }

    @Override
    public Move doMove(IGameState state) {
        ArrayList<Result> results = (ArrayList<Result>) state.getHistoricResults();

        Random rand = new Random();
        Move botMove;
        int result = rand.nextInt(3) +1;
        switch (result){
            case 1:
                botMove = Move.Rock;
                break;
            case 2:
                botMove = Move.Paper;
                break;
            default:
                botMove = Move.Scissor;
                break;
        }
        return botMove;
    }
}
