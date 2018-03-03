package uk.co.sullenart.kallingkard.injection

import dagger.Component
import uk.co.sullenart.kallingkard.BaseFragment
import uk.co.sullenart.kallingkard.heroku.HerokuFragment
import uk.co.sullenart.kallingkard.lambda.LambdaFragment
import javax.inject.Singleton

@Component(modules = [MainModule::class])
@Singleton
interface MainComponent {
    fun inject(lambdaFragment: LambdaFragment)
    fun inject(herokuFragment: HerokuFragment)
    fun inject(baseFragment: BaseFragment)
}