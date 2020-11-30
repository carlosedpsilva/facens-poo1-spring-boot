package af.afproject.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import af.afproject.dto.ReservaDTO;
import af.afproject.model.Cliente;
import af.afproject.model.Reserva;
import af.afproject.model.Veiculo;
import af.afproject.repository.ReservaRepository;

@Service
public class ReservaService {

  @Autowired
  private ReservaRepository reservaRepository;

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private VeiculoService veiculoService;

  public Reserva fromDTO(ReservaDTO dto) {
    return new Reserva(dto);
  }

  public ReservaDTO toDTO(Reserva reserva) {
    ReservaDTO dto = new ReservaDTO();
    dto.setCodigo(reserva.getCodigo());
    dto.setDataInicio(reserva.getDataInicio());
    dto.setDataFim(reserva.getDataFim());
    dto.setTotalReserva(reserva.totalReserva());
    return dto;
  }

  public List<Reserva> getAllReservas() {
    return reservaRepository.getAll();
  }

  public Reserva getReservaByCodigo(int codigo) {
    Optional<Reserva> op = reservaRepository.getByCodigo(codigo);
    return op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não existe"));
  }

  public Reserva save(Reserva reserva, int idCliente, int idVeiculo) {
    Cliente cliente = clienteService.getClienteByCodigo(idCliente);
    Veiculo veiculo = veiculoService.getVeiculoByCodigo(idVeiculo);

    validatePeriod(reserva, reserva.getDataInicio(), reserva.getDataFim(), veiculo.getReservas());

    reserva.setCliente(cliente);
    reserva.setVeiculo(veiculo);

    cliente.addReserva(reserva);
    veiculo.addReserva(reserva);

    return reservaRepository.save(reserva);
  }

  public void removeByCodigo(int codigo) {
    reservaRepository.remove(getReservaByCodigo(codigo));
  }

  public Reserva update(Reserva reserva) {
    Reserva aux = getReservaByCodigo(reserva.getCodigo());
    validatePeriod(aux, aux.getDataInicio(), reserva.getDataFim(), aux.getVeiculo().getReservas());
    return reservaRepository.update(reserva);
  }

  public Cliente getCliente(Reserva reserva) {
    return reservaRepository.getCliente(reserva);
  }

  public Veiculo getVeiculo(Reserva reserva) {
    return reservaRepository.getVeiculo(reserva);
  }

  public List<ReservaDTO> toListDTO(List<Reserva> reservas) {
    ArrayList<ReservaDTO> listDTO = new ArrayList<>();

    for (Reserva p : reservas) {
      listDTO.add(toDTO(p));
    }
    return listDTO;
  }

  private void validatePeriod(Reserva reserva, LocalDate dataInicio, LocalDate dataFim, List<Reserva> reservas) {
    if (dataInicio.compareTo(dataFim) > 0)
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Período inválido"));

    if (dataInicio.getDayOfWeek().equals(DayOfWeek.SUNDAY))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Período de reserva não pode começar no Domingo");

    if (dataFim.getDayOfWeek().equals(DayOfWeek.SUNDAY))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Período de reserva não pode terminar no Domingo");

    for (Reserva res : reservas) {
      if (!res.equals(reserva)) // não comparar com o período da própria reserva
        if (!(dataInicio.compareTo(res.getDataInicio()) < 0 && dataFim.compareTo(res.getDataInicio()) < 0)
            && !(dataInicio.compareTo(res.getDataFim()) > 0 && dataFim.compareTo(res.getDataFim()) > 0))
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
              "Este veículo já está reservado no período inserido");
    }

    // valid
  }
}
