package uk.co.sullenart.kallingkard.injection

import dagger.Component
import uk.co.sullenart.kallingkard.BaseFragment
import uk.co.sullenart.kallingkard.HerokuFragment
import javax.inject.Singleton

@Component(modules = [MainModule::class])
@Singleton
interface MainComponent {
    fun inject(herokuFragment: HerokuFragment)
    fun inject(baseFragment: BaseFragment)
}