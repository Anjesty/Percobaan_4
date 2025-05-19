package com.example.percobaan4.ui.medical

data class HealthParameter(
    val min: String,
    val max: String,
    val time: String,
    val abnormalPeriod: String
)

data class DailyHealthRecord(
    val date: String,
    val heartRate: HealthParameter,
    val oxygenLevel: HealthParameter,
    val breathRate: HealthParameter
)