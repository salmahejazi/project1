package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialForm {

    private Integer credentialId;
    private String credentialUrl;
    private String username;
    private String password;

    public Integer getCredentialId() {
        return credentialId;
    }
    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public String getCredentialUrl() {
        return credentialUrl;
    }
    public void setCredentialUrl(String credentialUrl) {
        this.credentialUrl = credentialUrl;
    }

    public String getUrl() {
        return credentialUrl;
    }
    public void setUrl(String credentialUrl) {
        this.credentialUrl = credentialUrl;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
