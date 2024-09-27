package inf112.Sun_Mist_Mountain.app;

public interface Timer {

    /**
     * @param task the task to schedule.
     * @param delay the delay in seconds before the task will first run.
     * @param interval the delay in seconds between repetions of this task.
     * @return a handle to the task that can be used to cancel it.
     */
    public TaskHandle schedule(Runnable task, float delay, float interval);

}
