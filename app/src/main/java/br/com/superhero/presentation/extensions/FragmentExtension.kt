package br.com.superhero.presentation.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Created by João Bosco on 11/05/2023.
 */

fun Fragment.showLongToast(@StringRes textResId: Int) =
    Toast.makeText(requireContext(), textResId, Toast.LENGTH_LONG).show()