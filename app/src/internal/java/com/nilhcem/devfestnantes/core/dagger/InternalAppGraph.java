package com.nilhcem.devfestnantes.core.dagger;

import com.nilhcem.devfestnantes.InternalMobilizationApp;

public interface InternalAppGraph extends AppGraph {

    void inject(InternalMobilizationApp app);
}
