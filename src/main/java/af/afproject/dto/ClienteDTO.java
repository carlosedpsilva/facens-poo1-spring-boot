package af.afproject.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {

  @NotBlank(message = "Nome é obrigatório")
  @Length(min = 2, max = 80, message = "Nome deve ter no mínimo 2 caracteres e no máximo 80 caracteres")
  private String nome;

  @NotBlank(message = "Endereço é obrigatório")
  @Length(min = 2, max = 80, message = "Endereço deve ter no mínimo 2 caracteres e no máximo 80 caracteres")
  private String endereco;

  @NotNull(message = "CPF é obrigatório")
  @CPF
  private String cpf;

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
}