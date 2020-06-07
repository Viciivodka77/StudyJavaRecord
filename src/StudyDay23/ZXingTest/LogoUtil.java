package StudyDay23.ZXingTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LogoUtil {
    //传入logo、二维码  ->带logo的二维码
    public BufferedImage createLogo(BufferedImage bufImg,String logo) throws IOException {
        //在二维码上画logo：产生一个二维码画板
        Graphics2D graphics = bufImg.createGraphics();
        //画logo:String -> BufferedImage
        BufferedImage read = ImageIO.read(new File(logo));
        int width = bufImg.getWidth();
        int height = bufImg.getHeight();
        graphics.drawImage(read.getScaledInstance(width/5,height/5,Image.SCALE_SMOOTH),width*5/12,height*5/12,null);
        //产生一个白色圆角正方形的画笔
        BasicStroke strokeWhite = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        //将画板和画笔关联起来
        graphics.setStroke(strokeWhite);
        //创建一个正方形
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(width/3+33,height/3+33,width*3/12-20,height*3/12-20,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        graphics.setColor(Color.white);
        graphics.draw(round);
        //灰色的
        BasicStroke strokeGray = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        //将画板和画笔关联起来
        graphics.setStroke(strokeGray);
        //创建一个正方形
        RoundRectangle2D.Float roundGray = new RoundRectangle2D.Float((width/3)+35,height/3+35,width*3/12-24,height*3/12-24,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        graphics.setColor(Color.LIGHT_GRAY);
        graphics.draw(roundGray);

        graphics.dispose();
        bufImg.flush();

        return bufImg;
    }
}
