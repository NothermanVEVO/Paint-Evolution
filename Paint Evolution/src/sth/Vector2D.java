package src.sth;

public enum Vector2D{
    ZERO(new Vector2(0, 0)),
    ONE(new Vector2(1, 1)),
    UP(new Vector2(0, -1)),
    DOWN(new Vector2(0, 1)),
    LEFT(new Vector2(-1, 0)),
    RIGHT(new Vector2(1, 0));

    private final Vector2 vct;

    Vector2D(Vector2 vector2) {
        this.vct = vector2;
    }

    public Vector2 getVct() {
        return vct;
    }
}
