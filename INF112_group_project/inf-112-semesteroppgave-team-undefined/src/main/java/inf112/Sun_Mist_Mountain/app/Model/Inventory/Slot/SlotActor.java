/* Copyright (c) 2014 PixelScientists
 * 
 * The MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
*/
package inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SlotActor extends ImageButton implements SlotListener {

    private static TextureRegion EMPTY = new TextureRegion(new Texture(Gdx.files.internal("Item/empty.png")), 32, 32);

    private final Slot slot;
    private final Skin skin;

    public SlotActor(Slot slot, Skin skin) {
        super(draw(slot, skin));

        this.slot = slot;
        this.slot.addListener(this);
        this.skin = skin;
    }

    @Override
    public void event() {
        this.setStyle(draw(this.slot, this.skin));
    }

    private static ImageButtonStyle draw(Slot slot, Skin skin) {
        ImageButtonStyle style = new ImageButtonStyle();

        TextureRegion itemTexture;
        if (slot.getItem() == null) {
            itemTexture = SlotActor.EMPTY;
        } else {
            itemTexture = slot.getItem().getTexture();
        }

        style.up = skin.getDrawable("button");
        style.down = skin.getDrawable("button-pressed");

        style.imageUp = new TextureRegionDrawable(itemTexture);
        style.imageDown = new TextureRegionDrawable(itemTexture);
        return style;
    }

}