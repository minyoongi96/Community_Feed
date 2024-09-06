package org.demo.post.application.dto;

import org.demo.post.domain.content.PostPulicationState;

public record CreatePostRequestDto(Long userId, String content, PostPulicationState state) {
}
