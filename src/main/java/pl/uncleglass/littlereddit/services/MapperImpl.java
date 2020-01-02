package pl.uncleglass.littlereddit.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl implements Mapper {

    private ModelMapper modelMapper;

    public MapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

}
