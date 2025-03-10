package repository;

import java.util.List;

public interface GenericRepository<T> {
    List<T> getAll();
    void saveAll(List<T> list);
    void update(T entity);
    void delete(int id);
}