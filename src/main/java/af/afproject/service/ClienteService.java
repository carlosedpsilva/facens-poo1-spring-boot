package af.afproject.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import af.afproject.dto.ClienteDTO;
import af.afproject.model.Cliente;
import af.afproject.model.Reserva;
import af.afproject.repository.ClienteRepository;

@Service
public class ClienteService implements IService<Cliente> {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private ReservaService reservaService;

  public Cliente fromDTO(ClienteDTO dto) {
    return new Cliente(dto);
  }

  @Override
  public Cliente getByCodigo(int codigo) {
    Optional<Cliente> op = clienteRepository.getByCodigo(codigo);
    return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não cadastrado"));
  }

  @Override
  public ArrayList<Cliente> getAll() {
    return clienteRepository.getAll();
  }

  @Override
  public Cliente save(Cliente cliente) {
    return clienteRepository.save(cliente);
  }

  @Override
  public void removeByCodigo(int codigo) {
    Cliente cliente = getByCodigo(codigo);
    for (Reserva reserva : cliente.getReservas())
      reservaService.remove(reserva);
    clienteRepository.remove(cliente);
  }

  @Override
  public void remove(Cliente cliente) {
    clienteRepository.remove(cliente);
  }

  @Override
  public Cliente update(Cliente cliente) {
    getByCodigo(cliente.getCodigo());
    return clienteRepository.update(cliente);
  }
}
