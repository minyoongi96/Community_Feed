package org.demo.post.domain.common;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DataTimeInfoTest {
    @Test
    void givenCreated_whenUpdated_thenTimeAndStateUpdated(){
        // given
        DateTimeInfo dataTimeInfo = new DateTimeInfo();
        LocalDateTime localDateTime = dataTimeInfo.getDateTime();

        // when
        dataTimeInfo.updateEditDateTime();

        // then
        assertNotEquals(localDateTime, dataTimeInfo.getDateTime());
    }
}
