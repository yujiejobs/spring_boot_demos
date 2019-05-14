package com.san.mail;

/**
 * @author yujie
 * @projectName spring_boot_init
 * @packageName com.san.mail
 * @className Mailervice
 * @description
 * @date 2019/05/14 11:51
 */
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);


    void sendHtmlMail(String to, String subject, String content);


    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);

}
