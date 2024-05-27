package com.thanksbucket.application;

import com.thanksbucket.domain.occupation.Occupation;
import com.thanksbucket.domain.occupation.OccupationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OccupationService {
    private final OccupationRepository occupationRepository;

    public List<Occupation> findAll() {
        return occupationRepository.findAll();
    }

    public Occupation findById(Long id) {
        return occupationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직업입니다."));
    }
}
