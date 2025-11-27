package com.upc.tukuntechmscare.caremanagement.application.commands;

public record AssignPatientCommand(
        Long caregiverId,
        Long patientId
) {}
