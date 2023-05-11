package me.ndkshr.giki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

class ResultFragment(private var searchEngine: String): Fragment() {

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initWebView(view)
        arguments?.takeIf { it.containsKey(SEARCH_WORD) }?.apply {
            updateWebView(get(SEARCH_WORD).toString())
        }
    }

    private fun initWebView(view: View) {
        webView = view.findViewById(R.id.result_web_view)
        webView.isVerticalScrollBarEnabled = true
        webView.isHorizontalScrollBarEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        webView.settings.javaScriptEnabled = true
        updateWebView("")
    }

    fun updateWebView(word: String) {
        if(this::webView.isInitialized) {
            if (word.isNotBlank())
                webView.loadUrl("https://www.${searchEngine}.com/search?q=${word}+define")
            else
                webView.loadUrl("https://ndkshr.github.io/Giki/")
        }
    }

    companion object {
        const val SEARCH_WORD = "result_url"
        const val SEARCH_ENGINE = "search_engine"
    }

}