package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.options.TypeableOption;
import org.escaperun.game.serialization.SaveManager;
import org.escaperun.game.view.Decal;

public class Saving extends GameState {

    private Playing prev;
    //
    private OptionContainer optionContainer;

    public Saving(Playing prev) {
        this.prev = prev;
        final Playing pr = prev;
        Option[][] options = new Option[][]{
                {new TypeableOption("Profile Name:", 15) {
                    @Override
                    public GameState doSomething() {
                        try {
                            SaveManager.save(System.getProperty("user.dir") + "/profiles/" + toString() + ".xml", pr.stage);
                        } catch (Exception e) {
                        }
                        return pr;
                    }
                }}
        };
        optionContainer = new OptionContainer(options, OptionContainer.ContainerType.CENTERED);
    }

    @Override
    public GameState update(KeyBindings bindings, boolean[] pressed) {
        return optionContainer.update(bindings, pressed);
    }

    @Override
    public Decal[][] getRenderable() {
        return optionContainer.getRenderable();
    }
}
