package com.tenacity.free.common.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @project_name: tenacity-free-common
 * @package_name: com.tenacity.free.common.email
 * @file_name: MailAuthenticator.java
 * @author: free.zhang
 * @datetime: 2018年1月13日 下午8:06:50
 * @desc: TODO
 */
public class MailAuthenticator extends Authenticator {

    String userName = null;
    String password = null;

    public MailAuthenticator() {
    }

    public MailAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }

}
