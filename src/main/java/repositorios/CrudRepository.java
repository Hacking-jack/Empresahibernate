package repositorios;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T,ID> {
     List<T> findAll();

    Optional<T> findById(ID id);
    List<T> findByName(String name);

    T modify(T entity);
    T create(T entity);

    Boolean delete(T entity);


}
