package pjs.config;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "timeslot")
public interface TimeSlotConfig {

    @WithName("duration-minutes")
    int minutes();

}