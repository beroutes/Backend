package com.beroutes.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.beroutes.app.web.rest.TestUtil;

public class SeguidoresTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Seguidores.class);
        Seguidores seguidores1 = new Seguidores();
        seguidores1.setId(1L);
        Seguidores seguidores2 = new Seguidores();
        seguidores2.setId(seguidores1.getId());
        assertThat(seguidores1).isEqualTo(seguidores2);
        seguidores2.setId(2L);
        assertThat(seguidores1).isNotEqualTo(seguidores2);
        seguidores1.setId(null);
        assertThat(seguidores1).isNotEqualTo(seguidores2);
    }
}
