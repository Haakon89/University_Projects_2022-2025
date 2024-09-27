package inf112.Sun_Mist_Mountain.app;

import java.util.ArrayDeque;
import java.util.Deque;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SunMistMountain extends Game implements Conductor {

    private SpriteBatch batch;

    private MainMenuScreen mainMenu;
    private PauseMenuScreen pauseMenu;
    private GameScreen game;
    private InstructionsScreen instructions;
    private ShopScreen shop;

    private final Deque<Screen> screenStack;

    public SunMistMountain() {
        this.screenStack = new ArrayDeque<>();
    }

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.mainMenu = new MainMenuScreen(this, this.batch);
        this.pauseMenu = new PauseMenuScreen(this, this.batch);
        this.game = new GameScreen(this.batch, this);
        this.instructions = new InstructionsScreen(this, this.batch);
        this.shop = new ShopScreen(this, this.game, this.batch);
        this.setScreen(this.mainMenu);
        this.screenStack.push(this.getScreen());
    }

    @Override
    public void startGame() {
    	this.screenStack.pollLast();
        this.setScreen(this.game);
       this.screenStack.push(this.getScreen());

    }

    @Override
    public void pauseGame() {
    	
       this.screenStack.removeLast();

        this.setScreen(this.pauseMenu);
      this.screenStack.push(this.getScreen());

    }

    @Override
    public void resumeGame() {
        this.screenStack.removeLast();
        
    	this.setScreen(this.game);
       this.screenStack.push(this.getScreen());

    }

    @Override
    public void dispose() {
        this.mainMenu.dispose();
        this.game.dispose();
    }

	@Override
	public void viewInstructions() {
	    this.setScreen(this.instructions);
	   this.screenStack.push(this.getScreen());
		
	}

	@Override
	public void viewLastScreen() {
			this.setScreen(screenStack.pollLast());
		
	}

	@Override
	public void viewShop() {
		this.screenStack.removeLast();
		this.setScreen(this.shop);
	      this.screenStack.push(this.getScreen());

		
	}
	
	
}
