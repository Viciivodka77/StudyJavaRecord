package StudyDay22.ZXingTest;

import com.google.zxing.WriterException;

import java.io.IOException;

public class ZXingTest {
    public static void main(String[] args) throws IOException, WriterException {
        String imgPath = "src/二维码2.gif";
        String content = "妮宝辛苦！加油！";

        //加密
        ZXingUtil test = new ZXingUtil();
        test.Encode(content,imgPath,"gif",430,430);
        //解密

    }
}
