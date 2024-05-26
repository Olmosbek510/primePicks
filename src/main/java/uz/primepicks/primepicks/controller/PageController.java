package uz.primepicks.primepicks.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uz.primepicks.primepicks.entity.User;
import uz.primepicks.primepicks.entity.enums.RoleName;
import uz.primepicks.primepicks.repo.RoleRepo;
import uz.primepicks.primepicks.repo.UserRepo;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class PageController {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    @GetMapping("/")
    public String home(Principal principal){
        String name = principal.getName();
        User user = userRepo.findByUsername(name);
        if(user.getAuthorities().contains(roleRepo.findByName(RoleName.ROLE_ADMIN))){
            return "admin/adminMain";
        }if(user.getAuthorities().contains(roleRepo.findByName(RoleName.ROLE_USER))){
            return "redirect:/user";
        }if(user.getAuthorities().contains(roleRepo.findByName(RoleName.ROLE_SALES_MANAGER))){
            return "sales/manager";
        }
        return "/auth/main";
    }
}
