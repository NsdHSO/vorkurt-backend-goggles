package com.vorkurt.repository.transport.pack;

import com.vorkurt.entity.transport.pack.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackRepository extends JpaRepository<Pack, Long> {
}
