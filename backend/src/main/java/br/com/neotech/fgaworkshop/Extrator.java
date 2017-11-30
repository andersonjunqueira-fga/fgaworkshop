package br.com.neotech.fgaworkshop;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.neotech.fgaworkshop.domain.model.Usuario;

public class Extrator {

    public static void main(String[] args) {
        try {
            Gson g = new Gson();
            Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();
            List<Usuario> list = g.fromJson(new FileReader("C:\\Desenv\\projetos\\fga-workshop\\backend\\src\\main\\resources\\usuarios-20170809.json"), listType);

            for(Usuario u : list) {
                StringBuilder str = new StringBuilder()
                    .append(u.getId()).append(";")
                    .append(u.getNome()).append(";")
                    .append(u.getCpf()).append(";")
                    .append(u.getRg()).append(";")
                    .append(u.getOrgaoExpedidor()).append(";")
                    .append(u.getUfDocumento()).append(";")
                    .append(u.getCurso()).append(";")
                    .append(u.getOutroCurso()).append(";")
                    .append(u.getLogradouro()).append(";")
                    .append(u.getComplemento()).append(";")
                    .append(u.getNumero()).append(";")
                    .append(u.getBairro()).append(";")
                    .append(u.getCidade()).append(";")
                    .append(u.getUf()).append(";")
                    .append(u.getCep()).append(";")
                    .append(u.getTelefone()).append(";")
                    .append(u.getEmail());

                System.out.println(str.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
