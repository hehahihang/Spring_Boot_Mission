package dev.jeongks.curdPractice.dto;

//실제로 게시판에 대한 Data를 전달하는 클래스
public class BoardDto {
    private Long id;
    private String name;

    public BoardDto() {
    }

    public BoardDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
