package uk.co.sullenart.kallingkard.heroku

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("Create Heroku fragment")

        (activity.application as MainApplication).component.inject(this)

        return super.onCreateView(R.layout.fragment_heroku, inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Timber.d("Destroy Heroku fragment")
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = { showResult(it) }, onError = { showError(it.message); hideWaiting() })
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
