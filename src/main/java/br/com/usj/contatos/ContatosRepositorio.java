package br.com.usj.contatos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContatosRepositorio extends CrudRepository<Contato,Long> {
    List<Contato> findAll();
}