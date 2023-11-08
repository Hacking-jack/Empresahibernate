package repositorios.proyectos;

import model.Proyecto;
import repositorios.CrudRepository;
import repositorios.departamentos.DepartamentosRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ProyectosRepositoryImpl implements ProyectosRepository {
    private final Logger logger = Logger.getLogger(ProyectosRepositoryImpl.class.getName());

    @Override
    public List<Proyecto> findAll() {
        return null;
    }

    @Override
    public Optional<Proyecto> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Proyecto> findByName(Proyecto entity) {
        return null;
    }

    @Override
    public Proyecto create(Proyecto entity) {
        return null;
    }

    @Override
    public Boolean delete(Proyecto entity) {
        return null;
    }

    @Override
    public Boolean update(Proyecto entity) {
        return null;
    }

    @Override
    public Optional<Proyecto> read(Proyecto entity) {
        return Optional.empty();
    }

    @Override
    public List<Proyecto> get(Proyecto entity) {
        return null;
    }
}
