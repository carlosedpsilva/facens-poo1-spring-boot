package af.afproject.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Component;

import af.afproject.model.Veiculo;

@Component
public class VeiculoRepository implements IRepository<Veiculo> {

  public ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

  private int nextCode = 1;

  @Override
  public ArrayList<Veiculo> getAll() {
    return veiculos;
  }

  @Override
  public Optional<Veiculo> getByCodigo(int codigo) {
    for (Veiculo veiculo : veiculos) {
      if (veiculo.getCodigo() == codigo) {
        return Optional.of(veiculo);
      }
    }
    return Optional.empty();
  }

  @Override
  public Veiculo save(Veiculo veiculo) {
    veiculo.setCodigo(nextCode++);
    veiculos.add(veiculo);
    return veiculo;
  }

  @Override
  public void remove(Veiculo veiculo) {
    veiculos.remove(veiculo);
  }

  @Override
  public Veiculo update(Veiculo veiculo) {
    Veiculo aux = getByCodigo((veiculo.getCodigo())).get();
    if (aux != null) {
      // Atualizar apenas modelo
      aux.setModelo(veiculo.getModelo());
    }
    return aux;
  }
}
