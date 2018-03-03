package uk.co.sullenart.kallingkard.lambda

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import uk.co.sullenart.kallingkard.BaseFragment
import uk.co.sullenart.kallingkard.MainApplication
import uk.co.sullenart.kallingkard.R
import javax.inject.Inject

class LambdaFragment : BaseFragment() {

    companion object {
        fun create() = LambdaFragment()
    }

    @Inject
    lateinit var lambdaCapitaliser: LambdaCapitaliser

    @BindView(R.id.lambda_input)
    lateinit var lambdaInput: EditText

    inner class ResultsHolder {
        @BindView(R.id.lambda_capitalise_result)
        lateinit var lambdaCapitaliseResult: TextView

        @BindView(R.id.lambda_device_result)
        lateinit var lambdaDeviceResult: TextView
    }

    private val results = ResultsHolder()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity.application as MainApplication).component.inject(this)

        val root = super.onCreateView(R.layout.fragment_lambda, inflater, container, savedInstanceState)
        val resultView = inflater.inflate(R.layout.lambda_result, container, false)
        progressResult.addView(resultView)
        ButterKnife.bind(results, progressResult)

        return root
    }

    @OnClick(R.id.lambda_capitalise)
    fun onCapitaliseClick() {

        val input = lambdaInput.text.toString()

        if (input.isEmpty()) {
            showError(R.string.empty_error)
            hideWaiting()
            return
        }

        hideKeyboard(lambdaInput)
        showWaiting()

        lambdaCapitaliser.capitalise(input)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = {
                    results.lambdaCapitaliseResult.text = it.uppercase
                    results.lambdaDeviceResult.text = resources.getString(R.string.device_name, it.device)
                    showResult()
                }, onError = { showError(it.message); hideWaiting() })
    }

    @OnClick(R.id.progress_close)
    fun onCloseClick() {
        hideWaiting()
    }

    @OnClick(R.id.lambda_github)
    fun onSourceClick() {
        val uri = Uri.parse("https://github.com/geoffday67/callingcard-lambda/blob/master/capitalise.js")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}
