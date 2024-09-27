package no.uib.inf101.sem2;

import java.io.IOException;

import javax.swing.JFrame;

import no.uib.inf101.sem2.MusicSFX.PlatformerMusic;
import no.uib.inf101.sem2.platformer.controller.PlayerController;
import no.uib.inf101.sem2.platformer.model.GameState;
import no.uib.inf101.sem2.platformer.model.PlatformerBoard;
import no.uib.inf101.sem2.platformer.model.PlatformerModel;
import no.uib.inf101.sem2.platformer.model.player.PlayerFactory;
import no.uib.inf101.sem2.platformer.model.player.RandomPlayerFactory;
import no.uib.inf101.sem2.platformer.view.PlatformerView;

public class Main {
  public static final String WINDOW_TITLE = "INF101 Platformer";
  public static void main(String[] args) throws IOException {

    PlatformerBoard board = new PlatformerBoard();
    PlayerFactory builder = new RandomPlayerFactory();
    PlatformerModel model = new PlatformerModel(board, builder);
    PlatformerView view = new PlatformerView(model);
    PlayerController controller = new PlayerController(model, view);
    PlatformerMusic music = new PlatformerMusic("src/main/java/no/uib/inf101/sem2/MusicSFX/castle.mid");
    music.play();

    JFrame frame = new JFrame(WINDOW_TITLE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(view);
    frame.pack();
    frame.setVisible(true);

    view.addKeyListener(controller);

    while (true) {
        controller.update();
        try {
          Thread.sleep(15);
        } catch (InterruptedException e) {
          
          e.printStackTrace();
        }
    }
  }
}

