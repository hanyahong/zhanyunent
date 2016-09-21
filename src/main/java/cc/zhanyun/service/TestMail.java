package cc.zhanyun.service;

import cc.zhanyun.model.OfferSend;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class TestMail {
    @Autowired
    private JavaMailSender mailSender;

    public void sendddddAttachmentsMail(OfferSend offerSend) {
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);


            helper.setFrom("18500201726@sina.cn");
            helper.setTo("2783309477@qq.com");
            helper.setSubject("a");
            helper.setText("sdfgdsfgsdfg");


            this.mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


