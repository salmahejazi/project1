package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CredentialController {
    private CredentialService credentialService;

    public CredentialController(CredentialService credentialService){
        this.credentialService = credentialService;
    }

    @ModelAttribute("CredentialForm")
    public CredentialForm getCredentialForm() {
        return new CredentialForm();
    }

    //     After the page has initially been loaded it should do be able to add notes to the page
    @PostMapping("/Credentials")
    public String postNewCredential(@ModelAttribute("CredentialForm") CredentialForm credentialForm, Model model) {
        this.credentialService.addCredential(credentialForm);
        return "redirect:/home";
    }

    @GetMapping("/Credentials/{credentialId}")
    public String deleteCredential(@PathVariable(value = "credentialId") Integer credentialId, Model model) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }

    @GetMapping(value = "/getCredential/{credentialId}")
        @ResponseBody
        public Credentials getCredential(@PathVariable(name="credentialId") String credentialID) {
            Integer credentialId = Integer.parseInt(credentialID);
            return credentialService.getCredential(credentialId);
    }
}
