package af.afproject.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import af.afproject.model.Reserva;

@Component
public class ReservaRepository implements IRepository<Reserva> {

  public ArrayList<Reserva> reservas = new ArrayList<Reserva>();

  private int nextCode = 1;

  @Override
  public ArrayList<Reserva> getAll() {
    return reservas;
  }

  @Override
  public Optional<Reserva> getByCodigo(int codigo) {
    for (Reserva reserva : reservas) {
      if (reserva.getCodigo() == codigo) {
        return Optional.of(reserva);
      }
    }
    return Optional.empty();
  }

  @Override
  public Reserva save(Reserva reserva) {
    reserva.setCodigo(nextCode++);
    reservas.add(reserva);
    return reserva;
  }

  @Override
  public void remove(Reserva reserva) {
    reservas.remove(reserva);
  }

  @Override
  public Reserva update(Reserva reserva) {
    Reserva aux = getByCodigo(reserva.getCodigo()).get();
    if (aux != null) {
      // Atualizar apenas dataFim
      aux.setDataFim(reserva.getDataFim());
    }
    return aux;
  }
}
