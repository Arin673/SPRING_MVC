package com.spring.mvc.controller;

import com.spring.mvc.dto.LoginDTO;
import com.spring.mvc.entity.User;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(HttpServletRequest request, Model model, String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        System.out.println("login request " + request.getMethod());
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            } else if (cookie.getName().equals("JSESSIONID")) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        session.removeAttribute("email");
        session.invalidate();
        return "redirect:/index.jsp";
    }

    @PostMapping("/login")
    public String loginPostPage(@ModelAttribute("loginDTO") LoginDTO dto, HttpSession session, HttpServletResponse response) {
        System.out.println("login request " + dto.getEmail() + " " + dto.getPassword());

        try {
            User user = userService.getUserByEmail(dto.getEmail());
            if (user != null && user.getPassword().equals(dto.getPassword())) {
                session.setAttribute("username", user.getUserName());
                Cookie cookie = new Cookie("username", user.getUserName());
                response.addCookie(cookie);
                return "redirect:/dashboard";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login?error=Invalid Credentials";
    }
}
