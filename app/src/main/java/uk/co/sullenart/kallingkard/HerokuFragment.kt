package uk.co.sullenart.kallingkard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import timber.log.Timber

class HerokuFragment : BaseFragment() {

    companion object {
        fun create() = HerokuFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("Create Heroku fragment")

        val scrollView = inflater.inflate(R.layout.fragment_heroku, container, false) as ViewGroup
        ButterKnife.bind(this, scrollView)

        val containerLayout = scrollView.getChildAt(0)
        val progressLayout = inflater.inflate(R.layout.progress, container, false) as ViewGroup

        val resultLayout = inflater.inflate(R.layout.heroku_result, container, false)
        progressLayout.addView(resultLayout)


        return scrollView
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Timber.d("Destroy Heroku fragment")
    }

    @OnClick(R.id.heroku_reverse)
    fun onReverseClick() {
        Timber.d("Heroku reverse clicked")
    }

    /*
        // Add handler for the 'reverse' button
        Button button = (Button) layout.findViewById(R.id.heroku_reverse);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View button)
            {
                // Hide keyboard
                EditText input = (EditText) layout.findViewById(R.id.heroku_input);
                InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(input.getWindowToken(), 0);

                // Make the progress bar visible and the result text invisible
                final ProgressBar progress_bar = (ProgressBar) progress_layout.findViewById(R.id.progress_bar);
                progress_bar.setVisibility(View.VISIBLE);
                final TextView output = (TextView) progress_layout.findViewById((R.id.heroku_reverse_result));
                output.setVisibility(View.INVISIBLE);

                // Add progress layout to main Heroku layout if it's not already present and add 'close' handler
                if (layout.findViewById(R.id.progress_bar) == null)
                {
                    layout.addView(progress_layout, 2);

                    ((Button) layout.findViewById(R.id.progress_close)).setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View button)
                        {
                            layout.removeView(progress_layout);
                        }
                    });
                }

                // Start call to Heroku
                HerokuReverse hr = new HerokuReverse(new HerokuReverse.ReverseDoneHandler()
                {
                    @Override
                    public void onReverseDone(String result)
                    {
                        progress_bar.setVisibility(View.INVISIBLE);
                        output.setText(result);
                        output.setVisibility(View.VISIBLE);
                    }
                });
                hr.execute(input.getText().toString());
            }
        });

        // Add handler for 'source' button
        button = (Button) layout.findViewById(R.id.heroku_github);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Uri uri = Uri.parse("https://github.com/geoffday67/callingcard-heroku/blob/master/index.js");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return scroll_view;
    }
     */


}
