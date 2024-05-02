package com.pa.domain.ports.in;

import com.pa.adapters.in.ClientResponse;
import com.pa.adapters.in.UpdateClientRequest;
import com.pa.domain.ports.UseCase;

public interface UpdateClientPort extends UseCase<UpdateClientRequest, ClientResponse> {
}
