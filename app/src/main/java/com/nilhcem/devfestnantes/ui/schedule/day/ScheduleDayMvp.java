package com.nilhcem.devfestnantes.ui.schedule.day;

import com.nilhcem.devfestnantes.data.app.model.ScheduleSlot;

import java.util.List;

public interface ScheduleDayMvp {

    interface View {
        void initSlotsList(List<ScheduleSlot> slots);

        void refreshSlotsList();
    }
}
