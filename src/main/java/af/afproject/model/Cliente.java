package af.afproject.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import af.afproject.dto.ClienteDTO;

public class Cliente {
  private int codigo;
  private String nome;
  private String endereco;
  private String cpf;

  @JsonIgnore
  private List<Reserva> reservas = new ArrayList<Reserva>();

  public Cliente(ClienteDTO dto) {
    nome = dto.getNome();
    endereco = dto.getEndereco();
    cpf = dto.getCpf();
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
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
    return "Cliente [codigo=" + codigo + ", cpf=" + cpf + ", endereco=" + endereco + ", nome=" + nome + ", reservas="
        + reservas + "]";
  }
}
