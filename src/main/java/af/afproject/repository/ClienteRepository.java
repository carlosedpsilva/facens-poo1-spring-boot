package af.afproject.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import af.afproject.model.Cliente;

@Component
public class ClienteRepository implements IRepository<Cliente> {

  public ArrayList<Cliente> clientes = new ArrayList<>();

  private int nextCode = 1;

  @Override
  public ArrayList<Cliente> getAll() {
    return clientes;
  }

  @Override
  public Optional<Cliente> getByCodigo(int codigo) {
    for (Cliente aux : clientes) {
      if (aux.getCodigo() == codigo)
        return Optional.of(aux);
    }
    return Optional.empty();
  }

  @Override
  public Cliente save(Cliente cliente) {
    cliente.setCodigo(nextCode++);
    clientes.add(cliente);
    return cliente;
  }

  @Override
  public void remove(Cliente cliente) {
    clientes.remove(cliente);
  }

  @Override
  public Cliente update(Cliente cliente) {
    Cliente aux = getByCodigo(cliente.getCodigo()).get();
    if (aux != null) {
      // Atualizar apenas endereço
      aux.setEndereco(cliente.getEndereco());
    }
    return aux;
  }
}
