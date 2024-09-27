package inf112.Sun_Mist_Mountain.app.Controller;

import java.util.Random;
import java.util.random.RandomGenerator;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import inf112.Sun_Mist_Mountain.app.Model.Actions.UseActions;
import inf112.Sun_Mist_Mountain.app.Model.Entities.EntityFactory;
import inf112.Sun_Mist_Mountain.app.Model.Item.Item;
import inf112.Sun_Mist_Mountain.app.Model.Item.ItemStack;
import inf112.Sun_Mist_Mountain.app.Model.Item.Seeds.Carrot;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.PlacedTile;
import inf112.Sun_Mist_Mountain.app.Model.Tiles.Tile;

public class ToolbarController extends InputAdapter {
    private static int ASCII_NUMERAL_FOR_ZERO = 7;
    private static int ASCII_NUMERAL_FOR_NINE = 16;

    private final RandomGenerator rng = new Random();
    private final ControllablePlayerModel playerModel; 
    private final ControllableToolbar inventory;
    private final ControllableInventoryActor actor;
    private final SoundController sounds;

    public ToolbarController(ControllableToolbar inventory, ControllablePlayerModel player, ControllableInventoryActor actor, SoundController sounds) {
        this.inventory = inventory;
        this.playerModel = player;
        this.actor = actor;
        this.sounds = sounds;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode >= ASCII_NUMERAL_FOR_ZERO && keycode <= ASCII_NUMERAL_FOR_NINE) {
            this.inventory.setActiveIndex(keycode-8);
            return true;
        } if (keycode == Input.Keys.TAB) {
            this.inventory.changeToolbar();
            return true;
        } if (keycode == Input.Keys.I) {
            this.actor.changeVisibility();
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            if (ToolbarController.this.playerModel.getCurrentStamina() <= 0 && !ToolbarController.this.inventory.getActiveItem().isEqual("carrot")) {
                return false;
            }
            var target = this.playerModel.getTarget();
            if (target != null) {
                // Play sound before using the item so the sounds see what is
                // being acted upon and not just what it turned into
                this.sounds.interact(target);
            }

            var actions = new Actions(this.rng, target);
            this.inventory.useActiveItem(actions, target);
            return actions.finish();
        }

        return false;

    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        Integer currentIndex = this.inventory.getActiveIndex();
        if (amountY < 0) {
            Integer newIndex = currentIndex - 1;
            this.inventory.setActiveIndex(newIndex);
            return true;
        } else if(amountY > 0) {
            Integer newIndex = currentIndex + 1;
            this.inventory.setActiveIndex(newIndex);
            return true;
        }
        return false;
    }

    private class Actions extends inf112.Sun_Mist_Mountain.app.Model.World.Actions implements UseActions {
        private final PlacedTile tile;

        private boolean anythingHappened = false;
        private boolean destroyItem = false;
        private int exertion = 0;

        public Actions(RandomGenerator rng, PlacedTile tile) {
            super(rng, tile);
            this.tile = tile;
        }

        /**
         * if an action happened then we will lower the players stamina, if the action destroyed an item, then we will add
         * that item to the inventory.
         * @return true if something happened, false otherwise.
         */
        public boolean finish() {
            if (this.anythingHappened) {
                ToolbarController.this.playerModel.changeStamina(exertion);

                if (this.destroyItem) {
                    ToolbarController.this.inventory.takeActiveItem(1);
                }

                return true;
            }

            return false;
        }

        @Override
        public void destroyItem() {
            this.destroyItem = true;
        }

        @Override
        public void exert(int amount) {
            this.exertion -= amount;
        }

        @Override
        public void changeStamina(int by) {
            this.anythingHappened = true;
            ToolbarController.this.playerModel.changeStamina(by);
        }

        @Override
        public void dropItems(ItemStack items) {
            this.anythingHappened = true;
            ToolbarController.this.inventory.placeItemInInventory(items.getItem(), items.getAmount());
        }

        @Override
        public void markHappened() {
            super.markHappened();
            this.anythingHappened = true;
        }

        @Override
        public void destroyEntity() {
            super.destroyEntity();
            this.anythingHappened = true;
        }

        @Override
        public void placeEntity(EntityFactory factory) {
            // Only if there is no entity here now should something happen.
            this.anythingHappened = !this.tile.hasEntity();
            super.placeEntity(factory);
        }

        @Override
        public void replaceEntity(EntityFactory factory) {
            super.replaceEntity(factory);
            this.anythingHappened = true;
        }

        @Override
        public void changeTile(Tile to) {
            super.changeTile(to);
            this.anythingHappened = true;
        }

        @Override
        public void useWater() {
            super.useWater();
            this.anythingHappened = true;
        }

    }

}
