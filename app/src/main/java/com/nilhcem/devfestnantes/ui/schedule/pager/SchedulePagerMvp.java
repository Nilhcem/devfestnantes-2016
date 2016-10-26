package com.nilhcem.devfestnantes.ui.schedule.pager;

import com.nilhcem.devfestnantes.data.app.model.Schedule;

public interface SchedulePagerMvp {

    interface View {
        void displaySchedule(Schedule schedule);

        void displayLoadingError();
    }

    interface Presenter {
        void reloadData();
    }
}
