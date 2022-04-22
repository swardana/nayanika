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

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;

/**
 * A slide-show.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Slide {

    /**
     * Playing the slide-show operation.
     * <p>
     *     This operation will call {@link PicturesNavigation#next()}
     *     with certain duration time.
     * </p>
     */
    void play();

    /**
     * Stop the slide-show operation.
     */
    void stop();

    /**
     * Update the current time duration with the new time.
     * <p>
     *     Updating the time duration will only applied when the
     *     slide is not running or applied after stop the running
     *     slide operation.
     * </p>
     *
     * @param duration the new time duration for slide transition.
     */
    void updateTime(double duration);

    /**
     * Update the current picture gallery navigation control.
     * <p>
     *     This operation is to update the current active picture gallery.
     *     Only require the navigation control. Updating the control
     *     will only applied when the slide is not running or applied
     *     after stop the running slide operation.
     * </p>
     *
     * @param nav the new {@link PicturesNavigation} control.
     */
    void updateControl(PicturesNavigation nav);

    /**
     * An observable state of slide-show running operation.
     *
     * @return {@code true} if the slide-show operation is running.
     */
    ReadOnlyBooleanProperty isRunningProperty();

    /**
     * A slide-show control.
     * <p>
     *     A slide-show operation using JavaFX {@link Timeline} API.
     * </p>
     *
     * @author Sukma Wardana
     * @version 1.0.0
     * @since 1.0.0
     */
    class Control implements Slide {

        private static final Logger LOG = Logger.getLogger(Control.class.getName());

        private static final double DEFAULT_DURATION = 9.0;

        private final BooleanProperty running;
        private final Timeline timeline;
        private double time;
        private PicturesNavigation pagination;

        /**
         * Creates new Slide::Control.
         *
         * @param duration the time duration of slide transition.
         * @param nav the picture gallery navigation.
         */
        public Control(final double duration, final PicturesNavigation nav) {
            this();
            this.time = duration;
            this.pagination = nav;
        }

        /**
         * Creates new Slide::Control.
         */
        public Control() {
            this.timeline = new Timeline();
            this.running = new SimpleBooleanProperty(this, "running", false);
            this.time = DEFAULT_DURATION;
        }

        @Override
        public final void play() {
            if (this.pagination == null) {
                LOG.log(
                    Level.WARNING,
                    "The PicturesNavigation is NULL, will not play the slide-show"
                );
                return;
            }
            var keyFrame = new KeyFrame(
                Duration.seconds(this.time),
                e -> {
                    this.pagination.next();
                }
            );
            this.timeline.getKeyFrames().addAll(keyFrame);
            this.timeline.setCycleCount(Timeline.INDEFINITE);
            this.timeline.play();
            this.running.set(true);
        }

        @Override
        public final void stop() {
            if (
                this.timeline.getStatus().equals(Timeline.Status.STOPPED)
                || this.timeline.getStatus().equals(Timeline.Status.PAUSED)
            ) {
                LOG.log(Level.WARNING, "The Slide is not running, do nothing!");
                return;
            }
            this.timeline.stop();
            this.timeline.getKeyFrames().clear();
            this.running.set(false);
        }

        @Override
        public final void updateTime(final double duration) {
            LOG.log(
                Level.FINE, "Update the time duration from {0}, to {1}",
                new Object[]{this.time, duration}
            );
            this.time = duration;
        }

        @Override
        public final void updateControl(final PicturesNavigation nav) {
            this.pagination = nav;
        }

        @Override
        public final ReadOnlyBooleanProperty isRunningProperty() {
            return this.running;
        }

    }

}
