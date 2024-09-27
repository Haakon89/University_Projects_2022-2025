package inf112.Sun_Mist_Mountain.app;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;

/**
 * A GameTest initializes a headless Gdx to allow spritesheets and resources to
 * be loaded.
 */
public class GameTest {

    @BeforeAll
    public static void setupGdx() {
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
        Gdx.gl = mock(GL20.class);
        Gdx.gl20 = mock(GL20.class);
    }

}
