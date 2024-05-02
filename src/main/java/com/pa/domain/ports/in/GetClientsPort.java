package com.pa.domain.ports.in;

import com.pa.adapters.in.ClientResponse;
import com.pa.domain.commands.GetClientCommand;
import com.pa.domain.ports.UseCase;
import java.util.List;

public interface GetClientsPort extends UseCase<GetClientCommand, List<ClientResponse>> {
}
