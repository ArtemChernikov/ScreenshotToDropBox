import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) throws AWTException {
        //Ввести токен DropBox для загрузки скриншотов вместо "***"
        String ACCESS_TOKEN = "***";
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        //Создается скриншот экрана и отправляется в DropBox каждые 5 секунд
        for(;;) {
            Robot robot = new Robot();
            BufferedImage image = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            Uploader uploader = new Uploader(client, image);
            uploader.start();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


