package repositorios.empleados;

import db.HibernateManager;
import exceptions.EmpleadoException;
import jakarta.persistence.TypedQuery;
import model.Empleado;
import model.Proyecto;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

public class EmpleadosRepositoryImpl implements EmpleadosRepository {
    private final Logger logger = Logger.getLogger(EmpleadosRepositoryImpl.class.getName());

    @Override
    public List<Empleado> findAll() {
        logger.info("findAll()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Empleado> query = hb.getManager().createQuery("SELECT e FROM Empleado e", Empleado.class);
        List<Empleado> list =  query.getResultList();
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


        try {
            hb.getManager().merge(entity);
            hb.getTransaction().commit();
            hb.close();
            return entity;
        } catch (Exception e) {
            throw new EmpleadoException("Error al salvar Empleado con Nombre: " + entity.getNombre());
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
            entity.removeDepartamento();
            Set<Proyecto> proyectos = entity.getProyecto();
            for (int i = 0; i < proyectos.size(); i++) {
          //      proyectos.get(i).removeEmpleado(entity);
            }
            create(entity);
            hb.getManager().remove(entity);
            hb.getTransaction().commit();
            hb.close();
            return true;
        } catch (Exception e) {
            throw new EmpleadoException("Error al eliminar empleado con id: " + entity.getId() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }
}
