package com.sparta.post.service;

import com.sparta.post.dto.ApiResponseDto;
import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.entity.User;
import com.sparta.post.security.UserDetailsImpl;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {
    /*

     */
    PostResponseDto createPost(PostRequestDto postRequestDto, User user);

    /*

     */
    List<PostResponseDto> getPosts();

    /*

     */
    PostResponseDto getPost(Long id);

    /*

     */
    PostResponseDto updatePost(Post post, PostRequestDto postRequestDto, User user);

    /*

     */
    void deletePost(Post post, User user);

    /*

     */
    ApiResponseDto likePost(Post post, User user);

    /*

     */
    void deleteLikePost(Post post, User user);

    /*

     */
    Post findPost(Long id);

    // post 좋아요
    @Transactional
    ApiResponseDto likePost(Long postId, UserDetailsImpl userDetails);
}
