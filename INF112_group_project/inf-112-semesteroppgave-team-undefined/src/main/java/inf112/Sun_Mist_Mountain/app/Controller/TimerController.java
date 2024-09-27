package inf112.Sun_Mist_Mountain.app.Controller;

import inf112.Sun_Mist_Mountain.app.TaskHandle;
import inf112.Sun_Mist_Mountain.app.Timer;

public class TimerController {

    private static float TICK_INTERVAL = 0.2f;

    Timer timer;
    ControllableWorldModel worldModel;
    ControllablePlayerModel playerModel;

    private TaskHandle timeTaskHandle = null;
    private final Runnable timeTask;

    public TimerController(Timer timer, ControllableWorldModel world, ControllablePlayerModel player) {
        this.timer = timer;
        this.worldModel = world;
        this.playerModel = player;
        this.timeTask = this.worldModel::tick;
    }

    /**
     * Start this controller.
     */
    public void start() {
        this.timeTaskHandle = this.timer.schedule(this.timeTask, 0, TICK_INTERVAL);
    }

    /**
     * Stop this controller.
     */
    public void stop() {
        this.timeTaskHandle.cancel();
    }

}
