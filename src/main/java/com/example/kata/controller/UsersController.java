package com.example.kata.controller;

import com.example.kata.model.User;
import com.example.kata.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping("/users")
    public ModelAndView showAllUsers(Model userModel) {
        userModel.addAttribute("allUsers", userService.getAllUsers());
        return new ModelAndView("users");
    }


    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model userModel) {
        userModel.addAttribute("man", userService.getUser(id));
        return "user";
    }


    @GetMapping("/new")
    public String newUserPage(@ModelAttribute("userBoy") User user) {
        return "new";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editeUserPage(@PathVariable Long id, Model userModel) {
        userModel.addAttribute("userUpdate", userService.getUser(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user) {
        System.out.println("UPDATE METHOD CALL");
        userService.updateUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        System.out.println("DELETE METHOD CALL");
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
