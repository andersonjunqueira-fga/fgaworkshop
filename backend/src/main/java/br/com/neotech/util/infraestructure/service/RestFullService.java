package br.com.neotech.util.infraestructure.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.neotech.util.infraestructure.exception.NegocioException;

public class RestFullService<E, PK extends Serializable> {

    private Class<E> modelClass;
    protected final JpaRepository<E, PK> repository;

    @SuppressWarnings("unchecked")
    private void loadTypes() {
        if(modelClass == null) {
            final ParameterizedType type = (ParameterizedType)getClass().getGenericSuperclass();
            modelClass = (Class<E>)type.getActualTypeArguments()[0];
        }
    }

    protected RestFullService(JpaRepository<E, PK> repository) {
        this.repository = repository;
        loadTypes();
    }

    public List<E> findAll() throws NegocioException  {
        return repository.findAll();
    }

    public E findById(PK id) throws NegocioException  {
        return repository.findOne(id);
    }

    @Transactional
    public void delete(PK id) throws NegocioException  {
        repository.delete(id);
    }

    @Transactional
    public E create(E e) throws NegocioException {
        return repository.save(e);
    }

    @Transactional
    public E update(E e) throws NegocioException  {
        return repository.save(e);
    }

}
