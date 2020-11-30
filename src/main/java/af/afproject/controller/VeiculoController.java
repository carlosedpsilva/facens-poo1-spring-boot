package af.afproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import af.afproject.dto.ReservaDTO;
import af.afproject.dto.VeiculoDTO;
import af.afproject.model.Veiculo;
import af.afproject.service.ReservaService;
import af.afproject.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  @Autowired
  private VeiculoService veiculoService;

  @Autowired
  private ReservaService reservaService;

  @GetMapping
  public List<Veiculo> getVeiculos() {
    return veiculoService.getAllVeiculos();
  }

  @GetMapping("/{codigo}")
  public ResponseEntity<Veiculo> getVeiculo(@PathVariable int codigo) {
    Veiculo veiculo = veiculoService.getVeiculoByCodigo(codigo);
    return ResponseEntity.ok(veiculo);
  }

  @GetMapping("/{codigo}/reservas")
  public List<ReservaDTO> getClientesVeiculo(@PathVariable int codigo) {
    Veiculo veiculo = veiculoService.getVeiculoByCodigo(codigo);
    return reservaService.toListDTO(veiculo.getReservas());
  }

  @PostMapping
  public ResponseEntity<Void> salvar(@RequestBody VeiculoDTO veiculoDTO, HttpServletRequest request,
      UriComponentsBuilder builder) {
    Veiculo veiculo = veiculoService.save(veiculoService.fromDTO(veiculoDTO));
    UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + veiculo.getCodigo()).build();
    return ResponseEntity.created(uriComponents.toUri()).build();
  }

  @DeleteMapping("/{codigo}")
  public ResponseEntity<Void> remover(@PathVariable int codigo) {
    veiculoService.removeByCodigo(codigo);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/{codigo}")
  public ResponseEntity<Veiculo> atualizar(@PathVariable int codigo, @RequestBody VeiculoDTO veiculoDTO) {
    Veiculo veiculo = veiculoService.fromDTO(veiculoDTO);
    veiculo.setCodigo(codigo);
    veiculo = veiculoService.update(veiculo);
    return ResponseEntity.ok(veiculo);
  }
}
