package com.pa.domain.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetClientCommand {
    private String name;
    private Integer id;
}
