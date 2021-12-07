import com.prac3.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

public class TDDDurak {
    private Durak durak;
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
    @Test
    public void getDeck(){
        Durak durak = new Durak();
        List<Card> deck = durak.getDeck();
        Assert.assertEquals(36, deck.size());
        Assert.assertEquals(new HashSet<Card>(deck).size(), deck.size());
    }
    @Test
    public void RandomizeDeckTest(){
        Durak durak = new Durak();
        List<Card> deck = durak.getDeck();
        durak.randomizeDeck();
        List<Card> randomDeck = durak.getDeck();
        boolean identical = true;
        for (Integer i = 0; i < deck.size(); i++){
            if(deck.get(i).toString().equals(randomDeck.get(i).toString()))
                identical = false;
        }
        assert(!identical);
    }
}

