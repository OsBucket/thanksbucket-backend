package com.thanksbucket.core.occupation.application;

import com.thanksbucket.core.occupation.domain.Occupation;
import com.thanksbucket.core.occupation.domain.OccupationRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OccupationService {

  private final OccupationRepository occupationRepository;

  public List<OccupationResponse> findAll() {
    return occupationRepository.findAll()
        .stream().map(OccupationResponse::new).toList();
  }

  public Occupation findById(Long id) {
    return occupationRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 직업입니다."));
  }
}
