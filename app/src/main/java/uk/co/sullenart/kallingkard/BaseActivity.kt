package uk.co.sullenart.kallingkard

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife

@SuppressLint("Registered")
open class BaseActivity(@LayoutRes private val content: Int) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(content)
        ButterKnife.bind(this)
    }
}