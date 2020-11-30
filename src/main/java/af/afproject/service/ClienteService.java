package af.afproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import af.afproject.dto.ClienteDTO;
import af.afproject.model.Cliente;
import af.afproject.repository.ClienteRepository;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public Cliente fromDTO(ClienteDTO dto) {
    return new Cliente(dto);
  }

  public Cliente getClienteByCodigo(int codigo) {
    Optional<Cliente> op = clienteRepository.getByCodigo(codigo);
    return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não cadastrado"));
  }

  public List<Cliente> getAllClientes() {
    return clienteRepository.getAll();
  }

  public Cliente save(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  public void removeByCodigo(int codigo) {
    clienteRepository.remove(getClienteByCodigo(codigo));
  }

  public Cliente update(Cliente cliente) {
    getClienteByCodigo(cliente.getCodigo());
    return clienteRepository.update(cliente);
  }

  public void remove(Cliente cliente) {
    clienteRepository.remove(cliente);
  }
}
