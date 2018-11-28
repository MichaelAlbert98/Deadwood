## Deadwood-Csci-345
Project created by Michael Albert and Ryan Lingg

Started Date: October 30, 2018  
Last revised: Novemember 16, 2018  

This project is an implementation of the board game Deadwood with permission from the owner. Emphasizes Java object oriented programming as taught in CSCI345.

Instructions to compile and run are commented in the Makefile.

The following commands are supported:

'activeplayer': Returns active player info.

'stats': Same as activeplayer.

'where': Returns the location of every player and indicated active player.

'room stats': Returns information on room.

'move': Gives active player a list of available rooms to move to. Type in the desired room from the list to move to it.

'take role': Gives active player a list of available roles to take. Type in the desired role from the list to take it.

'act': Player will work on a role. Game will inform player whether or not they succeeded, and inform them of the money/credits that they made.

'rehearse': Player rehearses a role. Game will inform player of how many rehearse tokens they now have.

'upgrade': Gives active player a list of upgrades and their costs. Type in the desired rank. Then type in the desired payment type to upgrade.

'end turn': Player ends their turn. Gameplay moves to the next player.

Commands other than these will return "Command not recognized."

We assumed that upgrading counts as a 'main' action, and thus if you start your turn in the office and upgrade, you can still move, but can't perform any other action, such as taking a role.
