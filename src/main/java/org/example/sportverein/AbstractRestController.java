package org.example.sportverein;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public abstract class AbstractRestController<E extends AbstractEntity, CDTO extends CreateDTO<E>, UDTO extends UpdateDTO<E>, S extends AbstractService<E>, RDTO> {

    public abstract S getService();

    @GetMapping
    public List<RDTO> getAll(){
        return toDTO(getService().getAll());
    }

    @GetMapping("/{uuid}")
    public RDTO get(@PathVariable UUID uuid){
        return toDTO(getService().getByUUID(uuid));
    }

    @PostMapping
    public RDTO save(@RequestBody CDTO dto){
        return toDTO(getService().create(dto));
    }

    @PutMapping("/{uuid}")
    public RDTO update(@PathVariable UUID uuid, @RequestBody UDTO dto){
        return toDTO(getService().updateEntity(dto, uuid));
    }

    public abstract RDTO toDTO(E entity);

    public List<RDTO> toDTO(List<E> entities){
        return entities.stream().map(this::toDTO).toList();
    }

    @DeleteMapping("/{uuid}")
    public RDTO delete(@PathVariable UUID uuid){
        return toDTO(getService().delete(uuid));
    }

}