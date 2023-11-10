package repositorios.empleados;

import db.HibernateManager;
import exceptions.EmpleadoException;
import jakarta.persistence.TypedQuery;
import model.Departamento;
import model.Empleado;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class EmpleadosRepositoryImpl implements EmpleadosRepository {
    private final Logger logger = Logger.getLogger(EmpleadosRepositoryImpl.class.getName());

    @Override
    public List<Empleado> findAll() {
        logger.info("findAll()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Empleado> query = hb.getManager().createQuery("Empleado.findAll", Empleado.class);
        List<Empleado> list = query.getResultList();
        hb.close();
        return list;
    }

    @Override
    public Optional<Empleado> findById(Integer integer) {
        logger.info("findById()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        Optional<Empleado> empleado = Optional.ofNullable(hb.getManager().find(Empleado.class, integer));
        hb.close();
        return empleado;
    }

    @Override
    public List<Empleado> findByName(String nombre) {
        logger.info("findByName()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Empleado> query = hb.getManager().createQuery("SELECT e FROM Empleado e WHERE e.nombre = :nombre", Empleado.class);
        query.setParameter("nombre", nombre);
        List<Empleado> list = query.getResultList();
        hb.close();
        return list;
    }

    @Override
    public Empleado create(Empleado entity) {
        logger.info("save()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        hb.getTransaction().begin();

        // Por otro lado y si la raqueta no existe? Podemos controlar que exista el departamento
        // antes de guardar el empleado o que la inserte con el empleado. Vamos a ser restrictivos
        var existeDepartamento = hb.getManager().find(entity.getClass(), entity.getDepartamento().getId());
        if (existeDepartamento == null) {
            throw new EmpleadoException("El departamento con nombre: " + entity.getDepartamento().getNombre() + " no existe");
        }
        try {
            hb.getManager().merge(entity);
            hb.getTransaction().commit();
            hb.close();
            return entity;
        } catch (Exception e) {
            throw new EmpleadoException("Error al salvar Empleado con Nombre: " + entity.getNombre() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }

    @Override
    public Boolean delete(Empleado entity) {
        logger.info("delete()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            entity = hb.getManager().find(Empleado.class, entity.getId());
            hb.getManager().remove(entity);
            hb.getTransaction().commit();
            hb.close();
            return true;
        } catch (Exception e) {
            throw new EmpleadoException("Error al eliminar tenista con uuid: " + entity.getId() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }
}
