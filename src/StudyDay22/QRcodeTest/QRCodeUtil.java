package StudyDay22.QRcodeTest;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.data.QRCodeImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class QRCodeUtil {
    //加密
    public void encoderQRCode(String content,String imgPath,String imgType,int size) throws IOException {
        File file = new File(imgPath);
        //内存中的一张图片

        BufferedImage image = qRCodeCommon(content,imgType,size);
        //API
        ImageIO.write(image,imgType,file);

    }

    /*
            content:二维码中隐藏的信息
            imgType:格式
            size:二维码边长
     */
    private BufferedImage qRCodeCommon(String content , String imgType, int size) throws IOException {
        int imgSize = 67 + 12*(size-1);
        BufferedImage bufImg = new BufferedImage(imgSize,imgSize,BufferedImage.TYPE_INT_RGB);//RGB
        //创建一个画板
        Graphics2D graphics = bufImg.createGraphics();
        graphics.setBackground(Color.white);//背景设为白色
        graphics.clearRect(0,0,imgSize,imgSize);//初始化
        graphics.setColor(Color.BLACK);//设置画板上的颜色（二维码的颜色）

        //QRCode对象：字符串->boolean[][]
        Qrcode qrHandler = new Qrcode();
        //设置二维码的排除率:7% L<M<Q<H 30%
        //排错率越高，可存储的信息越少，但是对二维码清晰的要求越小
        qrHandler.setQrcodeErrorCorrect('M');
        //可存放的信息类型:N:数字、A：数字+字母 B:所有
        qrHandler.setQrcodeEncodeMode('B');
        //尺寸：取值范围:1~40;
        // 数值越大，容纳的信息越多，但是注意适合就好，太大加上LOGO后容易这里的解析方法抛错误，但是其他的二维码软件可以识别
        qrHandler.setQrcodeVersion(size);

        //String -> byte[]
        byte[] contentBytes = content.getBytes(StandardCharsets.UTF_8);
        boolean[][] codeOut = qrHandler.calQrcode(contentBytes);
        int pixOff = 2;
        for (int i = 0; i < codeOut.length; i++) {
            for (int j = 0; j < codeOut.length; j++) {
                if (codeOut[i][j]){
                    graphics.fillRect(i*3 +pixOff,j*3 + pixOff,3,3);
                }
            }
        }
        //增加logo
        Image logo = ImageIO.read(new File("src/晖晖.png"));
        int maxHeight = bufImg.getHeight();
        int maxWidth = bufImg.getWidth();
        //在已经生成的二维码上画logo
//        graphics.drawImage(logo,imgSize*2/5,imgSize*2/5,maxWidth/8,maxHeight/8,null);
        graphics.drawImage(logo.getScaledInstance(imgSize/5,imgSize/5,Image.SCALE_SMOOTH),maxWidth*5/12,maxHeight*5/12,null);


        graphics.dispose();//释放空间
        bufImg.flush();//清理
        return bufImg;
    }


    //解密
    public String decoderQRCode(String imgPath) throws IOException {
        //硬盘中的imgPath图片 加载-->内存中
        BufferedImage bufImg = ImageIO.read(new File(imgPath));
        //生成解密对象
        QRCodeDecoder qrCodeDecoder = new QRCodeDecoder();
        QRCodeImageImpl qr = new QRCodeImageImpl(bufImg);
        byte[] decode = qrCodeDecoder.decode(qr);
        //byte[] -> String
        String res = new String(decode, StandardCharsets.UTF_8);
        return res;
    }

}

