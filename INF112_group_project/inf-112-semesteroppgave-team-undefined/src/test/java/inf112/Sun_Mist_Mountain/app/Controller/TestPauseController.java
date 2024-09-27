package inf112.Sun_Mist_Mountain.app.Controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.Test;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;

import inf112.Sun_Mist_Mountain.app.Conductor;

public class TestPauseController {

    @Test
    public void pausesOnE() {
        var conductor = mock(Conductor.class);
        var controller = new PauseController(conductor);

        assertTrue(controller.keyDown(Input.Keys.E));
        verify(conductor).pauseGame();
    }

    @Test
    public void doesNothingOnAnythingElse() {
        var conductor = mock(Conductor.class);
        var controller = new PauseController(conductor);

        for (int i = 0; i < Input.Keys.MAX_KEYCODE; i++) {
            if (i == Input.Keys.E) {
                continue;
            }

            assertFalse(controller.keyDown(i));
        }

        verifyNoInteractions(conductor);
    }

    @Test
    public void worksInAMultiplexer() {
        var alwaysTrueAdapter = new InputAdapter() {
            @Override
            public boolean keyDown(int _keycode) {
                return true;
            }
        };

        var conductor = mock(Conductor.class);
        var controller = new PauseController(conductor);
        var processor = new InputMultiplexer(controller, alwaysTrueAdapter);

        processor.keyDown(Input.Keys.A);
        verifyNoInteractions(conductor);

        processor.keyDown(Input.Keys.E);
        verify(conductor).pauseGame();
    }

}
