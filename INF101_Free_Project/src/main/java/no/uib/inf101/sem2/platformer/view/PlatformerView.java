package no.uib.inf101.sem2.platformer.view;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import no.uib.inf101.sem2.grid.GridCell;
import no.uib.inf101.sem2.platformer.model.DoubleJump;
import no.uib.inf101.sem2.platformer.model.GameState;
import no.uib.inf101.sem2.platformer.model.MovementState;
import no.uib.inf101.sem2.platformer.model.PlayerDirection;
import no.uib.inf101.sem2.platformer.model.WallJump;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class PlatformerView extends JPanel {
  
  private ViewablePlatformerModel model;
  private GameState gameState;

  public PlatformerView(ViewablePlatformerModel model) {
    this.setFocusable(true);
    this.model = model;
    this.gameState = this.model.getGameState();
    this.setPreferredSize(new Dimension(1000, 500));
  }

  public static BufferedImage flipHorizontally(BufferedImage image) {
    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
    tx.translate(-image.getWidth(null), 0);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    return op.filter(image, null);
}
  
  /**
   * as long as the game is in ACTIVE_STATE it will draw the board and the tetrominos, 
   * if it moves to GAME_OVER it creates a seethrough overlay and gives a Game Over message
   * @param g2D takes in a Graphics2D that is used to draw the board and the tetrominos
   */
  private void drawGame(Graphics2D g2D) {
    if (this.gameState.equals(GameState.ACTIVE_GAME)) {
      BufferedImage player = null;
      BufferedImage level = null;
      BufferedImage dJump = null;
      BufferedImage wJump = null;

      try {
        player = ImageIO.read(new File("src/main/java/no/uib/inf101/sem2/LevelLayouts/" + this.model.getPlayerFrame() + ".png"));
        level = ImageIO.read(new File("src/main/java/no/uib/inf101/sem2/LevelLayouts/Tutorial_graphics.png"));
        dJump = ImageIO.read(new File("src/main/java/no/uib/inf101/sem2/LevelLayouts/D_Jump.png"));
        wJump = ImageIO.read(new File("src/main/java/no/uib/inf101/sem2/LevelLayouts/W_Jump.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      if (model.getPlayerDirection().equals(PlayerDirection.LEFT)) {
        player = flipHorizontally(player);
      }

      drawPlayer(g2D, this.model.getPlayerTilesOnBoard(), player, level);

      if (model.getDoubleJumpState().equals(DoubleJump.INACTIVE)) {
        g2D.drawImage(dJump, 920, 340, null);
      }
      if (model.getWallJumpState().equals(WallJump.INACTIVE)) {
        g2D.drawImage(wJump, 500, 80, null);
      }
    } 
    if (this.model.getGameState() == GameState.JUMPTUTORIAL) {
      g2D.setColor(new Color(0, 0, 0, 134));
      g2D.fill(new Rectangle2D.Double(0, 0, this.model.getDimension().cols(), this.model.getDimension().rows()));
      g2D.setColor(Color.RED);
      g2D.setFont(new Font("Arial", Font.BOLD, 20));
      g2D.drawString("Welcome to the tutorial level.", 380, 225);
      g2D.drawString("Use the arrow keys to move the character, press UP to jump and RIGHT and LEFT to run.", 75, 250);
      g2D.drawString("Press SPACE to start.", 400, 275);
    }
    if (this.model.getGameState() == GameState.DOUBLEJUMPTUTORIAL) {
      g2D.setColor(new Color(0, 0, 0, 134));
      g2D.fill(new Rectangle2D.Double(0, 0, this.model.getDimension().cols(), this.model.getDimension().rows()));
      g2D.setColor(Color.RED);
      g2D.setFont(new Font("Arial", Font.BOLD, 10));
      g2D.drawString("You picked up the double jump powerup.", 750, 300);
      g2D.drawString("When in the air press up again to do an extra jump.", 720, 315);
      g2D.drawString("Press SPACE to continue.", 790, 330);
        
    }
    if (this.model.getGameState() == GameState.WALLJUMPTUTORIAL) {
      g2D.setColor(new Color(0, 0, 0, 134));
      g2D.fill(new Rectangle2D.Double(0, 0, this.model.getDimension().cols(), this.model.getDimension().rows()));
      g2D.setColor(Color.RED);
      g2D.setFont(new Font("Arial", Font.BOLD, 10));
      g2D.drawString("You picked up the wall jump powerup.", 450, 70);
      g2D.drawString("When you jump onto a wall you will now slide down it.", 420, 85);
      g2D.drawString("Press SPACE to continue.", 480, 100);

    }
      
    
    
  }

  public void drawPlayer(Graphics2D g2D, Iterable<GridCell<Character>> cells, BufferedImage player, BufferedImage level) {
    int counter = 0;
    
    for (GridCell<Character> cell : cells) {
      if(counter == 0) {
        if (player != null && level != null) {
          //g2D.drawImage(camera, 0, 0, null);
          g2D.drawImage(level, 0, 0, null);
          
          g2D.drawImage(player, cell.cp().col(), cell.cp().row(), null);
          counter++;
        }
      }
    }
    counter = 0;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    drawGame(g2);
  }
}
