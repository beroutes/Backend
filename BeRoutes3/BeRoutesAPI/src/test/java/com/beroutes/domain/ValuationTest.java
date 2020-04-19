package com.beroutes.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.beroutes.web.rest.TestUtil;

public class ValuationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Valuation.class);
        Valuation valuation1 = new Valuation();
        valuation1.setId(1L);
        Valuation valuation2 = new Valuation();
        valuation2.setId(valuation1.getId());
        assertThat(valuation1).isEqualTo(valuation2);
        valuation2.setId(2L);
        assertThat(valuation1).isNotEqualTo(valuation2);
        valuation1.setId(null);
        assertThat(valuation1).isNotEqualTo(valuation2);
    }
}
