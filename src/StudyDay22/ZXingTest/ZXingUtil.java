package StudyDay22.ZXingTest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.decoder.Version;
import jp.sourceforge.qrcode.util.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ZXingUtil {
    //加密
    public void Encode(String content,String imgPath,String imgFormat,int height,int width) throws IOException, WriterException {
        /*
            content:需要加密的内容
            BarcodeFormat.QR_CODE：需要解析的类型（二维码）
            hints:加密涉及的一些参数：编码、排错率
         */
        Map<EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, CharacterSetECI.UTF8);//设置编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//设置容错率 L<M<Q<H
        hints.put(EncodeHintType.MARGIN,1);//外边距
//        hints.put(EncodeHintType.QR_VERSION, );
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);
        BufferedImage bufImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB) ;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boolean b = bitMatrix.get(i, j);
                //jp.sourceforge.qrcode.util;        Color.BLACK=0 Color.WHITE = 16777215
                bufImg.setRGB(i,j,(b?Color.BLACK:Color.WHITE));
            }
        }
        ImageIO.write(bufImg, imgFormat, new FileOutputStream(new File(imgPath)));// format :照片格式
    }
}
