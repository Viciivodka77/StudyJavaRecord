package StudyDay25;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

public class JavaMailWithAttachmentDemo {
    public static void main(String[] args) throws MessagingException {
        System.setProperty("javax.net.ssl.trustStore", "E:\\IDEAProject\\StudyJava\\jssecacerts");
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol","smtp");//使用协议
        properties.setProperty("mail.smtp.host","smtp.qq.com");//协议地址
        properties.setProperty("mail.smpt.port","465");//协议端口
        properties.setProperty("mail.smpt.auth","true");//需要授权
        //qq:ssl安全认证
        properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback","false");
        properties.setProperty("mail.smtp.socketFactory.port","465");

        Session session = Session.getInstance(properties);
        session.setDebug(true);//开启日志
        //创建邮件
        MimeMessage message = createMimeMessage(session,"1772560342@qq.com","2559639546@qq.com","keshizumi11@gmail.com","Viciivodka77@outlook.com");

        Transport transport = null;
        try {
            transport = session.getTransport();//建立链接对象
            transport.connect("1772560342@qq.com","");//打开链接 "密码" -> 授权密码
            transport.sendMessage(message,message.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    //MimeMessage：带图片/附件的邮件
    private static MimeMessage createMimeMessage(Session session, String sender, String receiver1, String receiver2, String receiver3) {
        MimeMessage message = new MimeMessage(session);
        //邮件：标题、正文、收件人、发件人    //复杂：附件、图片
        try {
            //发件人地址
            Address address = new InternetAddress(sender,"发件人","UTF-8");
            message.setFrom(address);//设置发件人
            message.setSubject("带附件/图片邮件测试", "UTF-8");

            //创建图片节点
            MimeBodyPart imagePart = new MimeBodyPart();
            DataHandler imageDataHandler = new DataHandler(new FileDataSource("src/晖晖.png"));
            imagePart.setDataHandler(imageDataHandler);
            imagePart.setContentID("myImg");

            //创建文本节点:目的为了加载图片节点
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent("带附件/图片邮件测试。。。。。。。。。。<img src = 'cid:myImg'/>","text/html;charset=utf-8");

            //将文本节点和图片节点 组装
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(imagePart);
            multipart.addBodyPart(textPart);
            multipart.setSubType("related");//关联关系

            //注意：正文中只能出现普通节点，不能出现复合节点
            //MimeMultipart -> MimeBodyPart
            MimeBodyPart text_img_part = new MimeBodyPart();
            text_img_part.setContent(multipart);

            //图片节点 关联 文本节点 -> 复合节点 ->普通节点(！！正文！！中只有普通节点)

            //附件
            MimeBodyPart attachment = new MimeBodyPart();
            DataHandler attachHandler = new DataHandler(new FileDataSource("src/二维码.png"));
            attachment.setDataHandler(attachHandler);
            //给附件设置文件名
            attachment.setFileName(MimeUtility.encodeText(attachHandler.getName()));

            //将刚才处理好的“文本+图片”节点 与附件设置成一个新的复合节点
            MimeMultipart mm = new MimeMultipart();
            mm.addBodyPart(text_img_part);
            mm.addBodyPart(attachment);
            mm.setSubType("mixed");//混合关系

            message.setContent(mm,"text/html;charset=utf-8");
            //收件人地址
            InternetAddress receiverA = new InternetAddress(receiver1, "收件人A的名字", "UTF-8");
            InternetAddress receiverB = new InternetAddress(receiver2, "抄送人B的名字", "UTF-8");
            InternetAddress receiverC = new InternetAddress(receiver3, "密送人C的名字", "UTF-8");
            //收件人类型：普通收件人、抄送、密送
            message.setRecipient(MimeMessage.RecipientType.TO,receiverA);//普通收件人
            message.setRecipient(MimeMessage.RecipientType.CC,receiverB);//抄送
            message.setRecipient(MimeMessage.RecipientType.BCC,receiverC);//密送

            message.setSentDate(new Date());//设置发送时间
            message.saveChanges();//保存邮件
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return message;
    }
}
