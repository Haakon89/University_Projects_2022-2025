package inf112.Sun_Mist_Mountain.app;

import com.badlogic.gdx.utils.Timer.Task;

/**
 * Wraps the libGDX Timer and Task classes to implement the interfaces expected
 * by the controller. This is done so the tests can use a different
 * implementation to predictably check that the world schedules behaviour
 * correctly.
 */
public class GdxTimer implements Timer {

    @Override
    public TaskHandle schedule(Runnable task, float delay, float interval) {
        var gdxTask = new Task() {
            @Override
            public void run() {
                task.run();
            }
        };

        return new GdxTaskHandle(com.badlogic.gdx.utils.Timer.schedule(gdxTask, delay, interval));
    }

    private static record GdxTaskHandle(Task task) implements TaskHandle {

        @Override
        public void cancel() {
            this.task.cancel();
        }

    }

}
