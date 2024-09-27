package inf112.Sun_Mist_Mountain.app.View.Sound;

import java.util.function.Consumer;

import com.badlogic.gdx.audio.Sound;

import inf112.Sun_Mist_Mountain.app.Model.Math.Variants;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Sprite;

public class OggUseSounds implements UseSounds {

    private final Variants<Sound> mine;
    private final Variants<Sound> till;

    public OggUseSounds() {
        this.mine = SoundResources.fromPrefix("Sound/hit-stone", "ogg", 5);
        this.till = SoundResources.fromPrefix("Sound/step-dirt", "ogg", 7);
    }

    @Override
    public Variants<Sound> interact(Sprite sprite) {
        return switch (sprite.base()) {
            case Stone -> this.mine;
            default -> this.till;
        };
    }

    @Override
    public void all(Consumer<Sound> fn) {
        this.mine.all(fn);
        this.till.all(fn);
    }

}
