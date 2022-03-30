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

package com.swardana.nayanika.gui.frame;

import com.swardana.nayanika.base.Gallery;
import com.swardana.nayanika.base.PaginationStatusControl;
import com.swardana.nayanika.base.PictureImage;
import com.swardana.nayanika.base.PicturesNavigation;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.CacheHint;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

import java.util.List;

/**
 * A picture frame visual.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public final class FrameVisual extends PaginationStatusControl
    implements PicturesNavigation, Gallery, FrameView {

    private final ScrollPane pane;
    private final FramePane frame;
    private final ImageView image;
    private final Group content;

    private final FrameBehavior behavior;

    private List<PictureImage> pictures;
    private int lastIdx;
    private int index;

    private final ObjectProperty<Point2D> mouseAnchor;
    private final EventHandler<ScrollEvent> scrolledAction = new EventHandler<ScrollEvent>() {
        @Override
        public void handle(final ScrollEvent event) {
            event.consume();
            if (event.getDeltaY() == 0) {
                return;
            }
            behavior.onViewZoom(
                event.getDeltaY(),
                image.getScaleX(),
                image.getScaleY()
            );
        }
    };
    private final ChangeListener<Bounds> boundsAction = new ChangeListener<Bounds>() {
        @Override
        public void changed(
            final ObservableValue<? extends Bounds> observable,
            final Bounds oldBounds,
            final Bounds newBounds
        ) {
            frame.setPrefSize(
                newBounds.getWidth(),
                newBounds.getHeight()
            );
        }
    };
    private final EventHandler<MouseEvent> mouseClickedAction = new EventHandler<MouseEvent>() {
        @Override
        public void handle(final MouseEvent event) {
            behavior.onViewMouseClicked(event.getClickCount());
        }
    };
    private final EventHandler<MouseEvent> mousePressedAction = new EventHandler<MouseEvent>() {
        @Override
        public void handle(final MouseEvent event) {
            mouseAnchor.set(
                new Point2D(
                    event.getX(),
                    event.getY()
                )
            );
        }
    };
    private final EventHandler<MouseEvent> mouseDraggedAction = new EventHandler<MouseEvent>() {
        @Override
        public void handle(final MouseEvent event) {
            event.consume();
            behavior.onViewMouseDragged(
                event.getX(),
                mouseAnchor.get().getX(),
                event.getY(),
                mouseAnchor.get().getY()
            );
        }
    };

    /**
     * Creates new FrameVisual.
     */
    public FrameVisual() {
        this.pane = new ScrollPane();
        this.frame = new FramePane();
        this.image = new ImageView();
        this.content = new Group();

        this.behavior = new FrameBehavior(this);

        this.mouseAnchor = new SimpleObjectProperty<>(this, "mouse", null);

        this.initGraphics();
        this.registerListeners();
    }

    @Override
    public void exhibit(final List<PictureImage> pics) {
        this.pictures = pics;
        this.index = 0;
        this.lastIdx = pics.size();
        this.onChange();
    }

    @Override
    public void exhibit(final List<PictureImage> pics, final PictureImage curr) {
        this.exhibit(pics);
        this.jump(curr);
        this.onChange();
    }

    @Override
    public int index() {
        return this.index;
    }

    @Override
    public int size() {
        return this.lastIdx;
    }

    @Override
    public void next() {
        if (!this.isLast()) {
            this.index++;
            this.onChange();
        }
    }

    @Override
    public void previous() {
        if (!this.isBeginning()) {
            this.index--;
            this.onChange();
        }
    }

    @Override
    public void last() {
        if (!this.isLast()) {
            this.index= this.lastIdx - 1;
            this.onChange();
        }
    }

    @Override
    public void beginning() {
        if (!this.isBeginning()) {
            this.index = 0;
            this.onChange();
        }
    }

    @Override
    public Parent parent() {
        return this.pane;
    }

    @Override
    public void focus() {
        this.pane.setFocusTraversable(true);
        this.pane.requestFocus();
    }

    @Override
    public void rescalePicture(final double horizontal, final double vertical) {
        this.image.setScaleX(horizontal);
        this.image.setScaleY(vertical);
    }

    @Override
    public double horizontalScrollOffset() {
        double extraWidth = this.content.getLayoutBounds().getWidth()
            - this.pane.getViewportBounds().getWidth();

        double hScrollProportion = (this.pane.getHvalue() - this.pane.getHmin())
            / (this.pane.getHmax() - this.pane.getHmin());

        double scrollXOffset = hScrollProportion * Math.max(0, extraWidth);

        return scrollXOffset;
    }

    @Override
    public double verticalScrollOffset() {
        double extraHeight = this.content.getLayoutBounds().getHeight()
            - this.pane.getViewportBounds().getHeight();

        double vScrollProportion = (this.pane.getVvalue() - this.pane.getVmin())
            / (this.pane.getVmax() - this.pane.getVmin());

        double scrollYOffset = vScrollProportion * Math.max(0, extraHeight);

        return scrollYOffset;
    }

    @Override
    public void repositionScroller(
        final double scaleFactor,
        final double scrollXOffset,
        final double scrollYOffset
    ) {
        double extraWidth = this.content.getLayoutBounds().getWidth()
            - this.pane.getViewportBounds().getWidth();

        if (extraWidth > 0) {
            double halfWidth = this.pane.getViewportBounds().getWidth() / 2;

            double newScrollXOffset = (scaleFactor - 1)
                *  halfWidth + scaleFactor * scrollXOffset;

            this.pane.setHvalue(
                this.pane.getHmin() + newScrollXOffset
                    * (this.pane.getHmax() - this.pane.getHmin()) / extraWidth
            );
        } else {
            this.pane.setHvalue(this.pane.getHmin());
        }

        double extraHeight = this.content.getLayoutBounds().getHeight()
            - this.pane.getViewportBounds().getHeight();

        if (extraHeight > 0) {
            double halfHeight = this.pane.getViewportBounds().getHeight() / 2;

            double newScrollYOffset = (scaleFactor - 1)
                * halfHeight + scaleFactor * scrollYOffset;

            this.pane.setVvalue(
                this.pane.getVmin() + newScrollYOffset
                    * (this.pane.getVmax() - this.pane.getVmin()) / extraHeight
            );
        } else {
            this.pane.setHvalue(this.pane.getHmin());
        }
    }

    @Override
    public void horizontalMove(final double deltaX) {
        double extraWidth = this.content.getLayoutBounds().getWidth()
            - this.pane.getViewportBounds().getWidth();

        double deltaH = deltaX * (this.pane.getHmax() - this.pane.getHmin()) / extraWidth;

        double desiredH = this.pane.getHvalue() - deltaH;

        this.pane.setHvalue(Math.max(0, Math.min(this.pane.getHmax(), desiredH)));
    }

    @Override
    public void verticalMove(final double deltaY) {
        double extraHeight = this.content.getLayoutBounds().getHeight()
            - this.pane.getViewportBounds().getHeight();

        double deltaV = deltaY * (this.pane.getHmax() - this.pane.getHmin()) / extraHeight;

        double desiredV = this.pane.getVvalue() - deltaV;

        this.pane.setVvalue(Math.max(0, Math.min(this.pane.getVmax(), desiredV)));
    }

    @Override
    public void onChange() {
        super.onChange();
        this.image.setImage(this.pictures.get(this.index()).image());
    }

    private void initGraphics() {
        this.content.setCache(true);
        this.content.setCacheHint(CacheHint.SPEED);
        this.image.setCache(true);
        this.image.setCacheHint(CacheHint.SPEED);
        this.image.setPreserveRatio(true);
        this.image.setSmooth(true);

        this.frame.setView(this.image);
        this.content.getChildren().add(this.frame);
        this.pane.setContent(this.content);
    }

    private void registerListeners() {
        this.frame.setOnScroll(this.scrolledAction);
        this.pane.viewportBoundsProperty().addListener(this.boundsAction);
        this.pane.setOnMouseClicked(this.mouseClickedAction);
        this.content.setOnMousePressed(mousePressedAction);
        this.content.setOnMouseDragged(mouseDraggedAction);
    }

    private void jump(final PictureImage pic) {
        boolean isFound = false;
        for (int i=0; i <= this.lastIdx; i++) {
            if (pic.name().equals(this.pictures.get(i).name())) {
                this.index = i;
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw new IllegalArgumentException("The given picture is not part of this pagination!");
        }
    }
}
