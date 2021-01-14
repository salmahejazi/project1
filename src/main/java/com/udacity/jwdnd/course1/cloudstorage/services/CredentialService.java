package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
@Service
public class CredentialService {

    private  CredentialsMapper CredentialsMapper;
    private  EncryptionService EncryptionService;

    public CredentialService(CredentialsMapper CredentialsMapper) {
        this.CredentialsMapper = CredentialsMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating CredentialService bean");
    }

    public Credentials getCredential(Integer credentialId) {
        return CredentialsMapper.findCredential(credentialId);
    }


    public void addCredential(CredentialForm credentialForm) {

        SecureRandom random = new SecureRandom();
        byte salt [] = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        Credentials credentials = CredentialsMapper.findCredential(credentialForm.getCredentialId());
        if(credentials != null) {
            credentials.setKey(encodedSalt);
            credentials.setUrl(credentialForm.getCredentialUrl());
            credentials.setUsername(credentialForm.getUsername());
            String encryptedPassword = EncryptionService.encryptValue(credentials.getPassword(),encodedSalt);
            credentials.setPassword(encryptedPassword);
            //credentials.setPassword(credentialForm.getPassword());
            CredentialsMapper.updateCredential(credentials);
        } else {
            credentials = new Credentials();
            credentials.setKey(encodedSalt);
            credentials.setUrl(credentialForm.getUrl());
            credentials.setUsername(credentialForm.getUsername());
            String encryptedPassword = EncryptionService.encryptValue(credentials.getPassword(),encodedSalt);
            credentials.setPassword(encryptedPassword);
            //credentials.setPassword(credentialForm.getPassword());
            CredentialsMapper.addCredential(credentials);
        }
    }

    public List<Credentials> getAllCredentials(Integer userId){ return CredentialsMapper.getAllCredentials(userId); }

    public void deleteCredential(Integer credentialId){  CredentialsMapper.deleteCredential(credentialId);}

    public void updateCredential(Credentials Credentials){
        Credentials  Credential = new Credentials();

        String encodedSalt = CredentialsMapper.findKey(Credential.getCredentialId());
        String encryptedPassword = EncryptionService.encryptValue(Credential.getPassword(),encodedSalt);
        Credential.setPassword(encryptedPassword);

          CredentialsMapper.updateCredential(Credential);
    }
}
