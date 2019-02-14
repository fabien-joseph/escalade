package fr.fabien.escalade.business.topo;

import fr.fabien.escalade.consumer.topo.TopoRepository;
import fr.fabien.escalade.model.topo.Topo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class TopoManagement {

    //private final TopoRepository repository;

    public Iterable<Topo> findAll() {
        return null;
    }
}
