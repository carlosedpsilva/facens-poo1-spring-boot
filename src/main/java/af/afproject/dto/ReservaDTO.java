package af.afproject.dto;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservaDTO {
  private int codigo;

  @NotNull
  @Future(message = "Data de Início deve ser após a data de hoje")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataInicio;

  @NotNull
  @Future(message = "Data de Fim deve ser após a data de hoje")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataFim;

  private double totalReserva;

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
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

  public double getTotalReserva() {
    return totalReserva;
  }

  public void setTotalReserva(double totalReserva) {
    this.totalReserva = totalReserva;
  }
}
