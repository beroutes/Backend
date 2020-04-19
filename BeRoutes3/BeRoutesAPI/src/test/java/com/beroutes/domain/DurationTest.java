package com.beroutes.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.beroutes.web.rest.TestUtil;

public class DurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Duration.class);
        Duration duration1 = new Duration();
        duration1.setId(1L);
        Duration duration2 = new Duration();
        duration2.setId(duration1.getId());
        assertThat(duration1).isEqualTo(duration2);
        duration2.setId(2L);
        assertThat(duration1).isNotEqualTo(duration2);
        duration1.setId(null);
        assertThat(duration1).isNotEqualTo(duration2);
    }
}
