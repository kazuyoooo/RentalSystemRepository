package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ikeda.entity.Member;
import com.ikeda.presentation.form.LoginForm;
import com.ikeda.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;  // インスタンスを注入

    // GET /login → ログイン画面表示
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login"; // templates/login.html
    }

    @PostMapping("/login")
    public String doLogin(@ModelAttribute LoginForm loginForm, Model model, HttpSession session) {
        Member member = loginService.login(loginForm.getEmail(), loginForm.getPassword());

        if (member != null) {
            // ログイン成功
            session.setAttribute("loginUser", member);
            return "redirect:/index";
        } else {
            // ログイン失敗
            model.addAttribute("error", "メールアドレスまたはパスワードが違います");
            return "login";
        }
		

	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // セッションを完全に破棄
	    return "redirect:/";  // トップページへ戻る
	}

}
