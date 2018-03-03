package uk.co.sullenart.kallingkard.injection

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import uk.co.sullenart.kallingkard.lambda.LambdaCapitaliser
import javax.inject.Singleton

@Module
class MainModule(private val context: Context) {
    @Provides
    @Singleton
    fun providesInputMethodManager() =
            context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    @Provides
    @Singleton
    fun providesLambdaCapitaliser() =
            LambdaCapitaliser(context)
}