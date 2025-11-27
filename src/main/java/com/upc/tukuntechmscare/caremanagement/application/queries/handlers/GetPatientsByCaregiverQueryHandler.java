package com.upc.tukuntechmscare.caremanagement.application.queries.handlers;


import com.upc.tukuntechmscare.caremanagement.application.queries.GetPatientsByCaregiverQuery;
import com.upc.tukuntechmscare.caremanagement.domain.entity.CareAssignment;
import com.upc.tukuntechmscare.caremanagement.domain.repositories.CareAssignmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPatientsByCaregiverQueryHandler {

    private final CareAssignmentRepository repository;

    public GetPatientsByCaregiverQueryHandler(CareAssignmentRepository repository) {
        this.repository = repository;
    }

    public List<Long> handle(GetPatientsByCaregiverQuery query) {
        return repository.findByCaregiverId(query.caregiverId())
                .stream()
                .map(CareAssignment::getPatientId)
                .toList();
    }
}
