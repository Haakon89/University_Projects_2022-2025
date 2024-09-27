package inf112.Sun_Mist_Mountain.app.Controller;

import inf112.Sun_Mist_Mountain.app.View.AnimationState;

public interface ControllableAnimator {

    /**
     * used to set which state the animator should be in, 
     * for example if we set it to move to the right the state should be 
     * MOVING_RIGHT
     * @param newState
     */
    public void setState(AnimationState newState);

}
