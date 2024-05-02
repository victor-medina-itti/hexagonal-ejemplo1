package com.pa.domain.usecases;

import com.pa.adapters.in.ClientResponse;
import com.pa.domain.commands.GetClientCommand;
import com.pa.domain.ports.in.GetClientsPort;
import com.pa.domain.ports.out.ClientPersistence;
import com.pa.domain.ports.out.ClientPersistencePort;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetClientsUseCase implements GetClientsPort {

  private final ClientPersistencePort clientPersistencePort;
  @Override
  public List<ClientResponse> execute(GetClientCommand inputCommand) {
    return Optional.ofNullable(inputCommand)
            .map(GetClientCommand::getId)
            .map(findClientById())
            .orElseGet(this::getAllClients);
  }

  private Function<Integer, List<ClientResponse>> findClientById() {
    return clientId -> Optional.ofNullable(clientPersistencePort.getClientById(clientId))
            .map(mapClientResponse())
            .map(List::of)
            .get();
  }

  private List<ClientResponse> getAllClients() {
    return Optional.ofNullable(clientPersistencePort.getClients())
            .map(mapListClientResponse())
            .get();
  }
  private Function<ClientPersistence, ClientResponse> mapClientResponse() {
    return clientPersistence -> ClientResponse.builder()
            .clientId(clientPersistence.getId())
            .nombre(clientPersistence.getNombre())
            .identificacion(clientPersistence.getIdentificacion())
            .direccion(clientPersistence.getDireccion())
            .edad(clientPersistence.getEdad())
            .telefono(clientPersistence.getTelefono())
            .genero(clientPersistence.getGenero())
            .estado(clientPersistence.getEstado())
            .build();
  }

  private Function<List<ClientPersistence>, List<ClientResponse>> mapListClientResponse() {
    return clientPersistenceList -> clientPersistenceList.stream()
            .map(mapClientResponse())
            .collect(Collectors.toList());
  }
}
