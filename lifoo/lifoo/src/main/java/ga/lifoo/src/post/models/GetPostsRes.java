package ga.lifoo.src.post.models;

import lombok.Data;

import java.util.List;

@Data
public class GetPostsRes {

    private List<PostListDto> postList;

    public GetPostsRes(List<PostListDto> postList) {
        this.postList = postList;
    }
}
