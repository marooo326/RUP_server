package com.rup.service;

import com.rup.repository.PromiseMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromiseMemberService {
    private final PromiseMemberRepository promiseMemberRepository;

}
