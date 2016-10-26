package com.nilhcem.devfestnantes.core.dagger;

import com.nilhcem.devfestnantes.receiver.BootReceiver;
import com.nilhcem.devfestnantes.receiver.reminder.ReminderReceiver;
import com.nilhcem.devfestnantes.ui.drawer.DrawerActivity;
import com.nilhcem.devfestnantes.ui.schedule.day.ScheduleDayFragment;
import com.nilhcem.devfestnantes.ui.schedule.pager.SchedulePagerFragment;
import com.nilhcem.devfestnantes.ui.sessions.details.SessionDetailsActivity;
import com.nilhcem.devfestnantes.ui.sessions.list.SessionsListActivity;
import com.nilhcem.devfestnantes.ui.settings.SettingsFragment;
import com.nilhcem.devfestnantes.ui.speakers.details.SpeakerDetailsDialogFragment;
import com.nilhcem.devfestnantes.ui.speakers.list.SpeakersListFragment;

/**
 * A common interface implemented by both the internal and production flavored components.
 */
public interface AppGraph {

    void inject(DrawerActivity activity);

    void inject(SchedulePagerFragment fragment);

    void inject(ScheduleDayFragment fragment);

    void inject(SessionsListActivity activity);

    void inject(SpeakersListFragment fragments);

    void inject(SessionDetailsActivity activity);

    void inject(SpeakerDetailsDialogFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(BootReceiver receiver);

    void inject(ReminderReceiver receiver);
}
