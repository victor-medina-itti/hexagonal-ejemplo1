package com.pa.domain.ports;

public interface UseCase<I,O> {
  O execute (I inputCommand);
}
