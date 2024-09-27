package no.uib.inf101.sem2.platformer.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import no.uib.inf101.sem2.grid.CellPosition;
import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.grid.GridDimension;
import no.uib.inf101.sem2.platformer.controller.ControllablePlatformerModel;
import no.uib.inf101.sem2.platformer.model.player.Player;
import no.uib.inf101.sem2.platformer.model.player.PlayerFactory;
import no.uib.inf101.sem2.platformer.view.ViewablePlatformerModel;

public class PlatformerModel implements ViewablePlatformerModel, ControllablePlatformerModel {
    
    
    private int playerVelocity;
    private int timeElapsed = 0;
    private PlatformerBoard board;
    private PlayerFactory playerFactory;
    private Player newPlayer;
    private GameState gameState;
    private int timerSpeed;
    private String tickState;
    private DoubleJump doubleJump;
    private WallJump wallJump;
    private int hasDoubleJumped = 0;
    private boolean firstTutorial = false; 
    private PlayerDirection playerDirection;
    private MovementState movementState;
    private ArrayList<String> runningFrames;
    private int currentRunningFrame;
    private ArrayList<String> jumpingFrames;
    private int currentJumpingFrame;
    

    public PlatformerModel(PlatformerBoard board, PlayerFactory playerFactory) {
        this.board = board;
        this.playerFactory = playerFactory;
        this.newPlayer = playerFactory.getNext().moveToStart(board);
        this.gameState = GameState.ACTIVE_GAME;
        this.doubleJump = DoubleJump.INACTIVE;
        this.wallJump = WallJump.INACTIVE;
        this.playerDirection = PlayerDirection.RIGHT;
        this.movementState = MovementState.IDLE;
        this.timerSpeed = 1;
        this.tickState = "falling";
        this.playerVelocity = 0;
        this.runningFrames = new ArrayList<>();
        runningFrames.addAll(Arrays.asList("Run1", "Run2", "Run3", "Run4", "Run5", "Run6", "Run7"));
        this.currentRunningFrame = 0;
        this.jumpingFrames = new ArrayList<>();
        jumpingFrames.addAll(Arrays.asList("Jump1", "Jump2", "Jump3", "Jump4", "Jump5", "Jump6"));
        this.currentJumpingFrame = 0;
    }

    /**
    * checks if the new position of the player is a position on the board and a position not allready occupied by a collision objeckt
    * @param newPlayer the current player objekt
    * @return a boolean value, true if the new position is valid or false if it is not 
    */
    private boolean checkPositionForInteractibleObjects(Player newPlayer) {
        int maxRows = this.board.rows();
        int maxCols = this.board.cols();
        
        for (GridCell<Character> gc : newPlayer) {
            if (gc.cp().row() < 0 || gc.cp().row() >= maxRows) {
                return false;
            }
            if (gc.cp().col() < 0 || gc.cp().col() >= maxCols) {
                return false;
            }
            if (this.board.get(gc.cp()) == 'R') {
                this.doubleJump = DoubleJump.ACTIVE;
                for (int i = 340; i < 361; i++) {
                    for (int j = 920; j < 941; j++) {
                        this.board.set(new CellPosition(i, j), '-');
                    }
                }
                this.gameState = GameState.DOUBLEJUMPTUTORIAL;
            }
            if (this.board.get(gc.cp()) == 'G') {
                this.wallJump = WallJump.ACTIVE;
                for (int i = 80; i < 101; i++) {
                    for (int j = 500; j < 521; j++) {
                        this.board.set(new CellPosition(i, j), '-');
                    }
                }
                this.gameState = GameState.WALLJUMPTUTORIAL;
            }
            if (this.board.get(gc.cp()) == 'O') {
                getNewPlayer();
            }
            if (this.board.get(gc.cp()) == 'D') {
                this.doubleJump = DoubleJump.INACTIVE;
                for (int i = 340; i < 361; i++) {
                    for (int j = 920; j < 941; j++) {
                        this.board.set(new CellPosition(i, j), 'R');
                    }
                }
                this.wallJump = WallJump.INACTIVE;
                for (int i = 80; i < 101; i++) {
                    for (int j = 500; j < 521; j++) {
                        this.board.set(new CellPosition(i, j), 'G');
                    }
                }
                getNewPlayer();
            }
            if (this.board.get(gc.cp()) != '-') {
                return false;
            }
        }
        return true;
    }

    /**
     * sets the setting for the double jump powerup.
     * @param setting either ACTIVE or INACTIVE
     */
    public void setDoubleJump(DoubleJump setting) {
        this.doubleJump = setting;
    }

    /**
     * gets a new Player to the top of the board as long as there is a legal position there.
     */
    public void getNewPlayer() {
       this.newPlayer = playerFactory.getNext().moveToStart(board);
    }

    @Override
    public boolean checkLegalPosition(int deltaRow, int deltaCol) {
        Player candidate = this.newPlayer.shiftedBy(deltaRow, deltaCol);
        if (!checkPositionForInteractibleObjects(candidate)) {
            return false;
        }
        return true;
           
    }

    @Override
    public String getTickState() {
        return this.tickState;
    }

    @Override
    public Iterable<GridCell<Character>> getPlayerTilesOnBoard() {
        return this.newPlayer;
    }

    @Override
    public boolean movePlayer(int deltaRow, int deltaCol) {
        if (firstTutorial == false) {
            this.gameState = GameState.JUMPTUTORIAL;
            firstTutorial = true;
        }
        Player candidate = this.newPlayer.shiftedBy(deltaRow, deltaCol);
        if (!checkPositionForInteractibleObjects(candidate)) {
            return false;
        }
        this.newPlayer = candidate;
        return true;
           
    }
    @Override
    public DoubleJump getDoubleJumpState() {
        return this.doubleJump;
    }
    @Override
    public WallJump getWallJumpState() {
        return this.wallJump;
    }
    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    @Override
    public int getMillisecondsPerMove() {
        return this.timerSpeed;
    }

    @Override
    public void clockTick(String state) { 
        if (this.wallJump.equals(WallJump.ACTIVE) && (!checkLegalPosition(0, 6) || !checkLegalPosition(0, -6))) {
            movePlayer(1, 0);
            hasDoubleJumped = 0;
            this.movementState = MovementState.FALLING;
        } else if (state.equals("falling")) {
            playerVelocity += Math.pow(1, timeElapsed);
            if (movePlayer((int) Math.round(playerVelocity), 0)){
                timeElapsed++;
            } else {
                while (movePlayer(1, 0)){
                }
                this.hasDoubleJumped = 0;
                this.playerVelocity = 0;
                timeElapsed = 0;
            }
        } else {
            if (playerVelocity >= 0 || !checkLegalPosition(-1, 0)) {
                this.playerVelocity = 0;
                timeElapsed = 0;
                this.tickState = "falling";
            } else {
                playerVelocity += Math.pow(1, timeElapsed);
                if (movePlayer((int) Math.round(playerVelocity), 0)) { 
                    timeElapsed++;
                    if (timeElapsed % 5 == 0) {
                        if (this.currentJumpingFrame > 4) {
                            this.currentJumpingFrame = 0;
                        } else {
                            this.currentJumpingFrame++;
                        }
                    }
                    
                    
                } else {
                    this.playerVelocity = 0;
                    while (movePlayer(-1, 0)) {

                    }
                    timeElapsed = 0;
                }
            }
        }
    }

    @Override
    public void jumpPlayer() {
        if (!checkLegalPosition(1, 0)) {
            this.tickState = "jumping";
            this.playerVelocity = -14;
            this.movementState = MovementState.JUMPING;
        }
        else if (this.doubleJump.equals(DoubleJump.ACTIVE) && hasDoubleJumped < 10) {
            this.tickState = "jumping";
            this.playerVelocity = -12;
            hasDoubleJumped++;
            this.movementState = MovementState.JUMPING;
        }              
    }

    @Override
    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public GridDimension getDimension() {
        return this.board;
    }

    @Override
    public void setPlayerDirection(PlayerDirection playerDirection) {
        this.playerDirection = playerDirection;
    }

    @Override
    public PlayerDirection getPlayerDirection() {
        return this.playerDirection;
    }

    @Override
    public MovementState getMovementState() {
        return this.movementState;
    }

    @Override
    public String getPlayerFrame() {
        if (this.movementState.equals(MovementState.IDLE)) {
            return "Player2";
        } else if (this.movementState.equals(MovementState.RUNING)) {
            return this.runningFrames.get(this.currentRunningFrame);
        } else if (this.movementState.equals(MovementState.JUMPING)) {
            return this.jumpingFrames.get(this.currentJumpingFrame);
        } else if (this.movementState.equals(MovementState.FALLING)) {
            return "Jump3";
        }
        return "Player2";
    }

    @Override
    public void setMovementState(MovementState movementState) {
        this.movementState = movementState;
    }

    @Override
    public void setCurrentRunningFrame(int frameIndex) {
        this.currentRunningFrame = frameIndex;
    }
}
