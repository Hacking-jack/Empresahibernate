package repositorios.departamentos;

import db.HibernateManager;
import exceptions.DepartamentoException;
import exceptions.EmpleadoException;
import jakarta.persistence.TypedQuery;
import model.Departamento;
import model.Empleado;

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
        logger.info("findById()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        Optional<Departamento> departamento = Optional.ofNullable(hb.getManager().find(Departamento.class, integer));
        hb.close();
        return departamento;
    }

    @Override
    public List<Departamento> findByName(String nombre) {
        logger.info("findByName()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Departamento> query = hb.getManager().createQuery("SELECT e FROM Departamento e WHERE e.nombre = :nombre", Departamento.class);
        query.setParameter("nombre", nombre);
        List<Departamento> list = query.getResultList();
        hb.close();
        return list;
    }

    @Override
    public Departamento create(Departamento entity) {
        logger.info("save()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        // Por otro lado y si la raqueta no existe? Podemos controlar que exista el departamento
        // antes de guardar el empleado o que la inserte con el empleado. Vamos a ser restrictivos
        var existeJefe = hb.getManager().find(entity.getClass(), entity.getJefe());
        if (existeJefe == null) {
            throw new EmpleadoException("El empleado con nombre: " + entity.getJefe().getNombre() + " no existe");
        }
        try {
            hb.getManager().merge(entity);
            hb.getTransaction().commit();
            hb.close();
            return entity;
        } catch (Exception e) {
            throw new EmpleadoException("Error al salvar Departamento con Nombre: " + entity.getNombre() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }

    @Override
    public Boolean delete(Departamento entity) {
        logger.info("delete()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            entity = hb.getManager().find(Departamento.class, entity.getJefe());
            hb.getManager().remove(entity);
            hb.getTransaction().commit();
            hb.close();
            return true;
        } catch (Exception e) {
            throw new DepartamentoException("Error al eliminar Departamento con id: " + entity.getId() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }
}
