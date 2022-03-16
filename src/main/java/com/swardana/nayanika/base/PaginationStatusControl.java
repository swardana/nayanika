/*
 * Nayanika is a picture viewer application.
 * Copyright (C) 2022  Sukma Wardana
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.swardana.nayanika.base;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An observable {@link PaginationStatus}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class PaginationStatusControl implements PaginationStatus {

    private final IntegerProperty index;
    private final BooleanProperty beginning;
    private final BooleanProperty last;

    /**
     * Creates new PaginationStatusControl.
     */
    public PaginationStatusControl() {
        this.index = new SimpleIntegerProperty(this, "index", 0);
        this.beginning = new SimpleBooleanProperty(this, "beginning", true);
        this.last = new SimpleBooleanProperty(this, "last", true);
    }

    /**
     * THe observable current index position of pictures.
     *
     * @return the current {@link #index()} position.
     */
    public final ReadOnlyIntegerProperty indexProperty() {
        return this.index;
    }

    /**
     * The observable condition to check whether pictures already on the beginning or not.
     *
     * @return {@code true} if {@link #index()} is already on the beginning.
     */
    public final ReadOnlyBooleanProperty beginningProperty() {
        return this.beginning;
    }

    /**
     * The observable condition to check whether pictures already on the last or not.
     *
     * @return {@code true} if {@link #index()} is already on the last.
     */
    public final ReadOnlyBooleanProperty lastProperty() {
        return this.last;
    }

    /**
     * An operation to update the observable state.
     * <p>
     *     Whenever there are indication that {@link PaginationStatus}
     *     state is changed, MUST call this method.
     * </p>
     */
    public void onChange() {
        this.index.setValue(this.index());
        this.beginning.setValue(this.isBeginning());
        this.last.setValue(this.isLast());
    }

}
