package com.beroutes.app.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.beroutes.app.web.rest.TestUtil;

public class UbicacionesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ubicaciones.class);
        Ubicaciones ubicaciones1 = new Ubicaciones();
        ubicaciones1.setId(1L);
        Ubicaciones ubicaciones2 = new Ubicaciones();
        ubicaciones2.setId(ubicaciones1.getId());
        assertThat(ubicaciones1).isEqualTo(ubicaciones2);
        ubicaciones2.setId(2L);
        assertThat(ubicaciones1).isNotEqualTo(ubicaciones2);
        ubicaciones1.setId(null);
        assertThat(ubicaciones1).isNotEqualTo(ubicaciones2);
    }
}
