package com.udacity.jwdnd.course1.cloudstorage.model;




public class DecryptedCredential {

    private String decryptPassword;
    private Integer CredentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private Integer userid;

    public DecryptedCredential(Integer credentialId, String url, String username, String key, String password, String decryptPassword, Integer userid) {
        this.CredentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.decryptPassword = decryptPassword;
        this.userid = userid;
    }





    public Integer getUserId() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCredentialId() {
        return CredentialId;
    }

    public void setCredentialId(Integer CredentialId) {
        this.CredentialId = CredentialId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getdecryptPassword() {
        return decryptPassword;
    }

    public void setdecryptPassword(String decryptPassword) {
        this.decryptPassword = decryptPassword;
    }

}
