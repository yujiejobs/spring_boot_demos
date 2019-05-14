package com.san.controller;

import com.san.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.controller
 * @className MailController
 * @description
 * @date 2019/05/13 20:27
 */
@RestController
public class MailController {

    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine emplateEngine;

    /**
     * 发送普通邮件
     */
    @RequestMapping("/sendSimpleMail")
    public void sendSimpleMail() {
        mailService.sendSimpleMail("2211024378@qq.com", "test simple mail", " hello this is simple mail");
    }

    /**
     * 发送 html 格式邮件
     */
    @RequestMapping("/sendHtmlMail")
    public void sendHtmlMail() {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("2211024378@qq.com", "test simple mail", content);
    }


    /**
     * 发送带附件的邮件
     */
    @RequestMapping("/sendAttachmentsMail")
    public void sendAttachmentsMail() {
        String filePath = "D:\\1234.txt";
        mailService.sendAttachmentsMail("2211024378@qq.com", "主题：带附件的邮件", "有附件，请查收", filePath);
    }

    /**
     * 发送带静态资源的邮件
     */
    @RequestMapping("/sendInlineResourceMail")
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "D:\\1234.jpg";

        mailService.sendInlineResourceMail("2211024378@qq.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    /**
     * 解析模板并发送
     */
    @RequestMapping("/sendTemplateMail")
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = emplateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("2211024378@qq.com","主题：这是模板邮件",emailContent);
    }





}
