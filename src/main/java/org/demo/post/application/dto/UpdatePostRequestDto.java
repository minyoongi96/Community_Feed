package org.demo.post.application.dto;

import org.demo.post.domain.content.PostPulicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPulicationState state) {
}
