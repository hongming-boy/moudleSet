package com.ruoyi.moudleSet.utils;

import com.ruoyi.common.config.Global;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * 发送邮件工具类
 * @author 王泓明
 * @date 2019年9月4日
 */
public class SendEmailUtils extends Thread{

    /**
     * 详见application.yml配置文件
     */
    private static String hostName= Global.getConfig("mail.hostName");
    private static String senderMail= Global.getConfig("mail.senderMail");
    private static String senderName= Global.getConfig("mail.senderName");
    private static String authorization= Global.getConfig("mail.authorization");

    /** 发送地址 */
    private String addressee;
    /** 发送标题 */
    private String title;
    /** 发送正文 */
    private String content;

    /**
     * 构造器
     * @param addressee 发送地址
     * @param title     发送标题
     * @param content   发送正文
     */
    public SendEmailUtils(String addressee, String title, String content){
        this.addressee = addressee;
        this.content = content;
        this.title = title;
    }


    /**
     * 邮件发送
     * @author chujingang
     * @date 2019年9月4日11:06:55
     * @param addressee 收件人邮箱
     * @param title 邮件主题
     * @param content 邮件内容
     */
    public static void  sendEmail(String addressee,String title,String content){
        //  TODO 暂时弃用
    }

    /**
     * 邮件发送---多线程发送
     * @author chujingang
     * @date 2019年9月4日11:06:55
     */
    @Override
    public void run(){
        HtmlEmail email = new HtmlEmail();
        try {
            // 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"
            email.setHostName(hostName);
            // 字符编码集的设置
            email.setCharset("utf-8");
            // 收件人的邮箱
            email.addTo(addressee);
            // 发送人的邮箱2
            email.setFrom(senderMail, senderName);
            // 如果需要认证信息的话，设置认证：用户名-密码     ***是你开启POP3服务时的授权码，不是登录密码
            email.setAuthentication(senderMail, authorization);
            // 要发送的邮件主题
            email.setSubject(title);
            // 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签
            email.setMsg(content);
            // 发送
            email.send();
            System.out.println("发送成功");
        } catch (EmailException e) {
            e.printStackTrace();
            System.out.println("发送失败");
        }
    }
}
