package com.web.ui.automation.configurations;

import java.util.Properties;

public class MailConfiguration {

    public static Properties emailProperties = new Properties();

    static{
    	loadEmailProperties();
    }

    private static void loadEmailProperties() {
        try {
            if(emailProperties.isEmpty()){
                emailProperties.load(MailConfiguration.class.getResourceAsStream("/config/email.properties"));
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public static String getSmtpHost(){
        return emailProperties.getProperty("mail.smtp.host");
    }

    public static int getSmtpPort(){
        return Integer.parseInt(emailProperties.getProperty("mail.smtp.port"));
    }

    public static boolean getSmtpStartTlsEnable(){
        return Boolean.parseBoolean(emailProperties.getProperty("mail.smtp.starttls.enable"));
    }

    public static String getSmtpFrom(){
        return emailProperties.getProperty("mail.smtp.from");
    }

    public static String getSmtpUser(){
        return emailProperties.getProperty("mail.smtp.user");
    }

    public static String getSmtpPassword(){
        return emailProperties.getProperty("mail.smtp.password");
    }

    public static String getMailTo(){
        return emailProperties.getProperty("mail.to");
    }

    public static String getMailCC(){
        return emailProperties.getProperty("mail.cc");
    }
    
    public static boolean isSummaryMailEnabled(){
        try {
            return Boolean.parseBoolean(emailProperties.getProperty("summary.mail.enable"));
        } catch (Exception e) {
            return false;
        }
    }

}
