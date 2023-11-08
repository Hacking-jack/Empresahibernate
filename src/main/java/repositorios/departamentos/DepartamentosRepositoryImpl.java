package repositorios.departamentos;

import db.HibernateManager;
import jakarta.persistence.TypedQuery;
import model.Departamento;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DepartamentosRepositoryImpl implements DepartamentosRepository {
    private final Logger logger = Logger.getLogger(DepartamentosRepositoryImpl.class.getName());
    public List<Departamento> findAll() {
        logger.info("findAll()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Departamento> query = hb.getManager().createQuery("Departamento.findAll", Departamento.class);
        List<Departamento> list = query.getResultList();
        hb.close();
        return list;
    }

    @Override
    public Optional<Departamento> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Departamento> findByName(Departamento entity) {
        return null;
    }

    @Override
    public Departamento create(Departamento entity) {
        return null;
    }

    @Override
    public Boolean delete(Departamento entity) {
        return null;
    }

    @Override
    public Boolean update(Departamento entity) {
        return null;
    }

    @Override
    public Optional<Departamento> read(Departamento entity) {
        return Optional.empty();
    }

    @Override
    public List<Departamento> get(Departamento entity) {
        return null;
    }
}
