//package com.example.percobaan4.ui.medical
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import com.example.percobaan4.databinding.FragmentMedicalHistoryBinding
//
//class MedicalHistoryFragment : Fragment() {
//
//    private var _binding: FragmentMedicalHistoryBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val medicalHistoryViewModel =
//            ViewModelProvider(this).get(MedicalHistoryViewModel::class.java)
//
//        _binding = FragmentMedicalHistoryBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textMedicalHistory
//        medicalHistoryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}
package com.example.percobaan4.ui.medical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.percobaan4.databinding.FragmentMedicalHistoryBinding
import java.text.SimpleDateFormat
import java.util.*

class MedicalHistoryFragment : Fragment() {

    private var _binding: FragmentMedicalHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val medicalHistoryViewModel =
            ViewModelProvider(this).get(MedicalHistoryViewModel::class.java)

        _binding = FragmentMedicalHistoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Mengatur heading text
        medicalHistoryViewModel.text.observe(viewLifecycleOwner) {
            binding.textMedicalHistory.text = it
        }

        // Setup RecyclerView
        val recyclerView = binding.recyclerViewMedicalHistory
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Mendapatkan data dummy untuk 6 hari terakhir
        val healthRecords = generateDummyData()

        // Set adapter untuk RecyclerView
        recyclerView.adapter = MedicalHistoryAdapter(healthRecords)

        return root
    }

    private fun generateDummyData(): List<DailyHealthRecord> {
        val healthRecords = mutableListOf<DailyHealthRecord>()

        // Format tanggal
        val dateFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()

        // Buat data untuk hari ini dan 5 hari sebelumnya
        for (i in 0 until 6) {
            val date = if (i == 0) "Today" else dateFormat.format(calendar.time)

            // Data Heart Rate
            val heartRate = HealthParameter(
                min = "${60 + (Math.random() * 10).toInt()} bpm",
                max = "${100 + (Math.random() * 40).toInt()} bpm",
                time = String.format("%02d:%02d", (6 + (Math.random() * 12).toInt()), (Math.random() * 59).toInt()),
                abnormalPeriod = "${(Math.random() * 30).toInt()} minutes"
            )

            // Data Oxygen Level
            val oxygenLevel = HealthParameter(
                min = "${90 + (Math.random() * 5).toInt()}%",
                max = "${95 + (Math.random() * 5).toInt()}%",
                time = String.format("%02d:%02d", (6 + (Math.random() * 12).toInt()), (Math.random() * 59).toInt()),
                abnormalPeriod = "${(Math.random() * 15).toInt()} minutes"
            )

            // Data Breath Rate
            val breathRate = HealthParameter(
                min = "${10 + (Math.random() * 6).toInt()} bpm",
                max = "${16 + (Math.random() * 10).toInt()} bpm",
                time = String.format("%02d:%02d", (6 + (Math.random() * 12).toInt()), (Math.random() * 59).toInt()),
                abnormalPeriod = "${(Math.random() * 20).toInt()} minutes"
            )

            // Tambahkan record ke list
            healthRecords.add(
                DailyHealthRecord(
                    date = date,
                    heartRate = heartRate,
                    oxygenLevel = oxygenLevel,
                    breathRate = breathRate
                )
            )

            // Kurangi satu hari untuk record selanjutnya
            calendar.add(Calendar.DAY_OF_YEAR, -1)
        }

        return healthRecords
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}