package game.sounds;

public enum Sounds {

    EXPLOSION("./src/assets/sounds/Explosion.wav"),
    SHOT("./src/assets/sounds/shot.wav"),
    COLLISION("./src/assets/sounds/Collision.wav"),
    LASER("./src/assets/sounds/Laser.wav"),
    DIE("./src/assets/sounds/Die.wav"),
    ORE("./src/assets/sounds/Ore.wav");

    private String path;

    Sounds(String path){
        this.path = path;
    }

    public String getPath(){
        return this.path;
    }
}
