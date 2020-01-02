package pl.uncleglass.littlereddit.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.uncleglass.littlereddit.domain.Link;
import pl.uncleglass.littlereddit.domain.dtos.LinkRequest;

@Service
public class MapperImpl implements Mapper {

    private ModelMapper modelMapper;

    public MapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Link mapToLink(LinkRequest linkRequest) {
        return modelMapper.map(linkRequest, Link.class);
    }
}
