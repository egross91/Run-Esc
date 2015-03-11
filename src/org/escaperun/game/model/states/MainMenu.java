package org.escaperun.game.model.states;

import org.escaperun.game.controller.keyboard.KeyBindings;
import org.escaperun.game.model.options.SelectableOption;
import org.escaperun.game.model.options.TypeableOption;
import org.escaperun.game.model.states.GameState;
import org.escaperun.game.model.options.Option;
import org.escaperun.game.model.options.OptionContainer;
import org.escaperun.game.model.states.Exit;
import org.escaperun.game.view.Decal;

public class MainMenu extends GameState {

    private OptionContainer optionContainer;

    public MainMenu() {
        Option[][] options = new Option[][]{
                {new SelectableOption("New Game") {
            @Override
            public GameState getNextState() {
                return new CreationMenu();
            }
        },new SelectableOption("Exit1") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit2") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit3") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit4") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit5") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit6") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit7") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        }},
                {new SelectableOption("New Game") {
                    @Override
                    public GameState getNextState() {
                        return new CreationMenu();
                    }
                },new SelectableOption("Exit1") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit2") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit3") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit4") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit5") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit6") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit7") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                }},
                {new SelectableOption("New Game") {
                    @Override
                    public GameState getNextState() {
                        return new CreationMenu();
                    }
                },new SelectableOption("Exit1") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit2") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit3") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit4") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit5") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit6") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit7") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                }},
                {new SelectableOption("New Game") {
            @Override
            public GameState getNextState() {
                return new CreationMenu();
            }
        },new SelectableOption("Exit1") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit2") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit3") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit4") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit5") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit6") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        },new SelectableOption("Exit7") {
            @Override
            public GameState getNextState() {
                return new Exit();
            }
        }},
                {new SelectableOption("New Game") {
                    @Override
                    public GameState getNextState() {
                        return new CreationMenu();
                    }
                },new SelectableOption("Exit1") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit2") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit3") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit4") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit5") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit6") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit7") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                }},
                {new SelectableOption("New Game") {
                    @Override
                    public GameState getNextState() {
                        return new CreationMenu();
                    }
                },new SelectableOption("Exit1") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit2") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit3") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit4") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit5") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit6") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit7") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                }},
                {new SelectableOption("New Game") {
                    @Override
                    public GameState getNextState() {
                        return new CreationMenu();
                    }
                },new SelectableOption("Exit1") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit2") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit3") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit4") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit5") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit6") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit7") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                }},
                {new SelectableOption("New Game") {
                    @Override
                    public GameState getNextState() {
                        return new CreationMenu();
                    }
                },new SelectableOption("Exit1") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit2") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit3") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit4") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit5") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit6") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
                    }
                },new SelectableOption("Exit7") {
                    @Override
                    public GameState getNextState() {
                        return new Exit();
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
