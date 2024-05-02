package com.pa.adapters.out.config;

import com.pa.adapters.out.ClientPersistenceImpl;
import com.pa.adapters.out.ClientRepository;
import com.pa.domain.ports.out.ClientPersistencePort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientPersistenceConfig {
  @Bean
  public ClientPersistencePort createClientPersistenceImpl(ClientRepository clientRepository) {
    return new ClientPersistenceImpl(clientRepository);
  }
}
