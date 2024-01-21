package com.unir.webdev.orders.infrastructur.repositories;

import com.unir.webdev.orders.domain.Request;
import com.unir.webdev.orders.domain.repository.RequestRepository;
import com.unir.webdev.orders.infrastructur.persistence.RequestRepositoryJPA;
import com.unir.webdev.orders.infrastructur.persistence.mapper.RequestMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RequestRepositoryImp implements RequestRepository {

    RequestRepositoryJPA requestRepositoryJPA;
    RequestMapper requestMapper;

    @Override
    public void registerNewOrder(Request request) {
        requestRepositoryJPA
                .save(requestMapper.adaptDomainToDb(request));
    }

    @Override
    public List<Request> getAllOrders() {
        return requestRepositoryJPA.findAll()
                .stream()
                .map(requestMapper ::adaptDbToDomain)
                .toList();
    }

    @Override
    public void deleteOrder(Request request) {
        requestRepositoryJPA
                .delete(requestMapper.adaptDomainToDb(request));
    }

}