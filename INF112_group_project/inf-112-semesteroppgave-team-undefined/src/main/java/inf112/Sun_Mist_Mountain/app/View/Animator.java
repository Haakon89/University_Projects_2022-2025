package inf112.Sun_Mist_Mountain.app.View;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Controller.ControllableAnimator;

public class Animator implements ControllableAnimator {
    private static final int FRAME_WIDTH = 32;
    private static final int FRAME_HEIGHT = 64;
    private static final float FRAME_DURATION = 0.1f;

    private Texture sheet;
    private Map<AnimationState, TextureRegion[]> animations;
    private float stateTime;
    private AnimationState currentState;

    public Animator() {
        this.sheet = new Texture(Gdx.files.internal("Player/CharacterSpritesheet.png"));
        TextureRegion[][] tmp = TextureRegion.split(sheet, FRAME_WIDTH, FRAME_HEIGHT);
        
        animations = new HashMap<>();
        animations.put(AnimationState.IDLE_RIGHT, new TextureRegion[] { tmp[0][0] });
        animations.put(AnimationState.IDLE_LEFT, new TextureRegion[] { tmp[1][0] });
        animations.put(AnimationState.IDLE_BACK, new TextureRegion[] { tmp[2][0] });
        animations.put(AnimationState.IDLE, new TextureRegion[] { tmp[3][0] }); 
        animations.put(AnimationState.WALKING_RIGHT, new TextureRegion[] { tmp[0][1], tmp[0][2], tmp[0][3], tmp[0][4] }); 
        animations.put(AnimationState.WALKING_LEFT, new TextureRegion[] { tmp[1][1], tmp[1][2], tmp[1][3], tmp[1][4] }); 
        animations.put(AnimationState.WALKING_UP, new TextureRegion[] { tmp[2][1], tmp[2][2], tmp[2][3], tmp[2][4] }); 
        animations.put(AnimationState.WALKING_DOWN, new TextureRegion[] { tmp[3][1], tmp[3][2], tmp[3][3], tmp[3][4] }); 

        this.stateTime = 0f;
        this.currentState = AnimationState.IDLE;
    }

    /**
     * oppdaterer tiden som er gått sånn at vi vet når man skal bytte animation frame.
     * @param deltaTime
     */
    public void update(float deltaTime) {
        stateTime += deltaTime;
    }

    /**
     * ser på animasjonen i den nåværende animasjons staten, finner ut hvor mange frames som er i den animasjonen,
     * og finner indeksen til den TextureRegionen sånn at vi får riktig animasjons bilde.
     * @return Det animasjonsbildet som passer med tiden og animasjons staten
     */
    public TextureRegion getCurrentFrame() {
        TextureRegion[] currentAnimationFrames = animations.get(currentState);
        int frameCount = currentAnimationFrames.length;
        int index = (int)(stateTime / FRAME_DURATION) % frameCount;
        return currentAnimationFrames[index];
    }

    /**
     * Tar inn en ny AnimationState og endrer currentState til den nye hvis det ikke er den samme staten. 
     * Hvis staten blir endret så settes tiden tilbake til 0
     */
    public void setState(AnimationState newState) {
        if (currentState != newState) {
            currentState = newState;
            stateTime = 0;
        }
    }

    /**
     * fjerner texturen sheet når vi er ferdig med den.
     */
    public void dispose() {
        if (sheet != null) {
            sheet.dispose();
        }
    }

    /**
     * Hovedsaklig brukt for testing, den tar inn en AnimasjonState
     * @param animationState
     * @return den første framen som tilhører animasjonen til den gitte AnimationStaten.
     */
    public TextureRegion getFirstFrameOfState(AnimationState animationState) {
        return animations.get(animationState)[0];
    }
}
