package com.healthtrack.views.sync

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_sync_data.*
import com.healthtrack.R
import com.healthtrack.utility.Constants
import com.healthtrack.utility.LocalizationUtil


class SyncDataDialog : DialogFragment() {
    private var listener: SyncDataModeListener? = null

    interface SyncDataModeListener {
        fun syncDataWith(mode: String)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_AppCompat_Light_Dialog_Alert)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is SyncDataModeListener) {
            listener = context
        }
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.dialog_sync_data, container, false)
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_sync_data_detail.text =
            LocalizationUtil.getLocalisedString(context, R.string.sync_data_detail)
        btn_being_tested.text =
            LocalizationUtil.getLocalisedString(context, R.string.sample_collected_for_testing)
        btn_being_tested.setOnClickListener {
            listener?.syncDataWith(Constants.UPLOAD_TYPES.BEING_TESTED)
            dismissAllowingStateLoss()
        }
        btn_tested_positive.text = LocalizationUtil.getLocalisedString(context, R.string.tested_positive)
        btn_tested_positive.setOnClickListener {
            listener?.syncDataWith(Constants.UPLOAD_TYPES.TESTED_POSITIVE_CONSENT)
            dismissAllowingStateLoss()
        }
        close.setOnClickListener { dismissAllowingStateLoss() }
    }
}