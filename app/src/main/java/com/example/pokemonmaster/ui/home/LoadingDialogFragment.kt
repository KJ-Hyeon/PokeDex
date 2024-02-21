package com.example.pokemonmaster.ui.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.DialogFragment
import com.example.pokemonmaster.R
import com.example.pokemonmaster.databinding.DialogLoadingBinding

class LoadingDialogFragment: DialogFragment() {
    private val binding: DialogLoadingBinding by lazy { DialogLoadingBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.CustomAlertDialog)
        dialog.window.apply {
            isCancelable = false
        }
        return dialog
    }
}