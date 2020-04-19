package com.beroutes.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.beroutes.web.rest.TestUtil;

public class TravelRouteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TravelRoute.class);
        TravelRoute travelRoute1 = new TravelRoute();
        travelRoute1.setId(1L);
        TravelRoute travelRoute2 = new TravelRoute();
        travelRoute2.setId(travelRoute1.getId());
        assertThat(travelRoute1).isEqualTo(travelRoute2);
        travelRoute2.setId(2L);
        assertThat(travelRoute1).isNotEqualTo(travelRoute2);
        travelRoute1.setId(null);
        assertThat(travelRoute1).isNotEqualTo(travelRoute2);
    }
}
