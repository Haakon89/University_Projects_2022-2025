package inf112.Sun_Mist_Mountain.app.Controller;

import java.util.HashSet;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import inf112.Sun_Mist_Mountain.app.Model.Coordinates;
import inf112.Sun_Mist_Mountain.app.Model.Math.Position;
import inf112.Sun_Mist_Mountain.app.Model.Math.Vector;
import inf112.Sun_Mist_Mountain.app.Model.Player.Player;
import inf112.Sun_Mist_Mountain.app.View.AnimationState;

public class Conntroller extends InputAdapter {

    private final ControllablePlayerModel playerModel; 
    private final ControllableAnimator animator;
    private final HashSet<Integer> pressedKeys = new HashSet<>();
    private final Coordinates coordinates;
    private final SoundController sounds;
    private int lastKeyPressed;

    public Conntroller(Coordinates coordinates, ControllablePlayerModel playerModel, ControllableAnimator animator, SoundController sounds) {
        this.animator = animator;
        this.playerModel = playerModel;
        this.coordinates = coordinates;
        this.sounds = sounds;
        this.lastKeyPressed = 0;
    }

    /**
     * updates the movement of the character depending on the keys
     * registered in the set keysPressed.
     */
    public void update(float delta) {
        double dx = 0;
        double dy = 0;

        double speed = Player.WALKING_SPEED * delta;

        var animationState = AnimationState.IDLE;

        if (this.pressedKeys.contains(Input.Keys.W)) {
            dy += speed;
            animationState = AnimationState.WALKING_UP;
        }
        if (this.pressedKeys.contains(Input.Keys.S)) {
            dy -= speed;
            animationState = AnimationState.WALKING_DOWN;
        }
        if (this.pressedKeys.contains(Input.Keys.A)) {
            dx -= speed;
            animationState = AnimationState.WALKING_LEFT;
        }
        if (this.pressedKeys.contains(Input.Keys.D)) {
            dx += speed;
            animationState = AnimationState.WALKING_RIGHT;
        }

        if (!this.pressedKeys.isEmpty()) { 
            var by = new Vector(dx, dy);
            var nowOn = this.playerModel.move(by);

            if (nowOn != null) {
                this.sounds.move(nowOn);
            }

            this.animator.setState(animationState);
        } else {
            if(this.lastKeyPressed == Input.Keys.W) {
                this.animator.setState(AnimationState.IDLE_BACK);
            } else if(this.lastKeyPressed == Input.Keys.A) {
                this.animator.setState(AnimationState.IDLE_LEFT);
            } else if(this.lastKeyPressed == Input.Keys.S) {
                this.animator.setState(AnimationState.IDLE);
            } else if(this.lastKeyPressed == Input.Keys.D) {
                this.animator.setState(AnimationState.IDLE_RIGHT);
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        this.pressedKeys.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        this.pressedKeys.remove(keycode);
        if (this.pressedKeys.isEmpty()) {
            this.lastKeyPressed = keycode;
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Position worldCoordinates = this.coordinates.screenToWorld(screenX, screenY);
        this.playerModel.setTarget(worldCoordinates);
        return true;
    }

}
