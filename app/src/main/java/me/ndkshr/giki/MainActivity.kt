package me.ndkshr.giki

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    lateinit var input: TextInputEditText
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    private val pagerAdapter = ResultViewPagerAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById(R.id.input)
        val inputLayout: TextInputLayout = findViewById(R.id.input_field)
//        inputLayout.setStartIconDrawable(R.drawable.giki_text)
        viewPager = findViewById(R.id.tab_viewpager)
        tabLayout = findViewById(R.id.tabs)

        inputLayout.setEndIconOnClickListener {
            val word = input.text.toString()
            if (word.isNotBlank())
                pagerAdapter.updateAll(word)
            else
                Toast.makeText(applicationContext, "Invalid input", Toast.LENGTH_LONG).show()

            if (it != null) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }

        viewPager.adapter = pagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = SearchEngineTypes.getName(position)
        }.attach()
    }

    companion object {
        const val HOME_HTML = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Bootstrap Example</title>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js\"></script>\n" +
                "    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "\n" +
                "    <div class=\"jumbotron\" style=\"text-align:center; margin:auto; background-image: url('https://t4.ftcdn.net/jpg/01/79/07/77/360_F_179077796_P6UNHX6KBpb1hITJSF1gN173qw4GF98U.jpg');\">\n" +
                "        <h1 style=\"color:white;\"><strong>Giki</strong></h1>\n" +
                "        <p style=\"color:white;\">Lightweight Dictionary</p>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n"
    }
}