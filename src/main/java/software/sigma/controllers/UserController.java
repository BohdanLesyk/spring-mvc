package software.sigma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import software.sigma.UserDAO;
import software.sigma.models.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDAO userDAO;

    @Autowired  // We may not write this annotation. Here just for example
    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userDAO.index());

        return "user/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.show(id));

        return "user/show";
    }

    @GetMapping("new-user")
    public String newPerson(Model model) {
        model.addAttribute("user", new User());

        return "user/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/new";
        }
        userDAO.save(user);

        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.show(id));

        return "user/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }

        userDAO.update(id, user);

        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);

        return "redirect:/users";
    }
}
