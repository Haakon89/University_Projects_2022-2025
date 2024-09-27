package inf112.Sun_Mist_Mountain.app.Controller;

import java.util.HashSet;

import inf112.Sun_Mist_Mountain.app.TaskHandle;
import inf112.Sun_Mist_Mountain.app.Timer;

/**
 * Implements the {@link Timer} interface so it can be used in automatic tests.
 */
public class MockTimer implements Timer {

    private HashSet<MockTaskHandle> handles;

    public MockTimer() {
        this.handles = new HashSet<>();
    }

    @Override
    public MockTaskHandle schedule(Runnable task, float _delay, float _interval) {
        var handle = new MockTaskHandle(this, task);
        this.handles.add(handle);
        return handle;
    }

    /**
     * Run every scheduled task (regardless of their delay or interval), in an
     * arbitrary order.
     * @return the number of tasks that were run.
     */
    public void run() {
        for (var handle : this.handles) {
            handle.run();
        }
    }

    /**
     * @param handle will be removed from the set of handles.
     */
    private void cancel(MockTaskHandle handle) {
        this.handles.remove(handle);
    }

    public static class MockTaskHandle implements TaskHandle {

        private final MockTimer timer;
        private final Runnable task;
        private boolean isCanceled;

        public MockTaskHandle(MockTimer timer, Runnable task) {
            this.timer = timer;
            this.task = task;
            this.isCanceled = false;
        }

        /**
         * Run the scheduled task, if it has not been canceled.
         * @return {@code true} if the task has not been canceled, and therefore
         *         ran.
         */
        public boolean run() {
            if (!this.isCanceled) {
                this.task.run();
                return true;
            }

            return false;
        }

        @Override
        public void cancel() {
            this.timer.cancel(this);
            this.isCanceled = true;
        }

    }

}
