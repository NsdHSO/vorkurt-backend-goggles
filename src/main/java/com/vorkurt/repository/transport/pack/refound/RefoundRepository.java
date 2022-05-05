package com.vorkurt.repository.transport.pack.refound;

import com.vorkurt.entity.transport.pack.refound.RefoundType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefoundRepository extends JpaRepository<RefoundType, Long>{
}
