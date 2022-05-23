package unannn.inside.web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import unannn.inside.config.auth.PrincipalDetails;
import unannn.inside.web.dto.LoginDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(principalDetails == null){
            return "redirect:/login";
        }
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String loginForm(Model model, HttpServletRequest request) {
        model.addAttribute("loginDto", new LoginDto());
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginDto loginDto, BindingResult bindingResult, HttpServletRequest request, Model model) {
        model.addAttribute("loginDto", loginDto);
        if(!loginDto.getUsername().isEmpty() && !loginDto.getPassword().isEmpty()){
            bindingResult.reject("InvalidAccount");
        }
        return "loginForm";
    }

}
