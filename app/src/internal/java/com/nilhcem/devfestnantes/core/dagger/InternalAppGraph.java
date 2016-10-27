package com.nilhcem.devfestnantes.core.dagger;

import com.nilhcem.devfestnantes.InternalDevFestApp;

public interface InternalAppGraph extends AppGraph {

    void inject(InternalDevFestApp app);
}
