package com.hele.mrd.app.lib.common.ext

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

fun Fragment.showDialogCompat(dialog: DialogFragment){
    val transaction = childFragmentManager.beginTransaction()
    transaction.add(dialog, javaClass.simpleName)
    transaction.commitAllowingStateLoss()
}