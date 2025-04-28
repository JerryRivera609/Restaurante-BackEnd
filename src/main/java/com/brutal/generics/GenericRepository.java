package com.brutal.generics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository <T extends BaseEntity, Long> extends JpaRepository <T, Long> {
}
