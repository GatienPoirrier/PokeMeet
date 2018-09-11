package fr.epf.pokemeet.controllers;

import fr.epf.pokemeet.models.User;
import fr.epf.pokemeet.persistence.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * TODO class details.
 *
 * @author Loïc Ortola on 10/09/2018
 */
@Controller
public class UsersController {

    private final UserDao userDao;

    public UsersController(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Ceci sera mappé sur l'URL '/users'.
     * C'est le routeur de Spring MVC qui va détecter et appeler directement cette méthode.
     * Il lui fournira un "modèle", auquel on pourra rajouter des attributs.
     * Ce modèle sera ensuite forwardé à une page web (dans resources/templates).
     * Le nom de la template est retourné par la fonction. Ici, elle appelle donc le template "users".
     *
     * @param model le modèle
     * @return
     */
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("data", userDao.findAll());
        return "users-list";
    }

/*    @GetMapping("/user")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add_member";
    }*/

    @PostMapping("/user")
    public String addUser(User user, Model model) {
        System.out.println(user);
        userDao.save(user);


        return "redirect:/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userDao.deleteById(id);
        return "redirect:/users";
    }



}
