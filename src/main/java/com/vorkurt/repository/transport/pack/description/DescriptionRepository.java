package com.vorkurt.repository.transport.pack.description;

import com.vorkurt.entity.transport.pack.request.DescriptionImplementation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<DescriptionImplementation, Long> {
}
