package whz.pti.eva.praktikum_03.common;

import org.springframework.ui.Model;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;

/**
 * The class Current user util.
 *
 * @author Isaev A. Nurmanbetov B.
 */
public class CurrentUserUtil {
    /**
     * Gets current user.
     *
     * @param model the model
     * @return the current user
     */
    public static String getCurrentUser(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        String loggedInUser = currentUser.getLoginName();
        model.addAttribute("loggedInUser", loggedInUser);
        return loggedInUser;
    }

    /**
     * Gets user.
     *
     * @param model the model
     * @return the user
     */
    public static CurrentUser getUser(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        return currentUser;
    }
}