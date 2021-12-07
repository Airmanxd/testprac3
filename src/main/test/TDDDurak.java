import com.prac3.Card;
import org.junit.Test;

public class TDDDurak {
    private main.java.com.prac3.Durak durak;
    @Test
    public void getCardTest(){
        Card queenOfSpades = new Card("QS");
        Card fourOfDiamonds = new Card("4D");
        assert(queenOfSpades.getValue() == 12);
        assert(queenOfSpades.getSuit() == 'S');
        assert(fourOfDiamonds.getValue() == 4);
        assert(fourOfDiamonds.getSuit() == 'D');
        assert(queenOfSpades.toString().equals("QS"));
        assert(fourOfDiamonds.toString().equals("4D"));
    }

}

