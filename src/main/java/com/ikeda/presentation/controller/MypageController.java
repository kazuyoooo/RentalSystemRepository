import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {

    @GetMapping("/mypage")
    public String showMypage(HttpSession session, Model model) {
        // セッションからログインユーザーを取得
        String loginUser = (String) session.getAttribute("loginUser");
        
        if (loginUser == null) {
            // 未ログインならログインページへリダイレクト
            return "redirect:/login";
        }

        // 必要に応じてユーザー情報やレンタル履歴を model にセット
        model.addAttribute("loginUser", loginUser);

        // templates/mypage.html を表示
        return "editForm";
    }
}
