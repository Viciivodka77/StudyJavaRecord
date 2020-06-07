package StudyDay23.ZXingTest;

import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;

public class ZXingTest {
    public static void main(String[] args) throws IOException, WriterException, NotFoundException {
        String imgPath = "src/二维码2.gif";
        String content = "妮宝辛苦！加油！";
        String logoPath = "src/晖晖.png";
        //加密
        ZXingUtil test = new ZXingUtil();
        test.Encode(content,imgPath,"gif",430,430,logoPath);
        //解密
        File file = new File(imgPath);
        test.Decode(file);
    }
}
