# Java Implementation of Blackjack

This repository contains a Java implementation of the classic card game Blackjack. The program simulates a game between a single player and the dealer, with straightforward rules and gameplay mechanics.

## Game Rules

The game follows the basic rules of Blackjack with some simplifications:

- The program deals two cards randomly from a deck of cards to both the player and itself (the dealer).
- The player has the option to 'hit' (receive another card) or 'stand' (keep the current hand).
- The player continues to 'hit' or 'stand' until they either exceed 21 points (bust) or decide to 'stand'.
- The dealer follows these rules:
  - If the dealer's hand totals less than 17, it hits.
  - If the dealer's hand is 17 or more, it stands.
  - The dealer busts if its hand exceeds 21.
- If the player and dealer tie in terms of hand value, the dealer wins.
- All face cards (Jack, Queen, King) are valued at 10 points.
- The Ace can be worth 1 or 11 points, taking whichever value is more beneficial to the hand.
- Simplifications:
  - The split and double down rules are not implemented.
  - The deck is reset after each hand.
- The game continues until the user signals the end (e.g., by pressing the escape key).

## Getting Started

To play the game, follow these steps:

1. Clone the repository:
   ```sh
   git clone https://github.com/[YourGitHubUsername]/java-blackjack.git
