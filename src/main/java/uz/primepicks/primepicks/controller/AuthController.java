package uz.primepicks.primepicks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.primepicks.primepicks.entity.Role;
import uz.primepicks.primepicks.entity.User;
import uz.primepicks.primepicks.entity.enums.RoleName;
import uz.primepicks.primepicks.repo.RoleRepo;
import uz.primepicks.primepicks.repo.UserRepo;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/register")
    public String register(){
        return "auth/register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        Role role = roleRepo.findByName(RoleName.ROLE_SALES_MANAGER);
        user.setRoles(List.of(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("New User registration: " + user);
        userRepo.save(user);
        return "redirect:/";
    }
    @PreAuthorize("!hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_SALES_MANAGER')")
    @GetMapping("/login")
    public String login(){
        return "/login";
    }
}
