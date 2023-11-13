package repositorios.proyectos;

import db.HibernateManager;
import exceptions.DepartamentoException;
import exceptions.EmpleadoException;
import jakarta.persistence.TypedQuery;
import model.Departamento;
import model.Empleado;
import model.Proyecto;
import repositorios.CrudRepository;
import repositorios.departamentos.DepartamentosRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ProyectosRepositoryImpl implements ProyectosRepository {
    private final Logger logger = Logger.getLogger(ProyectosRepositoryImpl.class.getName());
    public List<Proyecto> findAll() {
        logger.info("findAll()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Proyecto> query = hb.getManager().createNamedQuery("Proyecto.findAll", Proyecto.class);
        List<Proyecto> list = query.getResultList();
        hb.close();
        return list;
    }
    @Override
    public Optional<Proyecto> findById(Integer integer) {
        logger.info("findById()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        Optional<Proyecto> proyecto = Optional.ofNullable(hb.getManager().find(Proyecto.class, integer));
        hb.close();
        return proyecto;
    }

    @Override
    public List<Proyecto> findByName(String nombre) {
        logger.info("findByName()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        TypedQuery<Proyecto> query = hb.getManager().createQuery("SELECT e FROM Proyecto e WHERE e.nombre = :nombre", Proyecto.class);
        query.setParameter("nombre", nombre);
        List<Proyecto> list = query.getResultList();
        hb.close();
        return list;
    }

    @Override
    public Proyecto create(Proyecto entity) {
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
            throw new EmpleadoException("Error al salvar Departamento con Nombre: " + entity.getNombre() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }
    @Override
    public Boolean delete(Proyecto entity) {
        logger.info("delete()");
        HibernateManager hb = HibernateManager.getInstance();
        hb.open();
        try {
            hb.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            entity = hb.getManager().find(Proyecto.class, entity.getId());
            hb.getManager().remove(entity);
            hb.getTransaction().commit();
            hb.close();
            return true;
        } catch (Exception e) {
            throw new EmpleadoException("Error al eliminar proyecto con uuid: " + entity.getId() + "\n" + e.getMessage());
        } finally {
            if (hb.getTransaction().isActive()) {
                hb.getTransaction().rollback();
            }
        }
    }


   }
