package com.Proiect.Proiect.controllers;

import com.Proiect.Proiect.entities.User;
import com.Proiect.Proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                               @RequestParam String password,
                               Model model) {

        User existentUser = userRepository.findByEmail(email);

        if(existentUser == null)
        {
            model.addAttribute("error", "Email gresit!");
            return "login";
        }

        if(!password.equals(existentUser.getPassword()))
        {
            model.addAttribute("error", "Parola gresita!");
            return "login";
        }
        model.addAttribute("loggedInUser", existentUser);
        return "home";
    }

    @GetMapping("/")
    public String showHomePage() { return "home"; }

    @PostMapping("/register")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String password,
                               Model model) {

        User existentUser = userRepository.findByEmail(email);

        if(existentUser != null)
        {
            model.addAttribute("error", "Email deja existent!");
            return "register";
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        String regex = "^([^@]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        String nameId = "";
        if (matcher.find()) {
            nameId = matcher.group(1);
        }
        user.setNameId(nameId);
        userRepository.save(user);
        model.addAttribute("successMessage", "Te-ai inregistrat cu succes!");
        return "register";
    }
    @PostMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("loggedInUser", null);
        return "home";
    }
}
