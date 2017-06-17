package br.com.neotech.fgaworkshop.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.neotech.fgaworkshop.domain.model.Estado;
import br.com.neotech.fgaworkshop.infraestructure.persistence.jpa.EstadosRepository;
import br.com.neotech.util.infraestructure.service.RestFullService;

@Service
public class EstadosService extends RestFullService<Estado, Long> {

    @Autowired
    EstadosService(EstadosRepository repository) {
        super(repository);
    }

    @Override
    public List<Estado> findAll() {
        return repository.findAll(new Sort(Sort.Direction.ASC, "nome"));
    }

}
