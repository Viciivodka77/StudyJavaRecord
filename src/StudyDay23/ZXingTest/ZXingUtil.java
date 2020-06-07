package StudyDay23.ZXingTest;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import jp.sourceforge.qrcode.util.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramSocket;
import java.util.HashMap;
import java.util.Map;

public class    ZXingUtil {
    //加密
    public void Encode(String content,String imgPath,String imgFormat,int height,int width,String logoPath) throws IOException, WriterException {
        /*
            content:需要加密的内容
            BarcodeFormat.QR_CODE：需要解析的类型（二维码）
            hints:加密涉及的一些参数：编码、排错率
         */
        Map<EncodeHintType,Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, CharacterSetECI.UTF8);//设置编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//设置容错率 L<M<Q<H
        hints.put(EncodeHintType.MARGIN,1);//外边距
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content,BarcodeFormat.QR_CODE,width,height,hints);
        BufferedImage bufImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB) ;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                boolean b = bitMatrix.get(i, j);
                //jp.sourceforge.qrcode.util;        Color.BLACK=0 Color.WHITE = 16777215
                bufImg.setRGB(i,j,(b?Color.BLACK:Color.WHITE));
            }
        }

        //画logo
        LogoUtil logo = new LogoUtil();
        BufferedImage logoBufImg = logo.createLogo(bufImg, logoPath);
        ImageIO.write(logoBufImg, imgFormat, new FileOutputStream(new File(imgPath)));// format :照片格式
    }

    //解密 二维码 -> 文字
    public String Decode(File file) throws IOException, NotFoundException {
        if (!file.exists()){
            return null;
        }
        BufferedImage bufImg = ImageIO.read(file);
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufImg);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        Map<DecodeHintType,Object> map = new HashMap<>();
        map.put(DecodeHintType.CHARACTER_SET,CharacterSetECI.UTF8);
        Result decode = new MultiFormatReader().decode(bitmap,map);
        System.out.println(decode.toString());
        return decode.toString();
    }

}