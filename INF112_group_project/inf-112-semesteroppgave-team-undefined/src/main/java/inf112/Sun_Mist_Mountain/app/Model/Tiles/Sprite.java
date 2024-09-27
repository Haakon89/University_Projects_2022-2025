package inf112.Sun_Mist_Mountain.app.Model.Tiles;

public record Sprite(Base base, State state) {

    public enum Base {
        Dirt,
        Grass,
        Earth,
        Stone,
    }

    public enum State {
        Dry,
        Watered,
    }

}
