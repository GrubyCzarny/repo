package com.packt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.domain.Product;
import com.packt.service.ProductsService;

@Controller
public class HomeController {

	@Autowired
	ProductsService productsService;
	private List<Product> list;

	@RequestMapping("/")
	public String showHome(Model m) {
		m.addAttribute("greeting", "Welcome to the internet store");
		m.addAttribute("tagline", "We provide best equipment available");
		list = new ArrayList<Product>();
		list = productsService.getAllProducts();
		m.addAttribute("produkt", list);
		System.out.println(list);
		return "home";
	}

	@RequestMapping("/order")
	public String processOrder(@RequestParam("id") int productId, @RequestParam("count") int count, Model model) {
		productsService.processOrder(productId, count);
		Product product = productsService.getProductById(productId);

		model.addAttribute("product", product);

		return "order-confirmation";
	}
}
