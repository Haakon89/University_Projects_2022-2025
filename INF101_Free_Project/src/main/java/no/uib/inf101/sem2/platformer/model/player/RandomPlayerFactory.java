package no.uib.inf101.sem2.platformer.model.player;

public class RandomPlayerFactory implements PlayerFactory {

    @Override
    public Player getNext() {
        return Player.newPlayer('I');
    }

    @Override
    public Player getCrouch() {
        return Player.newPlayer('C');
    }

    @Override
    public Player getJump() {
        return Player.newPlayer('J');
    }
}