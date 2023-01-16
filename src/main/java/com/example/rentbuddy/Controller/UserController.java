package com.example.rentbuddy.Controller;

import com.example.rentbuddy.PersistenceLayer.IUserPersistence;
import com.example.rentbuddy.PersistenceLayer.UserPersistenceFactory;
import com.example.rentbuddy.Model.Email.EmailFactory;
import com.example.rentbuddy.Model.Email.IEmail;
import com.example.rentbuddy.Model.EncoderDecoder.EncoderDecoderFactory;
import com.example.rentbuddy.Model.EncoderDecoder.IEncoderDecoder;
import com.example.rentbuddy.Model.User.IUser;
import com.example.rentbuddy.Model.User.User;
import com.example.rentbuddy.Model.User.UserFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    private final String resetEmailSubject = "Password Reset";
    private final String resetEmailInitialMessage = "Link for password reset is given below: ";
    private final String invalidUserErrorMessage = "Invalid username or password.";
    private final String accountNotCreatedErrorMessage = "Account is not created.";
    private final String systemErrorMessage = "Please try again.";
    private final String userExistErrorMessage = "User already exist.";
    private final String passwordResetMessage = "Password Successfully reset.";

    @GetMapping("/")
    public ModelAndView home(HttpServletRequest request) {
        IUser user = UserFactory.instance().createUser();

        return new ModelAndView("login", "user", user);
    }

    @PostMapping(value = "/")
    public ModelAndView login(@ModelAttribute("user") User user, HttpSession session) {
        IUserPersistence userPersistence = UserPersistenceFactory.instance().createUserPersistence();

        IUser.UserLoginResult userLoginResult = user.loginUser(user.getUserId(), user.getPassword(), userPersistence);

        if (userLoginResult.equals(IUser.UserLoginResult.VALID_USER)) {
            session.setAttribute("userId", user.getUserId());
            if (user.getUserType().equals("tenant")) {
                return new ModelAndView("redirect:/tenantHomePage");
            } else if (user.getUserType().equals("landlord")) {
                return new ModelAndView("redirect:/landlordHomePage", "userID", user.getUserId());
            }
        } else {
            String errorMessage = this.getErrorMessage(userLoginResult);

            return new ModelAndView("redirect:/", "error", errorMessage);
        }
        return new ModelAndView("redirect:/");
    }

    private String getErrorMessage(User.UserLoginResult userLoginResult) {
        String errorMessage = "";
        if (userLoginResult.equals(User.UserLoginResult.INVALID_USER)) {
            errorMessage = invalidUserErrorMessage;
        } else if (userLoginResult.equals(User.UserLoginResult.USER_DOES_NOT_EXIST)) {
            errorMessage = accountNotCreatedErrorMessage;
        } else {
            errorMessage = systemErrorMessage;
        }

        return errorMessage;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }


    @GetMapping("/forgotPassword")
    public ModelAndView forgotPassword() {
        IUser user = UserFactory.instance().createUser();
        return new ModelAndView("forgotPassword", "user", user);
    }

    @PostMapping("/forgotPassword")
    public ModelAndView getUserIDForForgotPassword(@ModelAttribute("user") User user, HttpServletRequest request) {

        IUserPersistence userDatabase = UserPersistenceFactory.instance().createUserPersistence();
        IUser userDetails = user.load(user.getUserId(), userDatabase);

        if (userDetails == null) {
            return new ModelAndView("redirect:/forgotPassword", "error", systemErrorMessage);
        } else {
            String pageURL = String.valueOf(request.getRequestURL());
            String userId = user.getUserId();
            IEncoderDecoder encoderDecoder = EncoderDecoderFactory.instance().createEncoderDecoder();
            String resetURL = user.generateResetURL(pageURL, userId, encoderDecoder);

            IEmail email = EmailFactory.instance().createEmail();

            String emailTo = userId;
            String message = resetEmailInitialMessage + resetURL;
            IEmail.EmailSendResult sendEmailResult = email.sendEmail(emailTo, message, resetEmailSubject);

            if (sendEmailResult.equals(IEmail.EmailSendResult.SUCCESS)) {
                return new ModelAndView("redirect:/");
            } else {
                return new ModelAndView("redirect:/forgotPassword", "error", systemErrorMessage);
            }
        }
    }

    @GetMapping("/resetPassword/{userId}")
    public ModelAndView resetPassword(@PathVariable(value = "userId") String encryptedUserId) {

        IUser user = UserFactory.instance().createDecryptedUser(encryptedUserId);

        return new ModelAndView("resetPassword", "user", user);
    }

    @PostMapping("/resetPassword")
    public ModelAndView reset(@ModelAttribute("user") User user) {

        IUserPersistence userPersistence = UserPersistenceFactory.instance().createUserPersistence();
        IUserPersistence.StorageResult result = user.updatePassword(userPersistence);

        if (result.equals(IUserPersistence.StorageResult.SUCCESS)) {

            return new ModelAndView("redirect:/", "error", passwordResetMessage);
        } else if (result.equals(IUserPersistence.StorageResult.STORAGE_FAILURE)) {
            return new ModelAndView("redirect:/resetPassword", "error", systemErrorMessage);
        }
        return new ModelAndView("redirect:/resetPassword");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        IUser user = UserFactory.instance().createUser();
        return new ModelAndView("register", "user", user);
    }

    @PostMapping("/register")
    public ModelAndView registerRedirect(@ModelAttribute("user") User user) {
        IUserPersistence userPersistence = UserPersistenceFactory.instance().createUserPersistence();
        IUser.UserRegisterResult result = user.register(user.getUserId(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getContactNumber(), user.getUserType(), userPersistence);

        if (result.equals(User.UserRegisterResult.SUCCESS)) {
            return new ModelAndView("redirect:/");
        } else if (result.equals(IUser.UserRegisterResult.USER_EXIST)) {

            return new ModelAndView("redirect:/register", "error", userExistErrorMessage);
        } else if (result.equals(User.UserRegisterResult.STORAGE_FAILURE)) {
            return new ModelAndView("redirect:/register", "error", systemErrorMessage);
        }
        return new ModelAndView("redirect:/register");
    }


}


