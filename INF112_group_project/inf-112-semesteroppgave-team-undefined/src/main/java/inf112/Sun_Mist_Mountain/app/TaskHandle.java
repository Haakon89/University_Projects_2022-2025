package inf112.Sun_Mist_Mountain.app;

public interface TaskHandle {

    /**
     * Cancels the task so it won't be run unless scheduled again.
     */
    public void cancel();

}
