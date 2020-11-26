## Deadwood
Project created by Michael Albert and Ryan Lingg

Started Date: October 30, 2018  
Last revised: December 07, 2018  

This project is an implementation of the board game Deadwood with permission from the owner. Emphasizes Java object oriented programming.

Instructions to compile and run are commented in the Makefile.

The following actions are supported:

'move': Gives active player a list of available rooms to move to. Click the desired room to move to it.

'take role': Gives active player a list of available roles to take. Click the desired role to take it.

'act': Player will work on a role. Game will inform player whether or not they succeeded.

'rehearse': Player rehearses a role. Game will inform player that they rehearsed.

'upgrade': Gives active player a choice of whether to upgrade with cash or credits. When one is picked, gives a list of available ranks to upgrade to. Click the desired rank to upgrade to it.

'end turn': Player ends their turn. Gameplay moves to the next player.

The following assumptions were made:

Since it is not specified in the rules, we assumed that upgrading counts as a 'main' action, and thus that upgrading, much like acting, rehearsing, or taking a role, brings your turn to an end. Our reasoning was that upgrading is one of the most powerful actions you can take in the game, as it makes it much more likely that you are able to take an on card role, allowing you to get a large advantage over another player who has not upgraded. Imposing a malus on upgrading seemed a logical choice.

If a player clicks on a button that lets them make a choice and then chooses to click on cancel or closes the window instead of picking something, they lose the ability to click that button again for that turn.

In the event of a tie, the player who input their name first in the initialization of the game is declared the winner.
