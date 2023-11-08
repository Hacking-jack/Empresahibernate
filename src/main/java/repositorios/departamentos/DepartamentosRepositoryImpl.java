package repositorios.departamentos;

import java.util.List;
import java.util.logging.Logger;

public class DepartamentosRepositoryImpl implements DepartamentosRepository {
    private final Logger logger = Logger.getLogger(DepartamentosRepositoryImpl.class.getName());
    public List<Departamento> findAll() {
        logger.info("findAll()");
        HibernateManager hibernateManager = new HibernateManager();
    }
}
