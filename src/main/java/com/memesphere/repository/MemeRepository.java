package com.memesphere.repository;

import com.memesphere.domain.MemeCoin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeRepository extends JpaRepository<MemeCoin, Long> {
    @EntityGraph(attributePaths = {"chartData"})
    Page<MemeCoin> findByNameContainingIgnoreCaseOrSymbolContainingIgnoreCaseOrKeywordsContainingIgnoreCase(String name, String symbol, String keyword, Pageable pageable);
}
