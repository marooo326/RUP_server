package com.rup.service;

import com.rup.repository.PromiseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PromiseService {
    private final PromiseRepository promiseRepository;
    private final int randomCodeLenght = 6;
    

    public void registerRandomCode() {
        String randomCode = createRandomCode();
    }

    private String createRandomCode() {
        return RandomStringUtils.randomNumeric(randomCodeLenght);
    }
}
