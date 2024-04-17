package com.thanksbucket.ui.api;

import com.thanksbucket.application.BucketTemplateService;
import com.thanksbucket.domain.buckettemplate.BucketTemplate;
import com.thanksbucket.ui.dto.BucketTemplateResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bucket-templates")
@Tag(name = "bucket-templates", description = "버킷 템플릿")
@RequiredArgsConstructor
public class BucketTemplateController {
    private final BucketTemplateService bucketTemplateService;

    @GetMapping("")
    public ResponseEntity<List<BucketTemplateResponse>> search() {
        List<BucketTemplate> bucketTemplates = bucketTemplateService.find();
        return ResponseEntity.ok(bucketTemplates.stream().map(BucketTemplateResponse::new).toList());
    }
}
