package ru.t_systems.alyona.sbb.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.t_systems.alyona.sbb.dto.ConnectionDTO;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ConnectionCache {

    private final Map<UUID, ConnectionDTO> connectionsById = new HashMap<>();

    public ConnectionDTO findById(UUID id) {
        return connectionsById.get(id);
    }

    public void putAll(Collection<ConnectionDTO> connections) {
        for (ConnectionDTO connection : connections) {
            connectionsById.put(connection.getId(), connection);
        }
    }
}
