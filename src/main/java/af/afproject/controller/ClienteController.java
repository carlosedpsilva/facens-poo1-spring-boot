package af.afproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import af.afproject.dto.ClienteDTO;
import af.afproject.dto.ReservaDTO;
import af.afproject.model.Cliente;
import af.afproject.model.Reserva;
import af.afproject.service.ClienteService;
import af.afproject.service.ReservaService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @Autowired
  private ReservaService reservaService;

  @GetMapping
  public List<Cliente> getClientes() {
    return clienteService.getAll();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Cliente> getCliente(@PathVariable final int codigo) {
    Cliente cliente = clienteService.getByCodigo(codigo);
    return ResponseEntity.ok(cliente);
  }

  @GetMapping("/{codigo}/reservas")
  public ArrayList<ReservaDTO> getVeiculosCliente(@PathVariable int codigo) {
    Cliente cliente = clienteService.getByCodigo(codigo);
    return reservaService.toListDTO(cliente.getReservas());
  }

  @PostMapping()
  public ResponseEntity<Void> salvar(@Valid @RequestBody ClienteDTO clienteDTO, HttpServletRequest request,
      UriComponentsBuilder builder) {
    Cliente cliente = clienteService.save(clienteService.fromDTO(clienteDTO));
    UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + cliente.getCodigo()).build();
    return ResponseEntity.created(uriComponents.toUri()).build();
  }

  @PostMapping("/{codigoCliente}/veiculos/{codigoVeiculo}")
  public ResponseEntity<Void> salvar(@PathVariable int codigoCliente, @PathVariable int codigoVeiculo,
      @Valid @RequestBody ReservaDTO reservaDTO, HttpServletRequest request, UriComponentsBuilder builder) {
    Reserva reserva = reservaService.save(reservaService.fromDTO(reservaDTO), codigoCliente, codigoVeiculo);
    UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + reserva.getCodigo()).build();
    return ResponseEntity.created(uriComponents.toUri()).build();
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<Void> remover(@PathVariable int codigo) {
    clienteService.removeByCodigo(codigo);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<Cliente> atualizar(@PathVariable int codigo, @RequestBody ClienteDTO clienteDTO) {
    Cliente cliente = clienteService.fromDTO(clienteDTO);
    cliente.setCodigo(codigo);
    cliente = clienteService.update(cliente);
    return ResponseEntity.ok(cliente);
  }
}