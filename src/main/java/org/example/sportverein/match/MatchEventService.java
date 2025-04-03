package org.example.sportverein.match;

import lombok.RequiredArgsConstructor;
import org.example.sportverein.AbstractService;
import org.example.sportverein.UUIDRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchEventService extends AbstractService<MatchEvent> {

    private final MatchEventRepository matchEventRepository;

    @Override
    public UUIDRepository<MatchEvent> getUUIDRepository() {
        return matchEventRepository;
    }

}
