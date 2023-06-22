# Java Implementation of Blackjack
• The program deals two cards randomly from a deck of cards to both the player and itself.
• The player then chooses to hit or stand until he/she is either bust (over 21) or decides to
stand.
• The dealer (program) hits and stands according to the following rules:
– If the dealer’s hand is less than 17, it hits.
– If the dealer’s hand is 17 or more it stands.
– Of course, if the dealer’s hand is greater than 21, it loses.
• If the player and dealer tie, then the dealer wins.
• All face cards (Jack, Queen, King) are worth 10 points.
• The Ace has two possible values (1 and 11) and takes on which ever one is more beneficial to the player or dealer.
• For simplicity, we will not implement the split and double rules.
• At the end of the hand, the deck should be reset and the game starts again, until the user  signals the game to end (by hitting escape, for instance).
