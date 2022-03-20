package dev.jeongks.curdPractice.dto;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private Long boardId; //어느 Board에 속해있는지 확인하려고
    private Long userId;

    public PostDto() {
    }

    public PostDto(Long id, String title, String content, Long boardId, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.boardId = boardId;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
