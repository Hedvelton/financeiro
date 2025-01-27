package br.com.gestao.financeiro.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.gestao.financeiro.model.Vendedor;
import br.com.gestao.financeiro.repository.ContatoRepository;
import br.com.gestao.financeiro.repository.VendedorRepository;
import br.com.gestao.financeiro.service.CookieService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private ContatoRepository contatoRepository;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/logar")
	public String loginUsuario(Vendedor vendedor, Model model, HttpServletResponse response) throws UnsupportedEncodingException {
		Vendedor vendedorLogado = this.vendedorRepository.login(vendedor.getNome(), vendedor.getSenha());

		if (vendedorLogado != null) {
			CookieService.setCookie(response, "vendedorId", String.valueOf(vendedorLogado.getId()), 10000);
			CookieService.setCookie(response, "nomeVendedor", String.valueOf(vendedorLogado.getNome()), 10000);
			return "redirect:/";
		}

		model.addAttribute("erro", "Usuario Invalido!");
		return "/login";
	}

	@GetMapping("/")
	public String dashboard(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		model.addAttribute("nome", CookieService.getCookie(request, "nomeVendedor"));
		model.addAttribute("listaContato", contatoRepository.findAll());

		return "index";
	}

	@GetMapping("/sair")
	public String sair(HttpServletResponse response) throws UnsupportedEncodingException {
		CookieService.setCookie(response, "vendedorId", "", 0);
		return "/login";
	}
}
