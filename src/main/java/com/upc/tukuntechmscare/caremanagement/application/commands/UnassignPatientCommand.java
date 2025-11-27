package com.upc.tukuntechmscare.caremanagement.application.commands;

public record UnassignPatientCommand(
        Long caregiverId,
        Long patientId
) {}
