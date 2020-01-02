package pl.uncleglass.littlereddit.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LinkRequest {

    private String title;
    private String url;
}
