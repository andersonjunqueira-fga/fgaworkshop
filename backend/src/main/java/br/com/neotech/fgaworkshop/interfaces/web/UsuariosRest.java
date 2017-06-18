package br.com.neotech.fgaworkshop.interfaces.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.neotech.fgaworkshop.domain.model.Usuario;
import br.com.neotech.fgaworkshop.domain.service.UsuarioService;
import br.com.neotech.util.infraestructure.exception.ExceptionDTO;
import br.com.neotech.util.infraestructure.exception.GlobalExceptionHandler;
import br.com.neotech.util.infraestructure.exception.RequisicaoInvalidaException;
import br.com.neotech.util.infraestructure.web.RestFullEndpoint;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuariosRest extends RestFullEndpoint<Usuario, Long> {

    @Autowired
    public UsuariosRest(UsuarioService service) {
        super(service);
    }

    @RequestMapping(method = RequestMethod.GET, path="/login")
    public ResponseEntity<Usuario> getByLogin(@RequestParam("login") String login) {
        Usuario u = ((UsuarioService)service).findByLogin(login);

        if(u != null) {
            return new ResponseEntity<>(u, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> add(@RequestBody Usuario input) {
        try {
            if (input.getNome() == null ||
                input.getCpf() == null ||
                input.getEmail() == null ||
                input.getTelefone() == null ||
                input.getCurso() == null ||
                (input.getCurso().equals("outro") && input.getOutroCurso() == null)) {
                throw new RequisicaoInvalidaException("campos-obrigatorios-nao-preenchidos");
            }

            return super.add(input);
        } catch(Exception ex) {
            ExceptionDTO error = GlobalExceptionHandler.tratamentoErro(ex);
            return new ResponseEntity<ExceptionDTO>(error, error.getErrorCode());
        }

    }
}
