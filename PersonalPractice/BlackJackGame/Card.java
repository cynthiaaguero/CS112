package BlackJackGame;

public class Card {
    private Card card; 

    public Card(Card c) {
		card = c;
	}

    public Card() {
        this(null);
    }

    public Card name () { 
        return card; 
    }

    public void setCard (Card c) { 
        card = c; 
    }
}
