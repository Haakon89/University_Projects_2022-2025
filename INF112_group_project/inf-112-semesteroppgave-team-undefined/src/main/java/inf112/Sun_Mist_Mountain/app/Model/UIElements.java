package inf112.Sun_Mist_Mountain.app.Model;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import inf112.Sun_Mist_Mountain.app.Model.Inventory.Inventory;
import inf112.Sun_Mist_Mountain.app.Model.Player.Player;
import inf112.Sun_Mist_Mountain.app.Model.World.PixelPosition;
import inf112.Sun_Mist_Mountain.app.Model.World.Seasons;
import inf112.Sun_Mist_Mountain.app.Model.World.World;
import inf112.Sun_Mist_Mountain.app.View.ViewableModel;

public class UIElements implements ViewableModel{
    private Player player;
    private World world;
    private Inventory toolbar;
    private final int NUMBER_SHIFT_PER_TEN = 7;
    private final int STAMINA_WIDTH = 37;
    private final int INFO_WIDTH = 200;
    private final int INFO_HEIGHT = 150;

    private final BitmapFont font;
    private final TextureRegion frame, infobox, stamina, toolbarBackground;
    private final Texture timeBarTexture;

    public UIElements(Player player, World world, Inventory toolbar) {
        this.player = player;
        this.world = world;
        this.toolbar = toolbar;

        // Use the default font
        this.font = new BitmapFont();
        this.font.setColor(Color.YELLOW);

        this.frame = UIElements.getFrameTexture();
        this.infobox = UIElements.getInfobox();
        this.stamina = UIElements.getStaminaTexture();
        this.toolbarBackground = UIElements.getToolbarBackgroundTexture();

        this.timeBarTexture = UIElements.createRectangle();
    }

    @Override
    public void draw(SpriteBatch batch) {
        PixelPosition staminapos = new PixelPosition(0, Gdx.graphics.getWidth() - STAMINA_WIDTH-25);
        PixelPosition infopos = new PixelPosition(Gdx.graphics.getHeight() - INFO_HEIGHT, Gdx.graphics.getWidth() - INFO_WIDTH);
        PixelPosition toolbarpos = this.getToolbarPosition();
        ArrayList<TextureRegion> items = this.getTextureItems();

        batch.draw(stamina, staminapos.col(), staminapos.row());
        batch.draw(infobox, infopos.col(), infopos.row());
        this.font.draw(batch, getMoney(), Gdx.graphics.getWidth()-(getMoney().length()*NUMBER_SHIFT_PER_TEN)-25, infopos.row()+28);
        this.font.setColor(Color.WHITE);
        this.font.draw(batch, getDay(), Gdx.graphics.getWidth()-(getDay().length()*NUMBER_SHIFT_PER_TEN)-25, infopos.row()+60);
        this.font.draw(batch, getTypeOfDay(), Gdx.graphics.getWidth()-75, infopos.row()+60);
        this.font.draw(batch, getSeason(), Gdx.graphics.getWidth()-75, infopos.row()+93);
        batch.setColor(getRectangleColor());
        batch.draw(this.timeBarTexture, staminapos.col()+15, staminapos.row(), 10, getRectangleHeight());
        batch.setColor(Color.WHITE);
        batch.draw(this.timeBarTexture, getTimeTrackerPosition(), infopos.row()+110, 2, 30);
        
        // TOOLBAR
        batch.draw(this.toolbarBackground, toolbarpos.col(), toolbarpos.row());
        for (int i = 0; i < Inventory.TOOLBAR_SIZE; i++) {
            if (items.get(i) == null)
                continue;
                batch.draw(items.get(i), toolbarpos.col() + Inventory.TOOLBAR_SLOT_SIZE * i, toolbarpos.row());
        }
        batch.draw(this.frame, toolbarpos.col() + Inventory.TOOLBAR_SLOT_SIZE * this.toolbar.getActiveIndex(), toolbarpos.row());


    }
    /**
     * checks how much money the player has and
     * @return that value as a string.
     */
    private CharSequence getMoney() {
        return String.valueOf(this.player.getMoney());
    }

    /**
     * checks how many days have passed and
     * @return that value as a string.
     */
    private CharSequence getDay() {
        return String.valueOf(this.world.getDaysPassed());
    }

    /**
     * checks which season we are in and
     * @return the season as a string.
     */
    private CharSequence getSeason() {
        if (this.world.getSeason().equals(Seasons.SPRING)) {
            return "SPRING";
        } else if (this.world.getSeason().equals(Seasons.SUMMER)) {
            return "SUMMER";
        } else if (this.world.getSeason().equals(Seasons.AUTUMN)) {
            return "AUTUMN";
        } else {
            return "WINTER";
        }
    }

    /**
     * checks how many days have passed then calculates what weekday it is
     * @return the day as a string
     */
    private CharSequence getTypeOfDay() {
        if (this.world.getDaysPassed() == 0 || (this.world.getDaysPassed() % 7 == 0)) {
            return "MON";
        } else if (this.world.getDaysPassed() == 1 || (this.world.getDaysPassed() % 8 == 0)) {
            return "TUE";
        } else if (this.world.getDaysPassed() == 2 || (this.world.getDaysPassed() % 9 == 0)) {
            return "WED";
        } else if (this.world.getDaysPassed() == 3 || (this.world.getDaysPassed() % 10 == 0)) {
            return "THU";
        } else if (this.world.getDaysPassed() == 4 || (this.world.getDaysPassed() % 11 == 0)) {
            return "FRI";
        } else if (this.world.getDaysPassed() == 5 || (this.world.getDaysPassed() % 12 == 0)) {
            return "SAT";
        } else if (this.world.getDaysPassed() == 6 || (this.world.getDaysPassed() % 13 == 0)) {
            return "SUN";
        }
        return "NULL";
    }

    /**
     * calculates the height of the rectangle measuring the player's stamina
     * @return that height as an int
     */
    private int getRectangleHeight() {
        float currentStaminaPercentage = this.player.getStamina()/100f;
        int rectangleHeight = Math.round(170*currentStaminaPercentage);
        return rectangleHeight;
    }

    /**
     * looks at how much stamina is left and changes the color of the rectangle
     * depending on how low the stamina is.
     * @return
     */
    private Color getRectangleColor() {
        int currentStaminaPercentage = this.player.getStamina();
        if (currentStaminaPercentage > 50) {
            return Color.GREEN;
        } else if (currentStaminaPercentage > 25) {
            return Color.ORANGE;
        } else {
            return Color.RED;
        }
    }

    /**
     * checks how much time has passed in the day
     * @return the position of the time tracker
     */
    public int getTimeTrackerPosition() {
        int frameLeft = 11;
        int frameRight = 14;

        int track = INFO_WIDTH - frameLeft - frameRight;

        double part = (double) this.world.getTicksPassed() / World.TICKS_PER_DAY;
        int x = frameLeft + (int) (part * track);

        return Gdx.graphics.getWidth() - INFO_WIDTH + x;
    }

    /**
     * gets the image for the stamina meter
     * @return the timage as TextureRegion
     */
    private static TextureRegion getStaminaTexture() {
        var stamninaTexture = new Texture(Gdx.files.internal("UI/stamina_meter.png"));
        stamninaTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

        TextureRegion textureRegion = new TextureRegion(stamninaTexture);
        textureRegion.setRegion(0, 0, 36, 226);

        return textureRegion;
    }

    /**
     * gets the image for the info box
     * @return the timage as TextureRegion
     */
    private static TextureRegion getInfobox() {
        var infoTexture = new Texture(Gdx.files.internal("UI/time_date_cash.png"));
        infoTexture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

        TextureRegion textureRegion = new TextureRegion(infoTexture);
        textureRegion.setRegion(0, 0, 200, 150);

        return textureRegion;
    }

    private static Texture createRectangle() {
        Texture pixelTexture = new Texture(1, 1, Pixmap.Format.RGBA8888);
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        pixelTexture.draw(pixmap, 0, 0);
        pixmap.dispose();
        return pixelTexture;
    }

    private static TextureRegion getFrameTexture() {
        Texture texture = new Texture(Gdx.files.internal("Toolbar/frame.png"));
        TextureRegion textureRegion = new TextureRegion(texture);
        return textureRegion;

    }

    private ArrayList<TextureRegion> getTextureItems() {
        ArrayList<TextureRegion> listOfItems = new ArrayList<>();

        for (int i = 0; i < Inventory.TOOLBAR_SIZE; i++) {
            if (this.toolbar.getActiveToolbar()[i].getItem() == null) {
                listOfItems.add(null);
                continue; 
            }
            TextureRegion textureRegion = this.toolbar.getActiveToolbar()[i].getItem().getTexture();
            listOfItems.add(textureRegion);
        }

        return listOfItems;
    }

    private static TextureRegion getToolbarBackgroundTexture() {
        Texture texture = new Texture(Gdx.files.internal("Toolbar/toolbar.png"));
        texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        TextureRegion textureRegion = new TextureRegion(texture);
        textureRegion.setRegion(0, 0, texture.getWidth()*Inventory.TOOLBAR_SIZE, texture.getHeight());
        return textureRegion;
    }

    private PixelPosition getToolbarPosition() {
        return new PixelPosition(0, Gdx.graphics.getWidth()/2 - (Inventory.TOOLBAR_SLOT_SIZE * (Inventory.TOOLBAR_SIZE/2)));
    }
}
