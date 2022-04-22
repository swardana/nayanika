package com.swardana.nayanika.gui.topbar.menu;

import java.util.logging.Logger;

import com.swardana.nayanika.base.Slide;
import com.swardana.nayanika.gui.Behavior;

/**
 * A behavior of {@link MenuView}.
 *
 * @author Sukma Wardana
 * @version 1.0.0
 * @since 1.0.0
 */
class MenuBehavior implements Behavior<MenuView> {

    private static final Logger LOG = Logger.getLogger(MenuBehavior.class.getName());

    private final MenuView view;
    private final Slide slide;

    /**
     * Creates new MenuBehavior.
     *
     * @param view the menu view counterpart.
     * @param slide the slide-show control state.
     */
    MenuBehavior(final MenuView view, final Slide slide) {
        this.view = view;
        this.slide = slide;

        this.registerListeners();
    }

    /**
     * Called by view.
     * <p>
     *     Call this operation when starting
     *     the slide-show operation.
     * </p>
     */
    final void onStartSlideShow() {
        this.slide.play();
    }

    /**
     * Called by view.
     * <p>
     *     Call this operation when stoping
     *     the slide-show operation.
     * </p>
     */
    final void onStopSlideShow() {
        this.slide.stop();
    }

    @Override
    public final MenuView view() {
        return this.view;
    }

    private void registerListeners() {
        this.slide.isRunningProperty().addListener(e -> {
            if (this.slide.isRunningProperty().getValue()) {
                this.view().showStopSlideMenu();
            } else {
                this.view().showStartSlideMenu();
            }
        });
    }

}
