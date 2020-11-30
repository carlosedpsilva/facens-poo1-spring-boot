package af.afproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import af.afproject.dto.VeiculoDTO;
import af.afproject.model.Reserva;
import af.afproject.model.Veiculo;
import af.afproject.repository.VeiculoRepository;

@Service
public class VeiculoService {

  @Autowired
  private VeiculoRepository veiculoRepository;

  @Autowired
  private ReservaService reservaService;

  public Veiculo fromDTO(VeiculoDTO dto) {
    return new Veiculo(dto);
  }

  public List<Veiculo> getAllVeiculos() {
    return veiculoRepository.getAll();
  }

  public Veiculo getVeiculoByCodigo(int codigo) {
    Optional<Veiculo> op = veiculoRepository.getByCodigo(codigo);
    return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não cadastrado"));
  }

  public Veiculo save(Veiculo veiculo) {
    return veiculoRepository.save(veiculo);
  }

  public void removeByCodigo(int codigo) {
    Veiculo veiculo = getVeiculoByCodigo(codigo);
    for (Reserva reserva : veiculo.getReservas())
      reservaService.removeByCodigo(reserva.getCodigo());
    veiculoRepository.remove(veiculo);
  }

  public Veiculo update(Veiculo veiculo) {
    getVeiculoByCodigo(veiculo.getCodigo());
    return veiculoRepository.update(veiculo);
  }
}
