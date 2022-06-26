package dk.ubaya.adv160819001finaltermproject.view

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dk.ubaya.adv160819001finaltermproject.R
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentBookDetailBinding
import dk.ubaya.adv160819001finaltermproject.databinding.FragmentMoreBookDetailBinding
import dk.ubaya.adv160819001finaltermproject.model.Book
import dk.ubaya.adv160819001finaltermproject.util.LibraryWorker
import dk.ubaya.adv160819001finaltermproject.util.NotificationHelper
import kotlinx.android.synthetic.main.fragment_more_book_detail.view.*
import java.util.*
import java.util.concurrent.TimeUnit

class MoreBookDetailFragment : BottomSheetDialogFragment(),DateClickListener,TimeClickListener,
    RequestBookClickListener,DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    private lateinit var dataBinding:FragmentMoreBookDetailBinding
    var year=0
    var month=0
    var day=0
    var hour=0
    var minute=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate<FragmentMoreBookDetailBinding>(inflater,R.layout.fragment_more_book_detail,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.book= Book("","","","","","",MoreBookDetailFragmentArgs.fromBundle(requireArguments()).location,"")
        dataBinding.dateListener=this
        dataBinding.timeListener=this
        dataBinding.listener=this
    }

    override fun onDateClick(v: View) {
        val c=Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        activity?.let { DatePickerDialog(it,this,year,month,day).show() }
    }

    override fun onTimeClick(v: View) {
        val c=Calendar.getInstance()
        val hour=c.get(Calendar.HOUR_OF_DAY)
        val minute=c.get(Calendar.MINUTE)
        TimePickerDialog(activity,this,hour,minute,DateFormat.is24HourFormat(activity)).show()
    }

    override fun onRequestBookClick(v: View) {
        val c=Calendar.getInstance()
        c.set(year,month,day,hour,minute)

        val now=Calendar.getInstance()
        val diff=(c.timeInMillis/1000L)-(now.timeInMillis/1000L)

//        this.context?.let { NotificationHelper(it).createNotification("Ubaya Library : Noted!","We have receive your request. We will notify you to take the book at the time you requested.") }

        val workRequest = OneTimeWorkRequestBuilder<LibraryWorker>()
            .setInitialDelay(5, TimeUnit.SECONDS)
            .setInputData(workDataOf("title" to "Ubaya Library : Noted!","message" to "We have receive your request. We will notify you to take the book at the time you requested."))
            .build()
        WorkManager.getInstance(requireContext()).enqueue(workRequest)
        val workRequest2 = OneTimeWorkRequestBuilder<LibraryWorker>()
            .setInitialDelay(diff, TimeUnit.SECONDS)
            .setInputData(workDataOf("title" to "Ubaya Library : Your book is ready!","message" to "Yeay! You can take the book now at ${MoreBookDetailFragmentArgs.fromBundle(requireArguments()).location}"))
            .build()
        WorkManager.getInstance(requireContext()).enqueue(workRequest2)
        Log.e("diff",diff.toString())
        this.dismiss()
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        Calendar.getInstance().let {
            it.set(year,month,day)
            dataBinding.root.txtDate.setText(day.toString().padStart(2,'0')+"/"+(month+1).toString().padStart(2,'0')+"/"+year)
            this.year=year
            this.month=month
            this.day=day
        }
    }

    override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {
        dataBinding.root.txtTime.setText(hour.toString().padStart(2,'0')+":"+minute.toString().padStart(2,'0'))
        this.hour=hour
        this.minute=minute
    }
}