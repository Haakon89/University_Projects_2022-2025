package inf112.Sun_Mist_Mountain.app.View;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TestAnimator {
    
    @BeforeAll
    public static void setupGdx() {
        // Set up a headless application so libGDX can load properly
        var config = new HeadlessApplicationConfiguration();
        var listener = new ApplicationListener() {
            @Override
            public void create() { }

            @Override
            public void resize(int _width, int _height) { }

            @Override
            public void render() { }

            @Override
            public void pause() { }

            @Override
            public void resume() { }

            @Override
            public void dispose() { }
        };

        new HeadlessApplication(listener, config);
        Gdx.gl = Mockito.mock(GL20.class);
    }

    private AnimationState animState;
    private Animator animator;

    @BeforeEach
    public void setUp() {
        this.animState = AnimationState.IDLE;
        this.animator = new Animator();
    }

    @Test
    public void testIfStartsInIdlePosition() {
        TextureRegion currentFrame = animator.getCurrentFrame();
        TextureRegion expectedFrame = animator.getFirstFrameOfState(AnimationState.IDLE);
        assertEquals(expectedFrame, currentFrame);        
    }

    @Test
    public void testCorrectFrameWhenMovingLeft() {
        animState = AnimationState.WALKING_LEFT;
        this.animator.setState(animState);
        this.animator.update(0);
        TextureRegion currentFrame = animator.getCurrentFrame();
        TextureRegion expectedFrame = animator.getFirstFrameOfState(AnimationState.WALKING_LEFT);
        assertEquals(expectedFrame, currentFrame);
        
    }

    @Test
    public void testCorrectFrameWhenMovingRight() {
        animState = AnimationState.WALKING_RIGHT;
        this.animator.setState(animState);
        this.animator.update(0);
        TextureRegion currentFrame = animator.getCurrentFrame();
        TextureRegion expectedFrame = animator.getFirstFrameOfState(AnimationState.WALKING_RIGHT);
        assertEquals(expectedFrame, currentFrame);
        
    }

    @Test
    public void testCorrectFrameWhenMovingUp() {
        animState = AnimationState.WALKING_UP;
        this.animator.setState(animState);
        this.animator.update(0);
        TextureRegion currentFrame = animator.getCurrentFrame();
        TextureRegion expectedFrame = animator.getFirstFrameOfState(AnimationState.WALKING_UP);
        assertEquals(expectedFrame, currentFrame);
        
    }

    @Test
    public void testCorrectFrameWhenMovingDown() {
        animState = AnimationState.WALKING_DOWN;
        this.animator.setState(animState);
        this.animator.update(0);
        TextureRegion currentFrame = animator.getCurrentFrame();
        TextureRegion expectedFrame = animator.getFirstFrameOfState(AnimationState.WALKING_DOWN);
        assertEquals(expectedFrame, currentFrame);
        
    }
}
