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
 * 
 * take() & add() metoder er inspirert av øvrenevnte.
*/
package inf112.Sun_Mist_Mountain.app.Model.Inventory.Slot;

import java.util.ArrayList;
import java.util.List;

import inf112.Sun_Mist_Mountain.app.Model.Item.Item;

/**
 * A slot contains some number of items of the same type.
 */
public class Slot {

    /**
     * The maximum number of items a slot can hold.
     */
    public static final Integer MAX_STACK = 64;

    private Item item;
    private int amount;

    private final List<SlotListener> listeners = new ArrayList<>();

    /**
     * Create an empty slot.
     */
    public Slot() {
        this.item = null;
        this.amount = 0;
    }

    /**
     * Can hold an <code>Item</code> and an <code>amount</code>
     * @param item - <code>Item</code> to be held.
     * @param amount - Amount of <code>Item</code> in <code>Slot</code>.
     */
    public Slot(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    /**
     * @return <code>item</code> in this slot.
     */
    public Item getItem() {
        return this.item;
    }

    /**
     * @return <code>amount</code> in this slot.
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Adds <code>item</code> and <code>amount</code> if Slot either
     *      contains the same <code>Item</code> or
     *      slot already holds <code>null</code>.
     * @param item - <code>item</code> to be added.
     * @param amount - <code>amount</code> to be added.
     * @return <code>true</code> if item was added,
     *         <code>false</code> otherwise.
     */
    public int add(Item item, int amount) {
        if (this.item == null || this.item.getClass() == item.getClass()) {
            var total = this.amount + amount;
            var actual = Math.min(total, MAX_STACK);
            var remaining = total - actual;

            this.amount = actual;
            this.item = item;
            this.notifyEvent();

            return remaining;
        }

        return amount;
    }

    /**
     * Subtracts <code>taken</code> from the amount that is held.
     * If the amount is 0, the item held turns to <code>null</code>.
     * @param taken - amount to be subtracted from the amount.
     * @return <code>true</code> if amount was subtracted,
     *         <code>false</code> if <code>taken</code> was larger than amount.
     */
    public boolean take(int taken) {
        if (!this.item.isReuseable()) {
            if (this.amount >= taken) {
                this.amount -= taken;
                if (this.amount == 0) {
                    this.item = null;
                }
                this.notifyEvent();
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * @return {@code true} if this slot does not contain any items.
     */
    public boolean isEmpty() {
        return this.item == null;
    }

    private void notifyEvent() {
        for (SlotListener listener : this.listeners) {
            listener.event();
        }
    }

    public void addListener(SlotListener listener) {
        this.listeners.add(listener);
    }

}
