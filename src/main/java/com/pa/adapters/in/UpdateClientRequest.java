package com.pa.adapters.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateClientRequest {

  private Integer clientId;
  @NotBlank
  private String nombre;
  @NotBlank
  private String genero;
  @NotNull
  private Integer edad;
  @NotBlank
  private String identificacion;
  private String direccion;
  private String telefono;
  private Boolean estado;
  private String password;

}
