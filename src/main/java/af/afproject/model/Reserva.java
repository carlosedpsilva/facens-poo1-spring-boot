package af.afproject.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

import af.afproject.dto.ReservaDTO;

public class Reserva {
  private int codigo;
  private Cliente cliente;
  private Veiculo veiculo;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataInicio;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataFim;

  public Reserva(ReservaDTO dto) {
    dataInicio = dto.getDataInicio();
    dataFim = dto.getDataFim();
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Veiculo getVeiculo() {
    return veiculo;
  }

  public void setVeiculo(Veiculo veiculo) {
    this.veiculo = veiculo;
  }

  public LocalDate getDataInicio() {
    return dataInicio;
  }

  public void setDataInicio(LocalDate dataInicio) {
    this.dataInicio = dataInicio;
  }

  public LocalDate getDataFim() {
    return dataFim;
  }

  public void setDataFim(LocalDate dataFim) {
    this.dataFim = dataFim;
  }

  @JsonGetter
  public double totalReserva() {
    return veiculo.getValor() * dataInicio.until(dataFim, ChronoUnit.DAYS);
  }

  @Override
  public String toString() {
    return "Reserva [cliente=" + cliente.getCodigo() + ", codigo=" + codigo + ", dataFim=" + dataFim + ", dataInicio="
        + dataInicio + ", veiculo=" + veiculo.getCodigo() + "]";
  }
}
