package br.com.gestao.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gestao.financeiro.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, String> {

}
