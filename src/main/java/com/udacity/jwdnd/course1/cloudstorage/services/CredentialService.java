package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.DecryptedCredential;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CredentialService {
/*
    private CredentialMapper CredentialMapper;
    private  EncryptionService EncryptionService;

    public CredentialService(CredentialMapper CredentialMapper) {
        this.CredentialMapper = CredentialMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating CredentialService bean");
    }

    public Credential getCredential(Integer credentialId) {
        return CredentialMapper.findCredential(credentialId);
    }


    public void addCredential(CredentialForm credentialForm) {

        SecureRandom random = new SecureRandom();
        byte salt [] = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        Credential credential = CredentialMapper.findCredential(credentialForm.getCredentialId());
        if(credential != null) {
            credential.setKey(encodedSalt);
            credential.setUrl(credentialForm.getCredentialUrl());
            credential.setUsername(credentialForm.getUsername());
            String encryptedPassword = EncryptionService.encryptValue(credential.getPassword(),encodedSalt);
            credential.setPassword(encryptedPassword);
            //credentials.setPassword(credentialForm.getPassword());
            CredentialMapper.updateCredential(credential);
        } else {
            credential = new Credential();
            credential.setKey(encodedSalt);
            credential.setUrl(credentialForm.getUrl());
            credential.setUsername(credentialForm.getUsername());
            String encryptedPassword = EncryptionService.encryptValue(credential.getPassword(),encodedSalt);
            credential.setPassword(encryptedPassword);
            //credentials.setPassword(credentialForm.getPassword());
            CredentialMapper.addCredential(credential);
        }
    }

    public List<Credential> getAllCredentials(Integer userId){ return CredentialMapper.getAllCredentials(userId); }

    public void deleteCredential(Integer credentialId){  CredentialMapper.deleteCredential(credentialId);}

    public void updateCredential(Credential Credentials){
        Credential Credential = new Credential();

        String encodedSalt = CredentialMapper.findKey(Credential.getCredentialId());
        String encryptedPassword = EncryptionService.encryptValue(Credential.getPassword(),encodedSalt);
        Credential.setPassword(encryptedPassword);

          CredentialMapper.updateCredential(Credential);
    }*/

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void encrypt(Credential credential) {
        String sKey = encryptionService.getRandomKey();
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), sKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(sKey);
    }

    public String decryptPassword(Credential credential) {
        return encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }

    public void addCredential(Credential credential) {
        encrypt(credential);
        Integer id = credential.getCredentialId();
        if (id == null) {
            credentialMapper.addCredential(credential);
        } else {
            credentialMapper.updateCredential(credential);
        }
    }

    public void deleteCredential(Integer id) {
        credentialMapper.deleteCredential(id);
    }

    public List<DecryptedCredential> getCredentialByUserId(Integer userId) {
        List<Credential> credentialList = credentialMapper.getAllCredentials(userId);
        ArrayList<DecryptedCredential> decryptedCredentials = new ArrayList<>();
        for (Credential credential : credentialList) {
            decryptedCredentials.add(getDecryptedCredential(credential));
        }
        return decryptedCredentials;
    }

    public DecryptedCredential getDecryptedCredential(Credential credential) {
        String decryptedPassword = decryptPassword(credential);
        return new DecryptedCredential(
                credential.getCredentialId(), credential.getUrl(), credential.getUsername(), credential.getKey(), credential.getPassword(), decryptedPassword, credential.getUserId());
    }
}


