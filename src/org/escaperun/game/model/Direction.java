package org.escaperun.game.model;

public enum Direction {
    DEG_0,
    DEG_45,
    DEG_90,
    DEG_135,
    DEG_180,
    DEG_225,
    DEG_270,
    DEG_315;

    public double getValues(){
        switch(this){
            case DEG_0:
                return 0;
            case DEG_45:
                return 45;
            case DEG_90:
                return 90;
            case DEG_135:
                return 135;
            case DEG_180:
                return 180;
            case DEG_225:
                return 225;
            case DEG_270:
                return 270;
            case DEG_315:
                return 315;
        }
        return 0;
    }
}
