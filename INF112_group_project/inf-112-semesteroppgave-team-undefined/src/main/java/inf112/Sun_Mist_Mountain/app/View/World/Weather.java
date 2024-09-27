package inf112.Sun_Mist_Mountain.app.View.World;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Weather {
    private static final float FRAME_DURATION = 0.5f;
    private float stateTime;
    Animation<TextureRegion> animation;

    public Weather() {
        TextureRegion frame1 = new TextureRegion(new Texture("Weather/rain2_1.png"));
        TextureRegion frame2 = new TextureRegion(new Texture("Weather/rain2_2.png"));
        TextureRegion frame3 = new TextureRegion(new Texture("Weather/rain2_3.png"));
        TextureRegion frame4 = new TextureRegion(new Texture("Weather/rain2_4.png"));
        TextureRegion frame5 = new TextureRegion(new Texture("Weather/rain2_5.png"));
        TextureRegion[] animationFrames = {frame1, frame2, frame3, frame4, frame5};
        this.stateTime = 0f;
        this.animation = new Animation<TextureRegion>(FRAME_DURATION, animationFrames);
    }

    /**
     * oppdaterer tiden som er gått sånn at vi vet når man skal bytte animation frame.
     * @param deltaTime
     */
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }

    /**
     * gets the current frame of the animation dpending on the time that has passed.
     * @return
     */
    public TextureRegion getCurrentFrame() {
        return this.animation.getKeyFrame(stateTime, true);
    }

}
