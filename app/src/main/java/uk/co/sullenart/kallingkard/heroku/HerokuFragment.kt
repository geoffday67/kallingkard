package uk.co.sullenart.kallingkard.heroku

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
import timber.log.Timber
import uk.co.sullenart.kallingkard.BaseFragment
import uk.co.sullenart.kallingkard.MainApplication
import uk.co.sullenart.kallingkard.R
import javax.inject.Inject

class HerokuFragment : BaseFragment() {

    companion object {
        fun create() = HerokuFragment()
    }

    @Inject
    lateinit var herokuReverser: HerokuReverser

    @BindView(R.id.heroku_input)
    lateinit var herokuInput: EditText

    inner class ResultsHolder {
        @BindView(R.id.heroku_reverse_result)
        lateinit var herokuReverseResult: TextView
    }
    private val results = ResultsHolder()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        (activity.application as MainApplication).component.inject(this)

        val root = super.onCreateView(R.layout.fragment_heroku, inflater, container, savedInstanceState)
        val resultView = inflater.inflate(R.layout.heroku_result, container, false)
        progressResult.addView(resultView)
        ButterKnife.bind(results, progressResult)

        return root
    }

    @OnClick(R.id.heroku_reverse)
    fun onReverseClick() {
        val input = herokuInput.text.toString()

        if (input.isEmpty()) {
            showError(R.string.empty_error)
            hideWaiting()
            return
        }

        hideKeyboard(herokuInput)
        showWaiting()

        herokuReverser.reverse(herokuInput.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = {
                    results.herokuReverseResult.text = it ?: ""
                    showResult()
                }, onError = { showError(it.message); hideWaiting() })
    }

    @OnClick(R.id.progress_close)
    fun onCloseClick() {
        hideWaiting()
    }

    @OnClick(R.id.heroku_github)
    fun onSourceClick() {
        val uri = Uri.parse("https://github.com/geoffday67/callingcard-heroku/blob/master/index.js")
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }
}
