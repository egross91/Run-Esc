package org.escaperun.game.model.entities.statistics;

public class LivesLeft extends PrimaryStatistic<Integer> {
    private Life life;

    public LivesLeft(Life life){
        super(0);
        life.subscribe(this);
        this.life = life;
        setBase(5);
    }

    @Override
    public void notifyChange() {
        if (life.getCurrent() <= 0) {
            setBase(getBase() - 1);
        }
    }

    @Override
    public String getName() {
        return "LivesLeft";
    }
}
