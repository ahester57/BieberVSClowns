import javafx.geometry.Insets;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Created by Austin on 4/19/2016.
 */
public class AttackListCell extends ListCell<String> {

    @Override
    protected void updateItem(String item, boolean empty){
        super.updateItem(item, empty);

        setText(item);
        CornerRadii end = new CornerRadii(15);


        if(item != null){

                if (item.contains("Microphone")) {
                    setTextFill(Color.BLACK);
                    setBackground(new Background(new BackgroundFill(Color.PINK, end, new Insets(5, 5, 5, 5))));
                } else if (item.contains("Call")) {
                    setTextFill(Color.WHITE);
                    setBackground(new Background(new BackgroundFill(Color.GREEN, end, new Insets(5, 5, 5, 5))));
                } else if (item.contains("Drink")) {
                    setTextFill(Color.WHITE);
                    setBackground(new Background(new BackgroundFill(Color.ORANGE, end, new Insets(5, 5, 5, 5))));
                }

        }

    }
}
