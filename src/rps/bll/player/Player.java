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

    private float[][] percentFloat;
    private String name;
    private PlayerType type;

    /**
     * @param name
     */
    public Player(String name, PlayerType type) {
        percentFloat = new float[][]{{0.33f, 0.33f, 0.33f}, {0.33f, 0.33f, 0.33f}, {0.33f, 0.33f, 0.33f}};
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


    public ArrayList<Move> last3moves (List<Move> list) {
        ArrayList last3moves = new ArrayList();
        if (list.size() >= 3) {
            for (int i = 3; i >= 1; i--) {
                last3moves.add(list.get(list.size() - i));
                System.out.println(last3moves);
            }
        }
        return last3moves;
    }

    @Override
    public Move doMove(IGameState state) {
        //Historic data to analyze and decide next move...
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
