package uk.co.sullenart.kallingkard

import android.app.Application
import timber.log.Timber
import uk.co.sullenart.kallingkard.injection.DaggerMainComponent
import uk.co.sullenart.kallingkard.injection.MainComponent
import uk.co.sullenart.kallingkard.injection.MainModule

class MainApplication : Application() {
    lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        //Stetho.initializeWithDefaults(this);

        component = DaggerMainComponent.builder()
                .mainModule(MainModule(this))
                .build()
    }
}
