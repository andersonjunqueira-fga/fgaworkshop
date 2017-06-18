package br.com.neotech.fgaworkshop.domain.service;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import br.com.neotech.util.infraestructure.exception.EmailException;
import br.com.neotech.util.infraestructure.service.SendMailService;

@Service
public class EmailService extends SendMailService {

    @Autowired
    private Environment env;

    public void enviarUsuarioCadastrado(String nome, String email, String cpf, String curso) throws EmailException {
        try {

            enviar(
                env.getProperty("fgaworkflow.mail.from"),
                env.getProperty("fgaworkflow.mail.cadastro.subject"),

                MessageFormat.format(env.getProperty("fgaworkflow.mail.cadastro.message"), new Object[] { nome, cpf, curso }),

                    new InternetAddress(email, nome)
            );

        } catch (UnsupportedEncodingException e) {
            throw new EmailException("Email inválido", e);
        }

    }
}
