package com.paulotech.trasaction_api.controllers.dtos;

import java.time.OffsetDateTime;

public record TransacaoRequestDTO(Double value,
                                  OffsetDateTime dataHora) {
}
