
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class Card {

    
    // define data members
	
	private int card_val;
	private String suit;
	private static int[] num = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13}; 
	private static String[] suits = new String[]{"spades", "clubs", "hearts","diamonds"};
    
	//default constructor
    public Card () {
	
	// initialization
	
		card_val = 0;
		suit = "";
    }
	
	//Assume one of the suits from {"spades", "clubs", "hearts","diamonds"} will be passed in, not some random string
	public Card (int card_val, String suit)
	{
		this.card_val = card_val;
		this.suit = suit;
	}

    public static void buildDeck(ArrayList<Card> deck) {
	
	// Given an empty deck, construct a standard deck of playing cards
		deck.clear(); // clear deck so that when rebuilding the deck, it won't use the deck that remains 
		
		
		//build a 52-card deck, 1-Ace, 11-Jack, 12-Queen, 13-King
		for(int i = 0;i < num.length;i++)
		{
			for(int j = 0;j < suits.length;j++)
			{
				Card c1 = new Card(num[i],suits[j]); //calling the 2-parameter constructor
				deck.add(c1);
			}
		}
		
    }

    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){ 
	
	// Deal two cards from the deck into each of the player's hand and dealer's hand 
		
		//rebuild the deck, or renew the deck, every time a new round starts. so the size of deck is always 52 at the beginning of each round
		buildDeck(deck);
		
		//System.out.println(deck.size());

		//clear both hands to make sure the hands from last round don't carry on to the new round
		playerHand.clear();
		dealerHand.clear();

		//check if the deck indeed has at least 4 cards to complete the initial deal.
		if(deck.size() >= 4)
		{	
			
			Random rand = new Random();
			//no need to use for loops since we're only drawing 2 cards for each side
			
			playerHand.add(deck.remove(rand.nextInt(deck.size())));  //each card is drawn from a random index
			playerHand.add(deck.remove(rand.nextInt(deck.size())));
			dealerHand.add(deck.remove(rand.nextInt(deck.size())));
			dealerHand.add(deck.remove(rand.nextInt(deck.size())));
		}

		//output a line instead of crashing the program
		else
		{

			System.out.println("There're fewer than 4 cards in the deck");
		}

	
    }

    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand){
	
	// this should deal a single card from the deck to the hand
		
		//check if the deck indeed has at least 1 card to complete the deal.
		if(deck.size() >= 1)
		{
			Random rand = new Random();	
			hand.add(deck.remove(rand.nextInt(deck.size()))); //draw card from random index
			
		}

		//output a line instead of crashing the program
		else
		{
			System.out.println("There's fewer than 1 card in the deck");
			
		}

		
    }

    public static boolean checkBust(ArrayList<Card> hand){
	
	// This should return whether a given hand's value exceeds 21
		
		int total = 0;
		for(int i = 0; i < hand.size();i++)
		{
			if(hand.get(i).card_val > 10)
			{
				total += 10;
			}

			else
			{
				total += hand.get(i).card_val;   
				/* Because we're checking for bust, it's easier(safer) to always 
				treat Ace as 1 */
			}
		}

		if(total > 21) 
		{
			return true;
		}
		else{
			return false;
		}
		
    }

    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
	
	// This should conduct the dealer's turn and
	// Return true if the dealer busts; false otherwise

		int total = SumHand(hand); 
		while(total < 17)    
		{
			dealOne(deck, hand); //draw a card from deck to hand while total is smaller than 17
			total = SumHand(hand); //updates total
		}

		if(total > 21) //bust
		{
			return true;
		}

		else{
			return false;
		}
		
    }

    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
	
	// This should return 1 if the player wins and 2 if the dealer wins
		if(SumHand(playerHand) <= SumHand(dealerHand)) //compare values for both hands, dealer wins even when they tie
		{
			return 2; 
		}
		else
		{
			return 1;
		}

    }

    public static String displayCard(ArrayList<Card> hand){
	
	// Return a string describing the card which has index 1 in the hand

		if(hand.get(1).card_val == 1)  //1->Ace
		{
			return "Ace " + "of " + hand.get(1).suit; 
		}
		else if(hand.get(1).card_val == 11)  //11->Jack
		{
			return "Jack " + "of " + hand.get(1).suit;
		}
		else if(hand.get(1).card_val == 12)  //12-->Queen
		{
			return "Queen " + "of " + hand.get(1).suit;
		}
		else if(hand.get(1).card_val == 13)   //13->King
		{
			return "King " + "of " + hand.get(1).suit;
		}
		else  //ok to use original numbers to represent value of card
		{
			return hand.get(1).card_val + " of " + hand.get(1).suit;
		}
		
    }

    public static String displayHand(ArrayList<Card> hand){
	
	// Return a string listing the cards in the hand

		
		String result = "|"; //to seperate each card with "|"
		for(int i = 0; i < hand.size();i++)
		 {
			if(hand.get(i).card_val == 1)
			{
				result += "Ace of " + hand.get(i).suit + "|"; //1->Ace
			}
			else if(hand.get(i).card_val == 11)
			{
				result += "Jack of " + hand.get(i).suit + "|";  //11->Jack
			}
			else if(hand.get(i).card_val == 12)
			{
				result += "Queen of " + hand.get(i).suit + "|";//12-->Queen
			}
			else if(hand.get(i).card_val == 13)
			{
				result += "King of " + hand.get(i).suit + "|"; //13->King
			}
			else
			{
				result += hand.get(i).card_val + " of " + hand.get(i).suit + "|";
			}
		 }
		return result;
    }



	public static int SumHand(ArrayList<Card> hand)  //added method that computes the sum in hand
	{	

		int values = 0;	 //total values of cards in hand
		int Ace = 0;    //number of aces
		for(int i = 0; i < hand.size();i++)
		{
			if(hand.get(i).card_val == 1)
			{
				values += 1;   //use lower bound of ace value
				Ace+=1;
			}
			else if(hand.get(i).card_val > 10)
			{
				values += 10; //jack, queen, king = 10
			}
			else
			{
				values += hand.get(i).card_val;
			}
		}
		while(values<12 && Ace > 0)  //make one ace's value 11 if total value doesn't exceed 12
		{
			values += 10;
			Ace -= 1;
		}
		return values;
	}


	//override toString() method to display card objects
	@Override
    public String toString() {
        return card_val + " of " + suit;
    }


    public static void main(String[] args) {

		int playerChoice, winner;
		ArrayList<Card> deck = new ArrayList<Card> ();
		
		buildDeck(deck);
		
		playerChoice = JOptionPane.showConfirmDialog(null, "Ready to Play Blackjack?", "Blackjack", JOptionPane.OK_CANCEL_OPTION);

		if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
		    System.exit(0);

		
		Object[] options = {"Hit","Stand"};
		boolean isBusted, dealerBusted;
		boolean isPlayerTurn;
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> dealerHand = new ArrayList<>();
	
		do{ // Game loop
		    initialDeal(deck, playerHand, dealerHand);
		    isPlayerTurn=true;
		    isBusted=false;
		    dealerBusted=false;
		    
		    while(isPlayerTurn){

				// Shows the hand and prompts player to hit or stand
				playerChoice = JOptionPane.showOptionDialog(null,"Dealer shows " + displayCard(dealerHand) + "\n Your hand is: " + displayHand(playerHand) + "\n What do you want to do?","Hit or Stand",
									   JOptionPane.YES_NO_OPTION,
									   JOptionPane.QUESTION_MESSAGE,
									   null,
									   options,
									   options[0]);

				if(playerChoice == JOptionPane.CLOSED_OPTION)
				    System.exit(0);
				
				else if(playerChoice == JOptionPane.YES_OPTION){
				    dealOne(deck, playerHand);
				    isBusted = checkBust(playerHand);
				    if(isBusted){
						// Case: Player Busts
						playerChoice = JOptionPane.showConfirmDialog(null,"Player has busted!", "You lose", JOptionPane.OK_CANCEL_OPTION);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
						
						isPlayerTurn=false;
				    }
				}
			    
				else{
				    isPlayerTurn=false;
				}
		    }
		    if(!isBusted){ // Continues if player hasn't busted
				dealerBusted = dealerTurn(deck, dealerHand);
				if(dealerBusted){ // Case: Dealer Busts
				    playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "\nThe dealer busted.\n Congrautions!", "You Win!!!", JOptionPane.OK_CANCEL_OPTION);		    

					if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						System.exit(0);
				}
			
			
				else{ //The Dealer did not bust.  The winner must be determined
				    winner = whoWins(playerHand, dealerHand);

				    if(winner == 1){ //Player Wins
						playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "\n Congrautions!", "You Win!!!", JOptionPane.OK_CANCEL_OPTION);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }

				    else{ //Player Loses
						playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "\n Better luck next time!", "You lose!!!", JOptionPane.OK_CANCEL_OPTION); 
					
						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }
				}
		    }
		}while(true);
    }
}