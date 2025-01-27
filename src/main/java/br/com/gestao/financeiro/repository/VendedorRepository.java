package br.com.gestao.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gestao.financeiro.model.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, String> {

	Vendedor findById(Long id);

	@Query(value = "select * from db_treiner.vendedor where nome = :nome and senha = :senha", nativeQuery = true)
	public Vendedor login(String nome, String senha);

}
