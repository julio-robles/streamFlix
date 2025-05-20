package com.example.streamflix.playlist;
import com.example.streamflix.playlist.domain.Playlist;
import com.example.streamflix.playlist.repository.PlaylistRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;
import org.springframework.test.annotation.Commit;

import java.time.OffsetDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlaylistRepositoryIT {

    @Autowired PlaylistRepository repo;

    @BeforeEach
    void seed() {
        UUID user = UUID.randomUUID();
        OffsetDateTime now = OffsetDateTime.now();

        repo.save(new Playlist(user, "Big",     5, now));
        repo.save(new Playlist(user, "Medium",  3, now.minusMinutes(1)));
        repo.save(new Playlist(user, "Empty",   0, now.minusMinutes(2)));
    }

    @Test
    void encuentraPlaylistsGrandes() {
        List<Playlist> result = repo.findWithMoreThan(2, Sort.unsorted());

        assertThat(result).extracting(Playlist::getItemCount)
                .containsExactly(5, 3);      // orden descendente
    }

    @Test
    @Commit     // ejecuta realmente el DELETE (no se revierte al final)
    void borraPlaylistsVacias() {
        int deleted = repo.deleteEmptyPlaylists();
        assertThat(deleted).isEqualTo(1);
        assertThat(repo.count()).isEqualTo(2);
    }
}