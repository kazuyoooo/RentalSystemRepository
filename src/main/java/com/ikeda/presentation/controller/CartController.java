package com.ikeda.presentation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ikeda.entity.DvdItem;
import com.ikeda.repository.DvdItemRepository;
import com.ikeda.service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private DvdItemRepository itemRepository;

    // カート追加
    @SuppressWarnings("unchecked")
	@GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") int id, HttpSession session) {

        List<DvdItem> cart = (List<DvdItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        
        // 重複している商品が追加された場合に、カート追加操作を無効にする処理


        DvdItem item = itemRepository.findById(id).orElse(null);

        if (item != null) {
            cartService.addToCart(item, session);
        }

        return "redirect:/cart"; // カート画面へ
    }

    // カート表示
    @GetMapping
    public String showCart(Model model, HttpSession session) {
        model.addAttribute("cart", cartService.getCart(session));
        return "cart";  // cart.html を表示
    }

    // カートの商品削除
    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable("id") int id, HttpSession session) {
        cartService.removeFromCart(id, session);
        return "redirect:/cart";
    }

    // カート内商品を全て削除
    @GetMapping("/clear")
    public String clearCart(HttpSession session) {
        cartService.clearCart(session);
        return "redirect:/cart";
    }

    /* カート内のアイテム削除機能
    @GetMapping("/delete/{id}")
    public String itemDeletedCart(@PathVariable("id") int id, HttpSession session, Model model) {
    	List<DvdItem> cart = (List<DvdItem>) session.getAttribute("cart");
    	
    	if (cart != null) {
    	cart.removeIf(DvdItem -> DvdItem.getId() == (id));
    	session.setAttribute("cart", cart);
    	model.addAttribute("cart", cart);
    	}
    	return "cart";
    }
    */
    // 注文を確定時の処理
//    @GetMapping("/cart/confirm")
//    public String confirm() {
    
    	// ログイン中のcustomerのstatusにてレンタル状況を更新
    	// ordersにて新規注文情報を作成
    	// productsのrented_stock、not_rented_stockにて在庫状況を更新
    
//    	return  注文情報表示ページ ;
//    }
}
    


