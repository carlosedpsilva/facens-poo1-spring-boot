package af.afproject.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import af.afproject.model.Veiculo;

@Component
public class VeiculoRepository {

  public List<Veiculo> veiculos = new ArrayList<Veiculo>();

  private int nextCode = 1;

  public List<Veiculo> getAllVeiculos() {
    return veiculos;
  }

  public Optional<Veiculo> getVeiculoByCodigo(int codigo) {
    for (Veiculo veiculo : veiculos) {
      if (veiculo.getCodigo() == codigo) {
        return Optional.of(veiculo);
      }
    }
    return Optional.empty();
  }

  public Veiculo save(Veiculo veiculo) {
    veiculo.setCodigo(nextCode++);
    veiculos.add(veiculo);
    return veiculo;
  }

  public void remove(Veiculo veiculo) {
    veiculos.remove(veiculo);
  }

  public Veiculo update(Veiculo veiculo) {
    Veiculo aux = getVeiculoByCodigo((veiculo.getCodigo())).get();
    if (aux != null) {
      // Atualizar apenas modelo
      aux.setModelo(veiculo.getModelo());
    }
    return aux;
  }
}
