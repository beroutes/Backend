package com.beroutes.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.beroutes.app.web.rest.TestUtil;

public class RutasTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rutas.class);
        Rutas rutas1 = new Rutas();
        rutas1.setId(1L);
        Rutas rutas2 = new Rutas();
        rutas2.setId(rutas1.getId());
        assertThat(rutas1).isEqualTo(rutas2);
        rutas2.setId(2L);
        assertThat(rutas1).isNotEqualTo(rutas2);
        rutas1.setId(null);
        assertThat(rutas1).isNotEqualTo(rutas2);
    }
}
