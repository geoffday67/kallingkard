package uk.co.sullenart.kallingkard

import android.app.Application
import timber.log.Timber

class MainApplication : Application() {
    //lateinit var component: DataComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        /*Stetho.initializeWithDefaults(this);

        component = DaggerDataComponent.builder()
                .dataModule(DataModule(this))
                .build()*/
    }
}
