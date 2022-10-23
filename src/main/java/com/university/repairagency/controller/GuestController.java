package com.university.repairagency.controller;

import com.university.repairagency.config.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuestController {

    @GetMapping("/")
    public String getMainPage() {
        return Page.MAIN;
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return Page.ABOUT;
    }

    @GetMapping("/contacts")
    public String getContactsPage() {
        return Page.ABOUT;
    }

}
