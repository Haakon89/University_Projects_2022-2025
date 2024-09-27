package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.List;
import java.util.function.Consumer;

import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.Model.Math.Variants;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

public class OggStepSounds implements StepSounds {

    private final Variants<Sound> grass;
    private final Variants<Sound> snow;
    private final Variants<Sound> dirt;
    private final Variants<Sound> empty;

    public OggStepSounds() {
        this.grass = SoundResources.fromPrefix("Sound/step-grass", "ogg", 4);
        this.snow = SoundResources.fromPrefix("Sound/step-snow", "ogg", 7);
        this.dirt =  SoundResources.fromPrefix("Sound/step-dirt", "ogg", 7);
        this.empty = new Variants<>(List.of());
    }

    @Override
    public Variants<Sound> on(Sprite sprite) {
        return switch (sprite.base()) {
            case Grass -> this.grass;
            case Dirt, Earth -> this.dirt;

            // Stone tiles are not walkable
            case Stone -> this.empty;
        };
    }

    @Override
    public void all(Consumer<Sound> fn) {
        this.grass.all(fn);
        this.snow.all(fn);
        this.dirt.all(fn);
        this.empty.all(fn);
    }

}
