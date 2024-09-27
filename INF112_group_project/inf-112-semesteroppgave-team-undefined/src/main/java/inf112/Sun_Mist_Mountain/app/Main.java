package inf112.Sun_Mist_Mountain.app;


import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        var cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("Sun Mist Mountain");
        cfg.setWindowedMode(1280, 720);

        new Lwjgl3Application(new SunMistMountain(), cfg);
    }
}