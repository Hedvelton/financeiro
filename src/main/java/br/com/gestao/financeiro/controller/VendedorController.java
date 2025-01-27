package br.com.gestao.financeiro.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.gestao.financeiro.model.Vendedor;
import br.com.gestao.financeiro.repository.VendedorRepository;
import br.com.gestao.financeiro.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
public class VendedorController {

	@Autowired
	private VendedorRepository repository;

	@GetMapping("/cadastroVendedor")
	public String cadastro(HttpServletRequest request, Model model) throws UnsupportedEncodingException {

		model.addAttribute("nome", CookieService.getCookie(request, "nomeVendedor"));
		model.addAttribute("listaVendedor", repository.findAll());

		return "cadastroVendedor";
	}

	@RequestMapping(value = "/cadastroVendedor", method = RequestMethod.POST)
	public String cadastroVendedor(@Valid Vendedor vendedor, BindingResult result) {

		if (result.hasErrors()) {
			return "redirect:/cadastroVendedor";
		}

		repository.save(vendedor);
		return "redirect:/login";
	}
}
