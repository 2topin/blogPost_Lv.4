package com.sparta.post.controller;

import com.sparta.post.dto.ApiResponseDto;
import com.sparta.post.dto.CommentRequestDto;
import com.sparta.post.dto.CommentResponseDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.security.UserDetailsImpl;
import com.sparta.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    //댓글 등록
    @PostMapping("/comments")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        CommentResponseDto comment = commentService.createComment(commentRequestDto,userDetails.getUser());
        return ResponseEntity.ok(201).body(comment);
    }

    @DeleteMapping("/comments")
    public ResponseEntity<ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl details, @RequestBody CommentRequestDto requestDto) {
        try {
            commentService.deleteComment(details.getUser(), requestDto);
            return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 되었습니다.", HttpStatus.OK.value()));
        } catch (SecurityException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("삭제 권한이 없습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl details,
                                                            @RequestBody CommentRequestDto requestDto,
                                                            Long id) {
        try {
            CommentResponseDto responseDto = commentService.updateComment(id, details.getUser(), requestDto);
            return ResponseEntity.ok().body(responseDto);
        } catch (SecurityException e) {
            throw new SecurityException("수정 권한이 없습니다.");
        }
    }
}
