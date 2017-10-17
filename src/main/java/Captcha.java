import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Captcha {
    private static final String CAPTCHA_IMAGE_FORMAT = "jpeg";

    //--kapcha验证码。
    private Properties props = new Properties();
    private Producer kaptchaProducer = null;

    private void KcaptchaController() {
        ImageIO.setUseCache(false);

        //设置宽和高。
        this.props.put(Constants.KAPTCHA_IMAGE_WIDTH, "200");
        this.props.put(Constants.KAPTCHA_IMAGE_HEIGHT, "50");
        //kaptcha.border：是否显示边框。
        this.props.put(Constants.KAPTCHA_BORDER, "no");
        //kaptcha.textproducer.font.color：字体颜色
        this.props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "black");
        //kaptcha.textproducer.char.space：字符间距
        this.props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "5");
        //设置字体。
        this.props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "42");
        this.props.put(Constants.KAPTCHA_BACKGROUND_IMPL, "com.google.code.kaptcha.impl.DefaultBackground");
        this.props.put(Constants.KAPTCHA_BACKGROUND_CLR_FROM, "white");
        this.props.put(Constants.KAPTCHA_BACKGROUND_CLR_TO, "white");
        this.props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "Lucida Sans Typewriter, Arial");
        //this.props.put(Constants.KAPTCHA_NOISE_COLOR, "");
        //更多的属性设置可以在com.google.code.kaptcha.Constants类中找到。


        Config config1 = new Config(this.props);
        this.kaptchaProducer = config1.getProducerImpl();
    }

    public static void main(String[] args) {
        String out_path = args[0];
        int num = Integer.parseInt(args[1]);

        for (int i = 0; i < num; i++) {
            System.out.println(i);
            Captcha cp = new Captcha();
            cp.KcaptchaController();
            String capText = cp.kaptchaProducer.createText();
            BufferedImage bi = cp.kaptchaProducer.createImage(capText);
            File out = new File("./" + out_path + "/" + capText + "_num" + i + ".jpg");
            try {
                ImageIO.write(bi, CAPTCHA_IMAGE_FORMAT, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
