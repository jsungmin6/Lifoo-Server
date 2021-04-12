package ga.lifoo.src.user.models;


import lombok.Data;

@Data
public class PostLocalUserLoginRes {
    private final Long userIdx;
    private final String jwt;
}
