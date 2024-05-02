package com.pa.config;

import com.pa.domain.ports.in.CreateClientPort;
import com.pa.domain.ports.in.DeleteClientPort;
import com.pa.domain.ports.in.GetClientsPort;
import com.pa.domain.ports.in.UpdateClientPort;
import com.pa.domain.ports.out.ClientPersistencePort;
import com.pa.domain.usecases.GetClientsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

  @Bean
  public GetClientsPort getClientsUseCase(ClientPersistencePort clientPersistencePort) {
    return new GetClientsUseCase(clientPersistencePort);
  }

}
