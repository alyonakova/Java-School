package ru.t_systems.alyona.sbb.service;

import ru.t_systems.alyona.sbb.dto.ConnectionSearchQueryDTO;
import ru.t_systems.alyona.sbb.dto.ConnectionSearchResultDTO;

public interface ConnectionSearchService {

    ConnectionSearchResultDTO findConnections(ConnectionSearchQueryDTO request);
}
