import com.dropbox.core.v2.DbxClientV2;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Uploader extends Thread {
    private DbxClientV2 client;
    private BufferedImage image;
    private String fileName;

    //Задается имя для скриншота в формате даты и времени
    public Uploader(DbxClientV2 client, BufferedImage image) {
        this.client = client;
        this.image = image;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        fileName = now.format(formatter) + ".png";
    }

    @Override
    public void run() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", bos);
            ByteArrayInputStream bis =
                    new ByteArrayInputStream(bos.toByteArray());
            client.files().uploadBuilder("/" + fileName)
                    .uploadAndFinish(bis);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
