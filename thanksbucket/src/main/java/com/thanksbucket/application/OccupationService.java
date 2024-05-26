package com.thanksbucket.application;

import com.thanksbucket.domain.occupation.Occupation;
import com.thanksbucket.domain.occupation.OccupationRepository;
import com.thanksbucket.ui.dto.OccupationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OccupationService {
    private final OccupationRepository occupationRepository;

    public List<OccupationResponse> findAll() {
        List<Occupation> occupations = occupationRepository.findAll();
        return occupations.stream().map(OccupationResponse::new).collect(Collectors.toList());
    }

    public Occupation findById(Long id) {
        return occupationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직업입니다."));
    }
}
