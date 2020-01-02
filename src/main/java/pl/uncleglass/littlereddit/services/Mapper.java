package pl.uncleglass.littlereddit.services;

import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.domain.dtos.LinkRequest;

public interface Mapper {

    Link mapToLink(LinkRequest linkRequest);
}
