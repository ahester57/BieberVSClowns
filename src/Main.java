import com.sun.javafx.application.LauncherImpl;

/**
 * Created by Austin on 4/17/2016.
 */
public class Main {
    public static void main(String[] args) {
        LauncherImpl.launchApplication(MenuController.class, MyPreloader.class, args);
    }
}
