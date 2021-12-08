import com.prac3.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        assertEquals(36, deck.size());
        assertEquals(new HashSet<Card>(deck).size(), deck.size());
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
    @Test
    public void getInitialSixTest(){
        Durak durak = new Durak();
        durak.randomizeDeck();
        Player player1 = new Player("Testname");
        player1.setCards(durak.getInitialSix());
        assertEquals(6, player1.getCards().size());
        assertEquals(30, durak.getDeck().size());
    }
    @Test
    public void checkValidityOfAPlayTest(){
        Durak durak = new Durak();
        durak.randomizeDeck();
        Card queenOfSpades = new Card("QS");
        Card aceOfSpades = new Card("AS");
        Card threeOfSpades = new Card("3S");
        Card kingOfDiamonds = new Card("KD");
        assert(!durak.checkPlay(queenOfSpades, aceOfSpades));
        assert(!durak.checkPlay(kingOfDiamonds, queenOfSpades));
        assert(durak.checkPlay(queenOfSpades, threeOfSpades));
    }
    @Test
    public void checkTrumpTest(){
        Durak durak = new Durak();
        durak.setTrump();
        char suit = durak.getTrumpSuit();
        System.out.println("Trump suit: " + suit);
        Card queenOfTrumpSuit = new Card("Q" + suit);
        queenOfTrumpSuit.setTrumpSuit();
        Card kingOfSpades = new Card("KS");
        Card kingOfClubs = new Card("KC");
        Card kingOfDiamonds = new Card("KD");
        Card kingOfHearts = new Card("KH");
        kingOfHearts.setTrumpSuit();
        assert(durak.checkPlay(queenOfTrumpSuit, kingOfClubs));
        assert(durak.checkPlay(queenOfTrumpSuit, kingOfSpades));
        assert(durak.checkPlay(queenOfTrumpSuit, kingOfDiamonds));
        assert(!durak.checkPlay(queenOfTrumpSuit, kingOfHearts));
    }
    @Test
    public void playerDrawCardTest(){
        Durak durak = new Durak();
        durak.randomizeDeck();
        Player player1 = new Player("Testname");
        player1.setCards(durak.getInitialSix());
        assert(!durak.draw(player1));
        ArrayList<Card> test = new ArrayList<>();
        test.add(new Card("KH"));
        test.add(new Card("VH"));
        player1.setCards(test);
        assert(durak.draw(player1));
        assertEquals(6, player1.getCards().size());
    }
    @Test
    public void replenishDeckTest(){
        Durak durak = new Durak();
        durak.getInitialSix();
        durak.getInitialSix();
        assertEquals(24, durak.getDeck().size());
        durak.replenishDeck();
        assertEquals(36, durak.getDeck().size());
    }
    @Test
    public void throwAnotherTest(){
        Durak durak = new Durak();
        ArrayList<Integer> testAdd = new ArrayList<>();
        testAdd.add(7);
        assert(durak.throwAnother(new Card("7D"), testAdd));
    }
    @Test
    public void getAllTest(){
        Durak durak = new Durak();
        ArrayList<Card> test = new ArrayList<>();
        test.add(new Card("KH"));
        test.add(new Card("VH"));

        ArrayList<Card> test2 = new ArrayList<>();
        Card AH = new Card("AH");
        Card QH = new Card("QH");
        test2.add(AH);
        test2.add(QH);
        Player player1 = new Player("Testname");
        player1.setCards(test);
        player1.getAll(test2);
        assert(player1.getCards().contains(AH) && player1.getCards().contains(QH));
    }
}

