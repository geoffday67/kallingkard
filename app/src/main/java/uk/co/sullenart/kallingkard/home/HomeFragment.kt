package uk.co.sullenart.kallingkard.home

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import timber.log.Timber
import uk.co.sullenart.kallingkard.BaseFragment
import uk.co.sullenart.kallingkard.R
import uk.co.sullenart.kallingkard.getRoundedBitmap

class HomeFragment: BaseFragment() {

    companion object {
        fun create() = HomeFragment()
    }

    @BindView(R.id.home_image)
    lateinit var homeImage: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("Create home fragment")

        val view = super.onCreateView(R.layout.fragment_home, inflater, container, savedInstanceState)

        val source = BitmapFactory.decodeResource(resources, R.drawable.avatar)
        homeImage.setImageBitmap(getRoundedBitmap(source))

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        Timber.d("Destroy home fragment")
    }
}