package com.nilhcem.devfestnantes.ui.settings;

import android.content.Context;

import com.nilhcem.devfestnantes.receiver.BootReceiver;
import com.nilhcem.devfestnantes.receiver.reminder.SessionsReminder;
import com.nilhcem.devfestnantes.ui.BasePresenter;
import com.nilhcem.devfestnantes.utils.App;

public class SettingsPresenter extends BasePresenter<SettingsMvp.View> implements SettingsMvp.Presenter {

    private final Context context;
    private final SessionsReminder sessionsReminder;

    public SettingsPresenter(Context context, SettingsMvp.View view, SessionsReminder sessionsReminder) {
        super(view);
        this.context = context;
        this.sessionsReminder = sessionsReminder;
    }

    @Override
    public void onCreate() {
        view.setAppVersion(App.getVersion());
    }

    @Override
    public boolean onNotifySessionsChange(boolean checked) {
        view.setNotifySessionsCheckbox(checked);

        if (checked) {
            sessionsReminder.enableSessionReminder();
            BootReceiver.enable(context);
        } else {
            sessionsReminder.disableSessionReminder();
            BootReceiver.disable(context);
        }
        return true;
    }
}
