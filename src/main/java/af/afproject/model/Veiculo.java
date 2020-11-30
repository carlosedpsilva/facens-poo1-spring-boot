package af.afproject.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import af.afproject.dto.VeiculoDTO;

public class Veiculo {
  private int codigo;
  private String modelo;
  private double valor;

  @JsonIgnore
  private List<Reserva> reservas = new ArrayList<Reserva>();

  public Veiculo(VeiculoDTO dto) {
    modelo = dto.getModelo();
    valor = dto.getValor();
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

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

  public List<Reserva> getReservas() {
    return reservas;
  }

  public boolean addReserva(Reserva reserva) {
    return reservas.add(reserva);
  }

  public boolean removeReserva(Reserva reserva) {
    return reservas.remove(reserva);
  }

  @Override
  public String toString() {
    return "Veiculo [codigo=" + codigo + ", modelo=" + modelo + ", reservas=" + reservas + ", valor=" + valor + "]";
  }
}
