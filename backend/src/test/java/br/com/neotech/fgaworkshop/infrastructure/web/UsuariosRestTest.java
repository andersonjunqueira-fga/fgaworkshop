package br.com.neotech.fgaworkshop.infrastructure.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import br.com.neotech.fgaworkshop.domain.model.Email;
import br.com.neotech.fgaworkshop.domain.model.Telefone;
import br.com.neotech.fgaworkshop.domain.model.Usuario;
import br.com.neotech.util.test.BaseRestTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuariosRestTest extends BaseRestTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getUsuarios() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange("/usuarios", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<?> usuarios = JsonSerialization.readValue(entity.getBody(), List.class);
        assertThat(usuarios.size()).isGreaterThan(0);

    }

    @Test
    public void testeGravacaoEmailValido() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // recuperando os usuários
        HttpEntity<?> e = new HttpEntity<Object>(headers);
        ResponseEntity<String> entity = restTemplate.exchange("/usuarios", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<?> usuarios = JsonSerialization.readValue(entity.getBody(), List.class);
        int quantidade = usuarios.size();

        // gravando um novo usuario com email invalido
        Usuario u = new Usuario();
        u.setNome("Teste Integrado");
        u.setLogin("andersonjunqueira@gmail.com");
        Email email = new Email();
        email.setEmail(u.getLogin());
        email.setPrincipal(true);
        u.setEmails(new ArrayList<Email>());
        u.getEmails().add(email);
        Telefone t = new Telefone();
        t.setNumero("6133270379");
        t.setTipo("res");
        u.setTelefones(new ArrayList<Telefone>());
        u.getTelefones().add(t);
        HttpEntity<?> payload = new HttpEntity<Object>(u, headers);
        entity = restTemplate.exchange("/usuarios", HttpMethod.POST, payload, String.class);

        // recuperando os usuários
        e = new HttpEntity<Object>(headers);
        entity = restTemplate.exchange("/usuarios", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        usuarios = JsonSerialization.readValue(entity.getBody(), List.class);
        int quantidadeNova = usuarios.size();

        // conferindo
        assertThat(quantidadeNova).isGreaterThan(quantidade);

    }

    @Test
    public void testeGravacaoEmailInvalido() throws Exception {

        // recuperando os usuários
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> e = new HttpEntity<Object>(headers);

        ResponseEntity<String> entity = restTemplate.exchange("/usuarios", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<?> usuarios = JsonSerialization.readValue(entity.getBody(), List.class);
        int quantidade = usuarios.size();

        // gravando um novo usuario com email invalido
        Usuario u = new Usuario();
        u.setNome("Teste Integrado");
        u.setLogin("suzana.avilagmail.com");

        Email email = new Email();
        email.setEmail(u.getLogin());
        email.setPrincipal(true);

        u.setEmails(new ArrayList<Email>());
        u.getEmails().add(email);

        Telefone t = new Telefone();
        t.setNumero("6133270379");
        t.setTipo("res");

        u.setTelefones(new ArrayList<Telefone>());
        u.getTelefones().add(t);

        HttpEntity<?> payload = new HttpEntity<Object>(u, headers);

        entity = restTemplate.exchange("/usuarios", HttpMethod.POST, payload, String.class);

        // recuperando os usuários
        e = new HttpEntity<Object>(headers);
        entity = restTemplate.exchange("/usuarios", HttpMethod.GET, e, String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        usuarios = JsonSerialization.readValue(entity.getBody(), List.class);
        int quantidadeNova = usuarios.size();

        // conferindo
        assertThat(quantidade).isEqualTo(quantidadeNova);

    }

    @Test
    public void ok() {
        String a = "áé";
        System.out.println(Charset.forName("UTF-8").encode(a));
    }

}
