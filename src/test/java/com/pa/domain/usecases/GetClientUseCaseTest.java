package com.pa.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.pa.adapters.in.ClientResponse;
import com.pa.adapters.out.ClientEntity;
import com.pa.adapters.out.ClientPersistenceImpl;
import com.pa.adapters.out.ClientRepository;
import com.pa.adapters.out.PersonEntity;
import com.pa.commons.ApiException;
import com.pa.commons.ErrorsEnum;
import com.pa.domain.commands.GetClientCommand;
import com.pa.domain.ports.out.ClientPersistencePort;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetClientUseCaseTest {
  @InjectMocks
  private GetClientsUseCase getClientsUseCase;

  @Mock
  private ClientRepository clientRepository;

  private ClientPersistencePort clientPersistencePort;

  @Before
  public void setup(){
    clientPersistencePort= new ClientPersistenceImpl(clientRepository);
    getClientsUseCase= new GetClientsUseCase(clientPersistencePort);
  }
  @Test
  public void getClientSuccess(){
    Integer clientId=1;
    ClientEntity client= ClientEntity.builder()
            .id(1)
            .estado(true)
            .persona(PersonEntity.builder().nombre("XXXXXX").telefono("123123").build())
            .build();
    given(clientRepository.findById(clientId)).willReturn(Optional.of(client));
    GetClientCommand command= GetClientCommand.builder().id(clientId).build();
    List<ClientResponse> response= getClientsUseCase.execute(command);
    assertNotNull(response);
    assertEquals("123123", response.get(0).getTelefono());
    assertEquals(response.size(), 1);
  }

  @Test
  public void getInvalidClientError(){
    Integer clientId=1;
    given(clientRepository.findById(clientId)).willReturn(Optional.empty());
    GetClientCommand command= GetClientCommand.builder().id(clientId).build();
    ApiException exception= assertThrows(ApiException.class, () ->getClientsUseCase.execute(command));
    assertEquals(exception.getCode(), ErrorsEnum.CLIENT_NOT_FOUND.getCode());
  }

  @Test
  public void getClientsSuccess(){
    ClientEntity client1= ClientEntity.builder()
            .id(1)
            .estado(true)
            .persona(PersonEntity.builder().nombre("XXXXXX").telefono("123123").build())
            .build();
    ClientEntity client2= ClientEntity.builder()
            .id(2)
            .estado(true)
            .persona(PersonEntity.builder().nombre("YYYYYYY").telefono("2222222").build())
            .build();
    given(clientRepository.findAll()).willReturn(List.of(client1, client2));
    List<ClientResponse> response= getClientsUseCase.execute(null);
    assertNotNull(response);
    assertEquals("123123", response.get(0).getTelefono());
    assertEquals(response.size(), 2);
  }
}
