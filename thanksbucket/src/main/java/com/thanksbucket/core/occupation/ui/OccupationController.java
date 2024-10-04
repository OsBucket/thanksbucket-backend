package com.thanksbucket.core.occupation.ui;

import com.thanksbucket.core.occupation.application.OccupationResponse;
import com.thanksbucket.core.occupation.application.OccupationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/occupations")
@Tag(name = "occupations", description = "직업")
@RequiredArgsConstructor
public class OccupationController {

  private final OccupationService occupationService;

  @GetMapping("")
  public ResponseEntity<List<OccupationResponse>> findAll() {
    List<OccupationResponse> occupations = occupationService.findAll();
    return ResponseEntity.ok(occupations);
  }
}
