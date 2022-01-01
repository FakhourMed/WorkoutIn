package com.fakhour.workoutin.workout.custom_views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import androidx.navigation.findNavController
import com.fakhour.workoutin.MainActivity
import com.fakhour.workoutin.R
import com.fakhour.workoutin.databinding.CustomHeaderBinding


class CustomHeader : RelativeLayout {
    private var _binding: CustomHeaderBinding? = null
    private val binding get() = _binding!!

    private lateinit var mContext: Context

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        mContext = context
        init(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(context, attributeSet, defStyle) {
        mContext = context
        init(attributeSet)
    }

    private fun init(attributeSet: AttributeSet) {
        _binding = CustomHeaderBinding.inflate(LayoutInflater.from(context), this, true)

        val typedArray = mContext.obtainStyledAttributes(attributeSet, R.styleable.CustomHeader, 0, 0)
        val headerTitle = typedArray.getString(R.styleable.CustomHeader_header_title)

        binding.titleTextView.text = headerTitle ?: ""


        binding.btnBack.setOnClickListener {
           findNavController().popBackStack()
        }


    }

    fun setHeaderTitleText(headerTitle: Int) {
        binding.titleTextView.setText(headerTitle)
    }

    fun setHeaderTitleText(headerTitle: String) {
        binding.titleTextView.setText(headerTitle)
    }

    fun setOnBackBtnListener(onBackBtnCLickListener: (() -> Unit)?) {
        binding.btnBack.setOnClickListener {
            onBackBtnCLickListener?.invoke()
        }
    }

    fun setHeaderBackVisibility(visibility: Int) {
        binding.btnBack.visibility = visibility
    }
}