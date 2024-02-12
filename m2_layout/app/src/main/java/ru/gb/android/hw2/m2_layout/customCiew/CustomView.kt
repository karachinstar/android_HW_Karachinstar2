package ru.gb.android.hw2.m2_layout.customCiew

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import ru.gb.android.hw2.m2_layout.R
import ru.gb.android.hw2.m2_layout.databinding.CustomViewBinding


class CustomView @JvmOverloads constructor(context: Context, attributeSet: AttributeSet? = null) :
    LinearLayout(context, attributeSet) {
    private var binding: CustomViewBinding
    init {
        val inflatedView = inflate(context, R.layout.custom_view, this)
        binding = CustomViewBinding.bind(inflatedView)
    }

    fun setMessageTopText(text: String) {
        binding.topText.text = text
    }

    fun setMessageBottomText(text: String) {
        binding.bottomText.text = text
    }
}