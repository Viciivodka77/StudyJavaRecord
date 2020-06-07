package StudyDay22.QRcodeTest;

import java.io.IOException;

public class Demo01 {



    public static void main(String[] args) throws IOException {
        /*
            生成二维码
            生成图片的路径 src/二维码.png
            文字信息、网址信息："helloworld "
         */
        String imgPath = "src/二维码.png";
        String content = "hui,i love u three thousand";
//        String content = "https://www.baidu.com/"; //网页跳转
        /*
            加密：文字信息- >二维码
            解密：二维码 -> 文字信息
         */
        //加密
        QRCodeUtil util = new QRCodeUtil();
        util.encoderQRCode(content,imgPath,"png",6);
        //解密
        String decoder = util.decoderQRCode(imgPath);
        System.out.println(decoder);
    }
}
