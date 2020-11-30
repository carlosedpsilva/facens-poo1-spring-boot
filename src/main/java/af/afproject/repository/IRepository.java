package af.afproject.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T> {

  public List<T> getAll();

  public Optional<T> getByCodigo(int codigo);

  public T save(T data);

  public void remove(T data);

  public T update(T data);

}
