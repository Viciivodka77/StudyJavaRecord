package StudyDay24.EmailTest;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

public class JavaMailDemo {
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
            transport.connect("1772560342@qq.com","ldwclvuenjmjjhab");//打开链接 "密码" -> 授权密码
            transport.sendMessage(message,message.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    //MimeMessage：邮件
    private static MimeMessage createMimeMessage(Session session,String sender,String receiver1,String receiver2,String receiver3) {
        MimeMessage message = new MimeMessage(session);
        //邮件：标题、正文、收件人、发件人    //复杂：附件、图片
        try {
            //发件人地址
            Address address = new InternetAddress(sender,"晖哥的小弟","UTF-8");
            message.setFrom(address);//设置发件人
            message.setSubject("晖哥万岁~", "UTF-8");
            message.setContent("晖哥冲鸭~冲鸭~冲鸭~木啊（づ￣3￣）づ╭❤～","text/html;charset=utf-8");

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
