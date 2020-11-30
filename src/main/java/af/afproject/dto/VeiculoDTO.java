package af.afproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

public class VeiculoDTO {

  @NotBlank(message = "Modelo é obrigatório")
  @Length(min = 2, max = 80, message = "Modelo deve ter no mínimo 2 caracteres e no máximo 80 caracteres")
  private String modelo;

  @NotBlank(message = "Valor é obrigatório")
  @Positive(message = "Valor deve ser positivo diferente de zero")
  private double valor;

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }
}
