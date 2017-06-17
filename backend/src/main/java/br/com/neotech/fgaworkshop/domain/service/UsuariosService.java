package br.com.neotech.fgaworkshop.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.neotech.fgaworkshop.domain.model.Email;
import br.com.neotech.fgaworkshop.domain.model.Telefone;
import br.com.neotech.fgaworkshop.domain.model.Usuario;
import br.com.neotech.fgaworkshop.infraestructure.persistence.jpa.EmailRepository;
import br.com.neotech.fgaworkshop.infraestructure.persistence.jpa.TelefoneRepository;
import br.com.neotech.fgaworkshop.infraestructure.persistence.jpa.UsuarioRepository;
import br.com.neotech.util.infraestructure.exception.NegocioException;
import br.com.neotech.util.infraestructure.service.RestFullService;

@Service
public class UsuariosService extends RestFullService<Usuario, Long> {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    UsuariosService(UsuarioRepository repository) {
        super(repository);
    }

    public Usuario findByLogin(String login) {
        return ((UsuarioRepository)repository).findByLogin(login);
    }

    @Override
    @Transactional
    public Usuario create(Usuario usuario) throws NegocioException {
        if(usuario.getEmails() != null && usuario.getEmails().size() == 1) {
            usuario.getEmails().get(0).setPrincipal(true);
        }
        usuario = repository.save(usuario);

        try {
            for(Email e : usuario.getEmails()) {
                e.setIdUsuario(usuario.getId());
                emailRepository.save(e);
            }
        } catch(DataIntegrityViolationException ex) {
            throw new NegocioException("email-ja-cadastrado", ex);
        }

        for(Telefone e : usuario.getTelefones()) {
            e.setIdUsuario(usuario.getId());
            telefoneRepository.save(e);
        }

        return usuario;
    }

}
