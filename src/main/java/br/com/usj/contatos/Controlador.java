package br.com.usj.contatos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Controlador {

    @Autowired
    ContatosRepositorio repositorio;

    // ------------------------------------------ LINKS DA HOME
    // --------------------------------------------------------//

    // Mapeamento Home Index
    @GetMapping(value = "/")
    public ModelAndView getIndex() {
        // Chama Lista de Contatos do repositorio
        List<Contato> contatos = repositorio.findAll();
        // Envia Lista atraves do modelo
        ModelAndView modelo = new ModelAndView("index");
        modelo.addObject("lista", contatos);
        return modelo;
    }

    // Link Home Adicionar
    @GetMapping(value = "/adicionar")
    public String getAdicionar() {
        // Retorna o template adicionar como string
        return "adicionar";
    }

    // Link Home Editar

    @GetMapping(value = "/editar/{id}")
    public ModelAndView getEditar(@PathVariable("id") int id) {
        // Converte id Int para Long
        Long nId = Long.valueOf(id);
        // Chama Lista de Contatos do repositorio
        Contato contato = repositorio.findById(nId).get();
        // Envia Lista atraves do modelo
        ModelAndView modelo = new ModelAndView("editar");
        modelo.addObject("contato", contato);
        return modelo;
    }

    // Link Home Excluir

    @GetMapping(value = "/excluir/{id}")
    public ModelAndView getExcluir(@PathVariable("id") int id) {
        // Converte id Int para Long
        Long nId = Long.valueOf(id);
        // Exclue o contato pelo ID
        repositorio.deleteById(nId);
        //Retorna Index
        return getIndex();
    }

    // Link Home Visualizar

    @GetMapping(value = "/visualizar/{id}")
    public ModelAndView getVisualizar(@PathVariable("id") int id) {
        // Converte id Int para Long
        Long nId = Long.valueOf(id);
        // Chama Lista de Contatos do repositorio
        Contato contato = repositorio.findById(nId).get();
        // Envia Lista atraves do modelo
        ModelAndView modelo = new ModelAndView("visualizar");
        modelo.addObject("contato", contato);
        return modelo;
    }

    // -------------------------------------------------- LINKS DA HOME
    // ----------------------------------------------//

    // Post Adicionar
    @PostMapping(value = "/adicionar")
    public ModelAndView postAdicionar(@RequestParam String nome, @RequestParam String email,
            @RequestParam String telf) {
        // Cria Instancia Contato
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelf(telf);
        // Salva no repositorio
        repositorio.save(contato);
        ModelAndView modelo = new ModelAndView("adicionar");
        return modelo;
    }

    // Post Editar
    @PostMapping(value = "/editar")
    public ModelAndView postEditar(@RequestParam String nome, @RequestParam String email, @RequestParam String telf,
            @RequestParam String id) {
        // Converte Id String para Long
        Long nId = Long.parseLong(id);
        // Cria Instancia Contato
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEmail(email);
        contato.setTelf(telf);
        contato.setId(nId);
        // Salva no repositorio as novas informações passando o id pelo contato
        repositorio.save(contato);
        // Retorno para o index depois de salvar a edição
        return getIndex();
    }

}