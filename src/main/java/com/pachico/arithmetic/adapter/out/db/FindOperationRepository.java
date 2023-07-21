package com.pachico.arithmetic.adapter.out.db;

import com.pachico.arithmetic.application.port.out.FindOperationsPortOut;
import com.pachico.arithmetic.domain.Operation;
import com.pachico.arithmetic.domain.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class FindOperationRepository implements FindOperationsPortOut {
    private final DbRepository dbrepository;
    @Autowired
    public FindOperationRepository(DbRepository dbRepository) {
        this.dbrepository = dbRepository;
    }

    @Override
    public Page<Operation> execute(Pagination pagination) {

        Pageable pageable = PageRequest.of(
                    pagination.getPage(),
                    pagination.getSize(),
                    Sort.by("datetime"
                ).descending());

        return dbrepository.findAll(pageable);
    }
}
