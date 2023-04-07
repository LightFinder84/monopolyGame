package com.development.Monopoly.repository;

import com.development.Monopoly.entity.Square;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquareRepository extends JpaRepository<Square, Long> {
}
