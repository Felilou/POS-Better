package org.example.sportverein;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public abstract class AbstractRestController<E extends AbstractEntity, RDTO> {

    public abstract AbstractService<E> getService();

    @GetMapping
    public List<RDTO> getAll(){
        return toDTO(getService().getAll());
    }

    @GetMapping("/{uuid}")
    public RDTO get(@PathVariable UUID uuid){
        return toDTO(getService().getByUUID(uuid));
    }

    @PostMapping
    public RDTO save(@RequestBody CreateDTO<E> dto){
        return toDTO(getService().create(dto));
    }

    @PutMapping("/{uuid}")
    public RDTO update(@PathVariable UUID uuid, @RequestBody UpdateDTO<E> dto){
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