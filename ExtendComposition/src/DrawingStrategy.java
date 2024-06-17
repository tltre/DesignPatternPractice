import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class DrawingStrategy {
    protected String leafIcon;
    protected String rootIcon;

    public void setIcon(String icon) {
        Properties config = new Properties();

        try (FileInputStream fis = new FileInputStream("icons.properties")) {
            config.load(fis);
            // 读取配置项
            switch (icon) {
                case "poker-face":
                    leafIcon = config.getProperty("poker-face.leaf");
                    rootIcon = config.getProperty("poker-face.root");
                    break;
                case "tri-circle":
                    leafIcon = config.getProperty("tri-circle.leaf");
                    rootIcon = config.getProperty("tri-circle.root");
                    break;
                default:
                    System.out.println("Invalid Icon Family!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    abstract void drawing(Iterator iterator, String icon);
}
