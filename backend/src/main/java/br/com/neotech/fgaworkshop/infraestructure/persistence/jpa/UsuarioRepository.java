package br.com.neotech.fgaworkshop.infraestructure.persistence.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.neotech.fgaworkshop.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByLogin(String login);
    List<Usuario> findByCpfOrEmail(String cpf, String email);

}
