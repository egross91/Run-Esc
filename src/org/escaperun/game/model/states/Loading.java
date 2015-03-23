package org.escaperun.game.model.states;

import org.escaperun.game.controller.Sound;
import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.stage.Stage;
import org.escaperun.game.serialization.SaveManager;
import org.escaperun.game.view.Decal;

import java.io.File;

public class Loading extends GameState {

    private OptionContainer optionContainer;

    public Loading() {
        final String[] profiles = getProfiles();
        Option[][] options = new Option[profiles.length+1][1];
        for (int i = 0; i < profiles.length; i++) {
            final int it = i;
            options[i][0] = new SelectableOption(profiles[i]) {
                @Override
                public GameState getNextState() {
                    try {
                        Stage load = SaveManager.load(System.getProperty("user.dir") + "/profiles/" + profiles[it], new Stage());
                        if (load != null) {
                            Sound.PLAYING1.play();
                            return new Playing(load);
                        }
                    } catch (Exception ex) {
                    }
                    return null;
                }
            };
        }
        options[profiles.length][0] = new SelectableOption("Return") {

            @Override
            public GameState getNextState() {
                return new Main();
            }
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

    private static final File SAVE_FILE_DIRECTORY = new File(System.getProperty("user.dir") + "/profiles/");
    private static String[] getProfiles() {
        int cnt = 0;
        for (File profile : SAVE_FILE_DIRECTORY.listFiles()) {
            if (!profile.isDirectory() && profile.getAbsolutePath().contains("xml")) {
                cnt++;
            }
        }
        String[] retval = new String[cnt];
        cnt = 0;
        for (File profile : SAVE_FILE_DIRECTORY.listFiles()) {
            if (!profile.isDirectory() && profile.getAbsolutePath().contains("xml")) {
                retval[cnt++] = profile.getName();
            }
        }
        return retval;
    }
}
