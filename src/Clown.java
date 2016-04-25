import javafx.scene.image.Image;

/**
 * Created by Austin on 4/19/2016.
 */
public class Clown extends Entity implements Cloneable{

    Clown(int x, int y, double aP, double h, double hR, double e) {
        super("Clown", new Image("imgs/sideshowBob.png"), x, y, aP, h, hR, e, 1);
    }
}
