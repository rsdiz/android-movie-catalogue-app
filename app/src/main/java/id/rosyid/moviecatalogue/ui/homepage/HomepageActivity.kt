package id.rosyid.moviecatalogue.ui.homepage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.rosyid.moviecatalogue.databinding.ActivityHomepageBinding

class HomepageActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHomepageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomepageBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        viewBinding.viewPager.adapter = sectionsPagerAdapter
        viewBinding.tabLayout.setupWithViewPager(viewBinding.viewPager)

        actionBar?.elevation = 0f
    }
}
