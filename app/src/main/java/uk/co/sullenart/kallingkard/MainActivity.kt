package uk.co.sullenart.kallingkard

import android.app.Fragment
import android.app.FragmentTransaction
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import butterknife.BindView
import uk.co.sullenart.kallingkard.heroku.HerokuFragment
import uk.co.sullenart.kallingkard.home.HomeFragment
import uk.co.sullenart.kallingkard.lambda.LambdaFragment

class MainActivity : BaseActivity(R.layout.activity_main), NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.drawer_layout)
    lateinit var drawerLayout: DrawerLayout

    @BindView(R.id.nav_contents)
    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }


        val source = BitmapFactory.decodeResource(resources, R.drawable.avatar)
        navView.getHeaderView(0).findViewById<ImageView>(R.id.nav_header_image)
                .setImageBitmap(getRoundedBitmap(source))

        navView.setNavigationItemSelectedListener(this)
        showFragment(HomeFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_phone -> phoneCall()
            R.id.action_email -> sendEmail()
            R.id.action_source -> showSource()
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    private fun sendEmail() {
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(resources.getString(R.string.geoff_email)))
            putExtra(Intent.EXTRA_SUBJECT, "Enquiry from CallingCard")
            resolveActivity(packageManager)?.let {
                startActivity(this)
            }
        }
    }

    private fun phoneCall() {
        Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:" + resources.getString(R.string.geoff_phone))
            resolveActivity(packageManager)?.let {
                startActivity(this)
            }
        }
    }

    private fun showSource() {
        val uri = Uri.parse("https://github.com/geoffday67/kallingkard/tree/master/app/src/main/java/uk/co/sullenart/kallingkard")
        Intent(Intent.ACTION_VIEW, uri).apply {
            startActivity(this)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = when (item.itemId) {
            R.id.nav_heroku -> HerokuFragment.create()
            R.id.nav_home -> HomeFragment.create()
            R.id.nav_lambda -> LambdaFragment.create()
            else -> null
        }
        fragment?.let { showFragment(it) }

        drawerLayout.closeDrawers()

        return true
    }

    private fun showFragment(fragment: Fragment) {
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.fragment_container, fragment)
                .commitAllowingStateLoss()
    }
}
