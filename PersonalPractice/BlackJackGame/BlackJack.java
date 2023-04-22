package BlackJackGame;
public class BlackJack {
    public static void main (String[] args){
        Card[] deck = new Card[56];
    //♣
        Card cA = new Card(); //1 or 14
        Card c2 = new Card();
        Card c3 = new Card();
        Card c4 = new Card();
        Card c5 = new Card();
        Card c6 = new Card();
        Card c7 = new Card();
        Card c8 = new Card();
        Card c9 = new Card();
        Card c10 = new Card();
        Card cJ = new Card();
        Card cK = new Card();
        Card cQ = new Card();
        deck[0] = cA; //1 or 14
        deck[1] = c2;
        deck[2] = c3;
        deck[3] = c4;
        deck[4] = c5;
        deck[5] = c6;
        deck[6] = c7;
        deck[7] = c8;
        deck[8] = c9;
        deck[9] = c10;
        deck[10] = cJ;
        deck[11] = cK;
        deck[12] = cQ;
    //♦
        Card dA = new Card(); //1 or 14
        Card d2 = new Card();
        Card d3 = new Card();
        Card d4 = new Card();
        Card d5 = new Card();
        Card d6 = new Card();
        Card d7 = new Card();
        Card d8 = new Card();
        Card d9 = new Card();
        Card d10 = new Card();
        Card dJ = new Card();
        Card dK = new Card();
        Card dQ = new Card();
        deck[13] = dA; //1 or 14
        deck[14] = d2;
        deck[15] = d3;
        deck[16] = d4;
        deck[17] = d5;
        deck[18] = d6;
        deck[19] = d7;
        deck[20] = d8;
        deck[21] = d9;
        deck[22] = d10;
        deck[23] = dJ;
        deck[24] = dK;
        deck[25] = dQ;
    //♥
        Card hA = new Card(); //1 or 14
        Card h2 = new Card();
        Card h3 = new Card();
        Card h4 = new Card();
        Card h5 = new Card();
        Card h6 = new Card();
        Card h7 = new Card();
        Card h8 = new Card();
        Card h9 = new Card();
        Card h10 = new Card();
        Card hJ = new Card();
        Card hK = new Card();
        Card hQ = new Card();
        deck[26] = hA; //1 or 14
        deck[27] = h2;
        deck[28] = h3;
        deck[29] = h4;
        deck[30] = h5;
        deck[31] = h6;
        deck[32] = h7;
        deck[33] = h8;
        deck[34] = h9;
        deck[35] = h10;
        deck[36] = hJ;
        deck[37] = hK;
        deck[38] = hQ;
    //♠
        Card sA = new Card(); //1 or 14
        Card s2 = new Card();
        Card s3 = new Card();
        Card s4 = new Card();
        Card s5 = new Card();
        Card s6 = new Card();
        Card s7 = new Card();
        Card s8 = new Card();
        Card s9 = new Card();
        Card s10 = new Card();
        Card sJ = new Card();
        Card sK = new Card();
        Card sQ = new Card();
        deck[39] = sA; //1 or 14
        deck[40] = s2;
        deck[41] = s3;
        deck[42] = s4;
        deck[43] = s5;
        deck[44] = s6;
        deck[45] = s7;
        deck[46] = s8;
        deck[47] = s9;
        deck[48] = s10;
        deck[49] = sJ;
        deck[50] = sK;
        deck[51] = sQ;


        System.out.println("Let's play blackjack.");
        
    }
}