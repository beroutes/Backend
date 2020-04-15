package com.beroutes.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.beroutes.app.web.rest.TestUtil;

public class FotosTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fotos.class);
        Fotos fotos1 = new Fotos();
        fotos1.setId(1L);
        Fotos fotos2 = new Fotos();
        fotos2.setId(fotos1.getId());
        assertThat(fotos1).isEqualTo(fotos2);
        fotos2.setId(2L);
        assertThat(fotos1).isNotEqualTo(fotos2);
        fotos1.setId(null);
        assertThat(fotos1).isNotEqualTo(fotos2);
    }
}
