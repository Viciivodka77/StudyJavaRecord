package StudyDay22.QRcodeTest;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

public class QRCodeImageImpl implements QRCodeImage {
    //内存中的二维码
    private BufferedImage bufferedImage;

    public QRCodeImageImpl(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    @Override
    public int getWidth() {
        return bufferedImage.getWidth();
    }

    @Override
    public int getHeight() {
        return bufferedImage.getHeight();
    }

    @Override
    public int getPixel(int i, int i1) {
        return bufferedImage.getRGB(i,i1);
    }
}
