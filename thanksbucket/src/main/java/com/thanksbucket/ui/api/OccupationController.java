package com.thanksbucket.ui.api;

import com.thanksbucket.application.OccupationService;
import com.thanksbucket.domain.occupation.Occupation;
import com.thanksbucket.ui.dto.OccupationResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/occupations")
@Tag(name = "occupations", description = "직업")
@RequiredArgsConstructor
public class OccupationController {
    private final OccupationService occupationService;

    @GetMapping("")
    public ResponseEntity<List<OccupationResponse>> findAll() {
        List<Occupation> occupations = occupationService.findAll();
        return ResponseEntity.ok(occupations.stream().map(OccupationResponse::new).toList());
    }
}
