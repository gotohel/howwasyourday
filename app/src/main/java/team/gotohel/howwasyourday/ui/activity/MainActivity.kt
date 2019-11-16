package team.gotohel.howwasyourday.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_main.*
import team.gotohel.howwasyourday.MyPreference
import team.gotohel.howwasyourday.R
import team.gotohel.howwasyourday.toast

class MainActivity : AppCompatActivity() {

    private lateinit var sheetBehavior: BottomSheetBehavior<FrameLayout>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sheetBehavior = BottomSheetBehavior.from(bottom_sheet)

        // callback for do something
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, newState: Int) {

            }

            override fun onSlide(view: View, v: Float) {
                bg_bottom_sheet.alpha = v
                if (v > 0) {
                    bg_bottom_sheet.visibility = View.VISIBLE
                } else {
                    bg_bottom_sheet.visibility = View.GONE
                }
            }
        })
    }

    fun logout(view: View) {
        MyPreference.savedUserId = null
        MyPreference.savedUserName = null
        MyPreference.stayLogin = false

        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun showChatList(view: View) {
        startActivity(Intent(this, ChatListActivity::class.java))

    }


    fun showBottomSheet(view: View) {
        sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun hideBottomSheet(view: View) {
        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun showSubMenu(view: View) {
        val titleChatList = "Chat List"
        val titleHelp = SpannableString("Help to doctor")
        titleHelp.setSpan(ForegroundColorSpan(Color.parseColor("#E55555")), 0, titleHelp.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        titleHelp.setSpan(TypefaceSpan(resources.getFont(R.font.koho_bold)), 0, titleHelp.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        PopupMenu(this, view).apply {
            menu.add(titleChatList)
            menu.add(titleHelp)
            setOnMenuItemClickListener {
                when (it.title) {
                    titleChatList -> {
                        toast(titleChatList)
                    }
                    titleHelp -> {
                        toast(titleHelp.toString())
                    }
                }

                return@setOnMenuItemClickListener true
            }
        }.show()
    }

    fun justSave(view: View) {

    }

    fun saveAndPublish(view: View) {

    }
}
