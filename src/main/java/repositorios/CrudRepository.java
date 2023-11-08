package repositorios;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T,ID> {
     List<T> findAll();

    Optional<T> findById(ID id);
    List<T> findByName(T entity);

    T create(T entity);

    Boolean delete(T entity);

    Boolean update(T entity);

    Optional<T> read(T entity);
    List<T> get(T entity);

}
