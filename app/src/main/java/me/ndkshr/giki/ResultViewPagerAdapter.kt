package me.ndkshr.giki

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import me.ndkshr.giki.ResultFragment.Companion.SEARCH_WORD

class ResultViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    private val googleFragment = ResultFragment(SearchEngineTypes.Google.domain)
    private val bingFragment = ResultFragment(SearchEngineTypes.Bing.domain)

    var fragmentList = mutableListOf<Fragment>(bingFragment, googleFragment)
        private set

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        fragmentList[position].arguments = Bundle().apply {
            putString(SEARCH_WORD, "")
        }
        return fragmentList[position]
    }

    fun updateAll(word: String) {
        fragmentList.forEach {
            (it as ResultFragment).updateWebView(word)
        }
    }
}