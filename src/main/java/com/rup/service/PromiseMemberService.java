package com.rup.service;

import com.rup.repository.PromiseMemberRepository;
import com.rup.repository.PromiseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromiseMemberService {
    private final PromiseMemberRepository promiseMemberRepository;

    private final PromiseRepository promiseRepository;


}
