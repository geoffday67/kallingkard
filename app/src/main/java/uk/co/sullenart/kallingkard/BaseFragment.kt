package uk.co.sullenart.kallingkard

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import javax.inject.Inject

open class BaseFragment : Fragment() {
    protected lateinit var rootView: View

    @Inject
    lateinit var inputMethodManager: InputMethodManager

    @BindView(R.id.progress_layout)
    lateinit var progressLayout: ViewGroup

    @BindView(R.id.progress_bar)
    lateinit var progressBar: ProgressBar

    @BindView(R.id.progress_result)
    lateinit var resultText: TextView

    fun onCreateView(contentId: Int, inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        (activity.application as MainApplication).component.inject(this)

        rootView = inflater.inflate(contentId, container, false) as ViewGroup
        ButterKnife.bind(this, rootView)
        return rootView
    }

    protected fun showWaiting() {
        progressBar.visibility = View.VISIBLE
        resultText.visibility = View.INVISIBLE
        progressLayout.visibility = View.VISIBLE
    }

    protected fun showResult(result: String) {
        resultText.text = result
        resultText.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    protected fun hideWaiting() {
        progressLayout.visibility = View.GONE
    }

    protected fun showError(text: String?) {
        Toast.makeText(activity, text ?: "", Toast.LENGTH_LONG).show()
    }

    protected fun showError(id: Int) {
        showError(activity.resources.getString(id))
    }

    protected fun hideKeyboard(view: View) {
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}