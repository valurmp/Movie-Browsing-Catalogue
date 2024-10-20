package Controllers;

import com.team18.MBC.core.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    UserService userService;
    ImageService imageService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Existing Endpoints

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGET(User user) {
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupPOST(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "redirect:/signup";
        }
        User exists = userService.findByUsername(user.getUsername());
        if (exists == null) {
            userService.save(user);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET(User user) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            return "login";
        }
        User exists = userService.login(user);
        if (exists != null) {
            session.setAttribute("LoggedInUser", exists);
            model.addAttribute("LoggedInUser", exists);
            return "LoggedInUser";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/loggedin", method = RequestMethod.GET)
    public String loggedinGET(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if (sessionUser != null) {
            model.addAttribute("LoggedInUser", sessionUser);
            return "loggedInUser";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/{username}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("username") String username, Model model) {
        User userToDelete = userService.findByUsername(username);
        userService.delete(userToDelete);
        return "redirect:/";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserAPIById(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);

        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }
    @RequestMapping(value = "/users/{id}/profile", method = RequestMethod.GET)
    public String getUserById(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "userProfile";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/users/{ID}/update-password", method = RequestMethod.PATCH)
    public String updatePassword(
            @PathVariable("ID") Long ID,
            @ModelAttribute("passwordChangeRequest") PasswordChangeRequest passwordChangeRequest,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "updatePassword";
        }

        User user = userService.findUserById(ID);
        if (user != null) {
            userService.updatePassword(user, passwordChangeRequest.getNewPassword());
            return "redirect:/user/" + ID;
        }

        return "redirect:/";
    }



    @RequestMapping(value = "/users/{ID}/update-password", method = RequestMethod.GET)
    public String showUpdatePasswordPage(@PathVariable("ID") Long ID, Model model) {
        User user = userService.findUserById(ID);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("passwordChangeRequest", new PasswordChangeRequest());  // Add the DTO to the model
            return "updatePassword";
        }
        return "redirect:/";
    }


    @RequestMapping(value = "/users/profile", method = RequestMethod.GET)
    public String getLoggedInUserProfile(HttpSession session, Model model) {
        User sessionUser = (User) session.getAttribute("LoggedInUser");
        if (sessionUser != null) {
            model.addAttribute("user", sessionUser);
            return "userProfile";
        }
        return "redirect:/login";
    }
}
