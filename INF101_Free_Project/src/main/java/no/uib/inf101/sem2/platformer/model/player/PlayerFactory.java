package no.uib.inf101.sem2.platformer.model.player;

public interface PlayerFactory {
    
    /**
     * @return a new player.
     */
    public Player getNext();

    public Player getCrouch();

    public Player getJump();
}
