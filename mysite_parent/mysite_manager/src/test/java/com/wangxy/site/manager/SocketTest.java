package com.wangxy.site.manager;

import java.io.File;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SocketTest {
	  @Autowired
	  private JavaMailSenderImpl mailSender;
	  private Logger logger = LoggerFactory.getLogger(this.getClass());
	 /**
     * 发送包含简单文本的邮件
     */
    @Test
    public void sendTxtMail() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人
        String receiver="177@qq.com";
        simpleMailMessage.setTo(new String[] {receiver});
        simpleMailMessage.setFrom("176@163.com");
        simpleMailMessage.setSubject("Spring Boot Mail 邮件测试【文本】");
        simpleMailMessage.setText("这里是一段简单文本测试邮件。！！！！！请忽略！！！！！");
        // 发送邮件
        mailSender.send(simpleMailMessage);
        logger.info("邮件已经发送到账户"+receiver);
        System.out.println("邮件已发送");
    }
    
    
    /**
     * 发送包含HTML文本的邮件
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        String receiver="177@qq.com";//接收者账户
        String postCount="176@163.com";//发送者账户
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setFrom("176@163.com");
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【HTML】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 发送邮件
        mailSender.send(mimeMessage);
        logger.info("邮件已经从"+postCount+"->发送到账户"+receiver);
        System.out.println("邮件已发送");
    }
    
    
    /**
     * 发送包含内嵌图片的邮件
     * @throws Exception
     */
    @Test
    public void sendAttachedImageMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        String receiver="177@qq.com";//接收者账户
        String postCount="176@163.com";//发送者账户
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setFrom(postCount);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【图片】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail test。</p>");
        // cid为固定写法，imageId指定一个标识
        sb.append("<img src='https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_272x92dp.png'></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);

        // 设置imageId
        FileSystemResource img = new FileSystemResource(new File("D:/壁纸/21.jpg"));
        mimeMessageHelper.addInline("imageId", img);

        // 发送邮件
        mailSender.send(mimeMessage);
        logger.info("邮件已经从"+postCount+"->发送到账户"+receiver+",发送内容为："+img);
        System.out.println("邮件已发送");
    }
    
    
    
    
    /**
     * 发送包含附件的邮件
     * @throws Exception
     */
    @Test
    public void sendAttendedFileMail() throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // multipart模式
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
        String receiver="177@qq.com";//接收者账户
        String postCount="176@163.com";//发送者账户
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setFrom(postCount);
        mimeMessageHelper.setSubject("Spring Boot Mail 邮件测试【附件】");

        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body><h1>spring 邮件测试</h1><p>hello!this is spring mail annex test</p></body>");
        sb.append("</html>");

        // 启用html
        mimeMessageHelper.setText(sb.toString(), true);
        // 设置附件
        FileSystemResource file = new FileSystemResource(new File("D:/简历/个人简历 朱旭.docx"));
        mimeMessageHelper.addAttachment("PersonalInformation.docx", file);

        // 发送邮件
        mailSender.send(mimeMessage);
        logger.info("邮件已经从"+postCount+"->发送到账户"+receiver+",发送内容为："+file);
        System.out.println("邮件已发送");
    }

}