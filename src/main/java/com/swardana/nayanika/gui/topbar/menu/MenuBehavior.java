package com.swardana.nayanika.gui.topbar.menu;

import java.util.logging.Logger;

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

    /**
     * Creates new MenuBehavior.
     *
     * @param view the menu view counterpart.
     */
    MenuBehavior(final MenuView view) {
        this.view = view;
    }

    /**
     * Called by view.
     * <p>
     *     Call this operation when starting
     *     the slide-show operation.
     * </p>
     */
    final void onStartSlideShow() {
        this.view().showStopSlideMenu();
    }

    /**
     * Called by view.
     * <p>
     *     Call this operation when stoping
     *     the slide-show operation.
     * </p>
     */
    final void onStopSlideShow() {
        this.view().showStartSlideMenu();
    }

    @Override
    public final MenuView view() {
        return this.view;
    }

}
