package af.afproject.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import af.afproject.model.Cliente;
import af.afproject.model.Reserva;
import af.afproject.model.Veiculo;

@Component
public class ReservaRepository implements IRepository<Reserva> {

  public List<Reserva> reservas = new ArrayList<Reserva>();

  private int nextCode = 1;

  public List<Reserva> getAll() {
    return reservas;
  }

  public Optional<Reserva> getByCodigo(int codigo) {
    for (Reserva reserva : reservas) {
      if (reserva.getCodigo() == codigo) {
        return Optional.of(reserva);
      }
    }
    return Optional.empty();
  }

  public Reserva save(Reserva reserva) {
    reserva.setCodigo(nextCode++);
    reservas.add(reserva);
    return reserva;
  }

  public void remove(Reserva reserva) {
    reservas.remove(reserva);
  }

  public Reserva update(Reserva reserva) {
    Reserva aux = getByCodigo(reserva.getCodigo()).get();
    if (aux != null) {
      // Atualizar apenas dataFim
      aux.setDataFim(reserva.getDataFim());
    }
    return aux;
  }

  public Cliente getCliente(Reserva reserva) {
    return reserva.getCliente();
  }

  public Veiculo getVeiculo(Reserva reserva) {
    return reserva.getVeiculo();
  }
}
