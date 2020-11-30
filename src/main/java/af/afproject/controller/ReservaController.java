package af.afproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.afproject.dto.ReservaDTO;
import af.afproject.model.Reserva;
import af.afproject.service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

  @Autowired
  private ReservaService reservaService;

  @GetMapping
  public List<Reserva> getReservas() {
    return reservaService.getAll();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Reserva> getReserva(@PathVariable int codigo) {
    Reserva reserva = reservaService.getByCodigo(codigo);
    return ResponseEntity.ok(reserva);
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<Void> remover(@PathVariable int codigo) {
    reservaService.removeByCodigo(codigo);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<Reserva> atualizar(@PathVariable int codigo, @RequestBody ReservaDTO reservaDTO) {
    Reserva reserva = reservaService.fromDTO(reservaDTO);
    reserva.setCodigo(codigo);
    reserva = reservaService.update(reserva);
    return ResponseEntity.ok(reserva);
  }
}
