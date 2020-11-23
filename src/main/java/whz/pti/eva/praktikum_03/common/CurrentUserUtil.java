package whz.pti.eva.praktikum_03.common;

import org.springframework.ui.Model;
import whz.pti.eva.praktikum_03.security.domain.CurrentUser;

public class CurrentUserUtil {
    public static String getCurrentUser(Model model) {
        CurrentUser currentUser = (CurrentUser) model.asMap().get("currentUser");
        String from = currentUser.getNickname();
        model.addAttribute("fromUser", from);
        return from;
    }
}