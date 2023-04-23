package com.azathoth.handlistserver.model.task

import java.time.LocalDate

class TaskRequest(
    var name: String?,
    var status: Status?,
    var description: String?,
    var startTime: LocalDate?,
    var endTime: LocalDate?,
    var spaceNodeId: Long?,
    var assignsEmails: List<String>?
)