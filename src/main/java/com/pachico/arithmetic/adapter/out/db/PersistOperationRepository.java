package com.pachico.arithmetic.adapter.out.db;

import com.pachico.arithmetic.application.port.in.PersistOperationPortIn;
import com.pachico.arithmetic.domain.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersistOperationRepository implements PersistOperationPortIn {
    private final DbRepository dbrepository;
    @Autowired
    public PersistOperationRepository(DbRepository dbRepository) {
        this.dbrepository = dbRepository;
    }

    @Override
    public Operation execute(Operation operation) {
        return dbrepository.save(operation);
    }
}
