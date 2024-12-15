package com.Proiect.Proiect.controllers;

import com.Proiect.Proiect.DTOS.UserDTO;
import com.Proiect.Proiect.entities.Articol;
import com.Proiect.Proiect.entities.User;
import com.Proiect.Proiect.repositories.ArticolRepository;
import com.Proiect.Proiect.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticolRepository articolRepository;

    @GetMapping("/bloggeri")
    public String showBloggeriPage(Model model) {

        List<User> useri = userRepository.findAll();
        List<UserDTO> bloggeri = new ArrayList<UserDTO>();
        useri.forEach( user -> {
            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getNameId());
            bloggeri.add(userDTO);
        });

        model.addAttribute("bloggeri", bloggeri);

        return "bloggeri";
    }

    @GetMapping("/bloggeri/{nameId}")
    public String showBloggerPage(@PathVariable String nameId,
                                   Model model) {

        User user = userRepository.findByNameId(nameId);
        List<Articol> articole = articolRepository.findAllByUserId(user.getId());
        model.addAttribute("articole", articole);

        return "articole-blogger";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/articolele-mele")
    public String showArticoleleMelePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email  = authentication.getName();
        User user = userRepository.findByEmail(email);
        List<Articol> articole = articolRepository.findAllByUserId(user.getId());
        model.addAttribute("articole", articole);
        return "articolele-mele";
    }


    @GetMapping("/adauga-articol")
    public String showAdaugaArticolPage() {
        return "adauga-articol";
    }

    @GetMapping("/modifica-articol/{id}")
    public String showModificaArticolPage(@PathVariable Long id, Model model) {
        Articol articol = articolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Articolul nu a fost gasit"));

        model.addAttribute("articol", articol);

        return "modifica-articol";
    }

    @PostMapping("/modifica-articol")
    public String modificaArticol(@RequestParam Long id, @RequestParam String titlu, @RequestParam String text,
                                  Model model) {
        Articol articol = articolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Articolul nu a fost gasit"));

        articol.setText(text);
        articol.setTitlu(titlu);
        articol.setData(new Date());
        articolRepository.save(articol);
        model.addAttribute("articol", articol);
        model.addAttribute("successMessage", "Articol modificat cu succes!");

        return "modifica-articol";
    }

    @PostMapping("/adauga-articol")
    public String adaugaArticol(@RequestParam String titlu,
                                @RequestParam String text,
                                Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email  = authentication.getName();
        User user = userRepository.findByEmail(email);

        Articol articol = new Articol();

        articol.setText(text);
        articol.setTitlu(titlu);
        articol.setUserId(user.getId());
        articol.setData(new Date());

        articolRepository.save(articol);
        model.addAttribute("successMessage", "Articol adaugat cu succes!");

        return "adauga-articol";
    }

    @PostMapping("/sterge-articol")
    public String stergeArticol(@RequestParam Long id,
                                Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email  = authentication.getName();
        User user = userRepository.findByEmail(email);

        articolRepository.deleteById(id);
        model.addAttribute("successMessage", "Sters articolul cu succes!");

        List<Articol> articole = articolRepository.findAllByUserId(user.getId());
        model.addAttribute("articole", articole);

        return "articolele-mele";
    }


    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }

    @GetMapping("/login-bootstrap")
    public String showLoginBootstrapPage() {
        return "login-bootstrap";
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
        user.setPassword("{noop}" + password);
        user.setRole("ROLE_USER");
        user.setStatus(true);
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
}
