package br.com.neotech.fgaworkshop.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.neotech.fgaworkshop.domain.model.Usuario;
import br.com.neotech.fgaworkshop.infraestructure.persistence.jpa.UsuarioRepository;
import br.com.neotech.util.infraestructure.exception.NegocioException;
import br.com.neotech.util.infraestructure.service.RestFullService;

@Service
public class UsuarioService extends RestFullService<Usuario, Long> {

    @Autowired
    private UsuarioRepository rep;

    @Autowired
    UsuarioService(UsuarioRepository repository) {
        super(repository);
    }

    public Usuario findByLogin(String login) {
        return rep.findByLogin(login);
    }

    @Override
    public Usuario save(Usuario usuario) {

        List<Usuario> usuariosBanco = rep.findByCpfOrEmail(usuario.getCpf(), usuario.getEmail());
        if(usuariosBanco == null || usuariosBanco.isEmpty()) {
            return super.save(usuario);

        } else {

            String msg = null;
            for(Usuario u : usuariosBanco) {
                if(usuario.getCpf().equals(u.getCpf())) {
                    msg = "cpf-duplicado";
                    break;
                } else if(usuario.getEmail().equals(u.getEmail())) {
                    msg = "email-duplicado";
                    break;
                }
            }
            throw new NegocioException(msg);

        }
    }
}
