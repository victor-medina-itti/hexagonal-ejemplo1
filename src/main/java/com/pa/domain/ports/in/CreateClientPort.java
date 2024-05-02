package com.pa.domain.ports.in;

import com.pa.adapters.in.NewClientRequest;
import com.pa.adapters.in.ClientResponse;
import com.pa.domain.ports.UseCase;

public interface CreateClientPort extends UseCase<NewClientRequest, ClientResponse> {
}
