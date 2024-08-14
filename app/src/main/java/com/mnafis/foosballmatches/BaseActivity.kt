package com.mnafis.foosballmatches

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    fun setToolbarNavigationIcon(toolbarNavigationIcon: ToolbarNavigationIcon, onClickListener: View.OnClickListener) {
        val icon = when(toolbarNavigationIcon) {
            ToolbarNavigationIcon.LOGO -> R.mipmap.ic_launcher
            ToolbarNavigationIcon.BACK -> R.drawable.ic_button_back
        }
        val toolbarIcon: ImageView = findViewById(R.id.toolbar_start_logo)
        toolbarIcon.setImageResource(icon)
        toolbarIcon.layoutParams.height = 90
        toolbarIcon.layoutParams.width = 90
        toolbarIcon.setPadding(0,0,15,0)
        toolbarIcon.setOnClickListener(onClickListener)
        toolbarIcon.visibility = View.VISIBLE
    }

    fun setToolbarTitle(@StringRes title: Int) {
        val toolBarTitle: TextView? = findViewById(R.id.toolbar_title)
        toolBarTitle?.text = getString(title)
    }

    fun setToolbarTrailerIcon(toolbarTrailerIcon: ToolbarTrailerIcon, onClickListener: View.OnClickListener) {
        resetToolbarTrailerIcon()
        val icon = when(toolbarTrailerIcon) {
            ToolbarTrailerIcon.ADD -> R.drawable.ic_button_add
            ToolbarTrailerIcon.SORT -> R.drawable.ic_button_sort
            ToolbarTrailerIcon.DELETE -> R.drawable.ic_delete
        }
        val toolbarIcon: ImageView = findViewById(R.id.toolbar_end_logo)
        toolbarIcon.setImageResource(icon)
        toolbarIcon.layoutParams.height = 90
        toolbarIcon.layoutParams.width = 90
        toolbarIcon.setPadding(0,0,15,0)
        toolbarIcon.setOnClickListener(onClickListener)
        toolbarIcon.visibility = View.VISIBLE
    }

    fun resetToolbarTrailerIcon() {
        val toolbarIcon: ImageView = findViewById(R.id.toolbar_end_logo)
        toolbarIcon.setOnClickListener {}
        toolbarIcon.visibility = View.GONE
    }
}

enum class ToolbarTrailerIcon {
    ADD, SORT, DELETE
}

enum class ToolbarNavigationIcon {
    LOGO, BACK
}