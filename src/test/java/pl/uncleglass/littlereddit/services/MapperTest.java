package pl.uncleglass.littlereddit.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.domain.dtos.LinkRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MapperTest {

    @Autowired
    private Mapper mapper;

    @Test
    void shouldMapLinkRequestToLink() {
        //given
        LinkRequest linkRequest = new LinkRequest();
        linkRequest.setTitle("Test title");
        linkRequest.setUrl("http://testurl.com");

        //when
        Link link = mapper.mapToLink(linkRequest);

        //then
        assertThat(link.getTitle()).isEqualTo("Test title");
        assertThat(link.getUrl()).isEqualTo("http://testurl.com");
    }
}