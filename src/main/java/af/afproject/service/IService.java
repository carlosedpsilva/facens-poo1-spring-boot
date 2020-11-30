package af.afproject.service;

import java.util.ArrayList;

public interface IService<T> {

  public ArrayList<T> getAll();

  public T getByCodigo(int codigo);

  public T save(T data);

  default T save(T data, int... codigos) {
    return save(data);
  }

  public void removeByCodigo(int codigo);

  public void remove(T data);

  public T update(T data);

}
