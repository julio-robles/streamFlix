package com.example.streamflix.playlist.repository;

import com.example.streamflix.playlist.domain.Playlist;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface PlaylistRepository extends JpaRepository<Playlist, UUID> {

    // existentes…
    Page<Playlist> findByUserId(UUID userId, Pageable pageable);
    boolean existsByUserIdAndNameIgnoreCase(UUID userId, String name);
    List<Playlist> findTop3ByUserIdOrderByCreatedAtDesc(UUID userId);

    // ─────────── NUEVOS ───────────

    /** Playlists con más de :min elementos, ordenadas desc por itemCount */
    @Query("""
           SELECT p FROM Playlist p
           WHERE p.itemCount > :min
           ORDER BY p.itemCount DESC
           """)
    List<Playlist> findWithMoreThan(@Param("min") int min, Sort sort);

    /** Borra todas las playlists vacías y devuelve filas afectadas */
    @Modifying
    @Query("DELETE FROM Playlist p WHERE p.itemCount = 0")
    int deleteEmptyPlaylists();
}
