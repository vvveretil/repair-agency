package com.university.repairagency.registration;

import com.university.repairagency.config.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return Page.REGISTRATION;
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute @Valid RegistrationRequest registrationRequest, BindingResult bindingResult) {
        System.out.println(registrationRequest);
        if (bindingResult.hasErrors()) {
            return Page.REGISTRATION;
        }

        try {
            registrationService.register(registrationRequest);
        } catch (Exception exception) {
            bindingResult.addError(new FieldError("registrationRequest", "username", "User already exists!"));
            return Page.REGISTRATION;
        }

        return "redirect:/" + Page.LOGIN;
    }
}
