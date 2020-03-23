package com.beroutes.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.beroutes.app.web.rest.TestUtil;

public class ValoracionesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Valoraciones.class);
        Valoraciones valoraciones1 = new Valoraciones();
        valoraciones1.setId(1L);
        Valoraciones valoraciones2 = new Valoraciones();
        valoraciones2.setId(valoraciones1.getId());
        assertThat(valoraciones1).isEqualTo(valoraciones2);
        valoraciones2.setId(2L);
        assertThat(valoraciones1).isNotEqualTo(valoraciones2);
        valoraciones1.setId(null);
        assertThat(valoraciones1).isNotEqualTo(valoraciones2);
    }
}
