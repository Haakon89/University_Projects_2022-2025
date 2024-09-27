package no.uib.inf101.sem2.platformer.controller;

import java.awt.event.KeyEvent;

import javax.swing.Timer;

import no.uib.inf101.sem2.MusicSFX.PlatformerMusic;
import no.uib.inf101.sem2.platformer.model.GameState;
import no.uib.inf101.sem2.platformer.model.MovementState;
import no.uib.inf101.sem2.platformer.model.PlayerDirection;
import no.uib.inf101.sem2.platformer.view.PlatformerView;

import java.awt.event.ActionEvent;



public class PlayerController implements java.awt.event.KeyListener {
    
    private ControllablePlatformerModel controllablePlatformerModel;
    private PlatformerView platformerView;
    private Timer timer;
    boolean[] keysPressed = new boolean[256];
    private int counter = 0;
    private int currentRunningFrame = 0;

    public PlayerController(ControllablePlatformerModel controllablePlatformerModel, PlatformerView platformerView) {
        this.controllablePlatformerModel = controllablePlatformerModel;
        this.platformerView = platformerView;
        platformerView.addKeyListener(this);
        this.timer = new Timer(controllablePlatformerModel.getMillisecondsPerMove(), this::clockTick);
        this.timer.start();
        
    }

    private void timerDelay() {
        int delay = this.controllablePlatformerModel.getMillisecondsPerMove();
        this.timer.setDelay(delay);
        this.timer.setInitialDelay(delay);
    }

    private void clockTick(ActionEvent ticker) {
        if (this.controllablePlatformerModel.getGameState() == GameState.ACTIVE_GAME) {
            this.controllablePlatformerModel.clockTick(this.controllablePlatformerModel.getTickState());
            this.platformerView.repaint();
            timerDelay();
        }
    }
    
    public void update() {
        if (this.controllablePlatformerModel.getGameState() == GameState.ACTIVE_GAME) {
            int dx = 0;
            int dy = 0;
        
            if (keysPressed[KeyEvent.VK_LEFT]) {
                this.controllablePlatformerModel.setPlayerDirection(PlayerDirection.LEFT);
                this.controllablePlatformerModel.setMovementState(MovementState.RUNING);
                this.counter++;
                if (this.counter % 5 == 0) {
                    this.controllablePlatformerModel.setCurrentRunningFrame(currentRunningFrame);
                    currentRunningFrame++;
                    if (currentRunningFrame+1 == 8) {
                        this.currentRunningFrame = 0;
                    }
                }
                dx -= 4;
            }
            if (keysPressed[KeyEvent.VK_RIGHT]) {
                this.controllablePlatformerModel.setPlayerDirection(PlayerDirection.RIGHT);
                this.controllablePlatformerModel.setMovementState(MovementState.RUNING);
                this.counter++;
                if (this.counter % 5 == 0) {
                    this.controllablePlatformerModel.setCurrentRunningFrame(currentRunningFrame);
                    this.currentRunningFrame++;
                    if (currentRunningFrame+1 == 8) {
                        this.currentRunningFrame = 0;
                    }
                }
                dx += 4;
            }
            if (keysPressed[KeyEvent.VK_DOWN]) {
                dy += 0;
            }
            if (keysPressed[KeyEvent.VK_UP]) {
                this.controllablePlatformerModel.jumpPlayer();
            }
        
            this.controllablePlatformerModel.movePlayer(dy, dx);
            platformerView.repaint();
        } else {
            if (keysPressed[KeyEvent.VK_SPACE]) {
                this.controllablePlatformerModel.setGameState(GameState.ACTIVE_GAME);
            }
        }
        
    }
    
    @Override
    public void keyPressed(KeyEvent input) {
        keysPressed[input.getKeyCode()] = true;
        
           
    }

    @Override
    public void keyReleased(KeyEvent input) {
        keysPressed[input.getKeyCode()] = false;
        this.controllablePlatformerModel.setMovementState(MovementState.IDLE);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        
    }
}

