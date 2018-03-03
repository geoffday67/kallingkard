package uk.co.sullenart.kallingkard.lambda

import android.content.Context
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory
import com.amazonaws.regions.Regions
import io.reactivex.Single
import uk.co.sullenart.kallingkard.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LambdaCapitaliser @Inject constructor(private val context: Context) {

    interface LambdaInterface {
        @LambdaFunction(functionName = "callingcard-capitals", invocationType = "RequestResponse")
        fun capitalise(input: LambdaInput): LambdaOutput
    }

    fun capitalise(input: String): Single<LambdaOutput> =
            Single.create<LambdaOutput> {
                val credentialsProvider = CognitoCachingCredentialsProvider(
                        context,
                        context.resources.getString(R.string.aws_pool_id),
                        Regions.EU_WEST_1
                )

                val factory = LambdaInvokerFactory.builder()
                        .context(context)
                        .region(Regions.EU_WEST_1)
                        .credentialsProvider(credentialsProvider)
                        .build()

                val output = factory.build(LambdaInterface::class.java)
                        .capitalise(LambdaInput(input))

                it.onSuccess(output)
            }
}

data class LambdaInput(
        val lowercase: String
)

data class LambdaOutput(
        val uppercase: String,
        val device: String
)