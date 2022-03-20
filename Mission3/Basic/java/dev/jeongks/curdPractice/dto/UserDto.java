package dev.jeongks.curdPractice.dto;

public class UserDto {
    private Long id;
    private String writer;
    private String password;

    public UserDto() {
    }

    public UserDto(Long id, String writer, String password) {
        this.id = id;
        this.writer = writer;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
