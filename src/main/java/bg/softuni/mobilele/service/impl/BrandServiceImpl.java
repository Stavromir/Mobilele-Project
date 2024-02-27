package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.BrandDto;
import bg.softuni.mobilele.repository.BrandRepository;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;


    public BrandServiceImpl(BrandRepository brandRepository,
                            ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandDto> getAllBrands() {

        List<BrandDto> collect = brandRepository.findAllByOrderByName()
                .stream()
                .map(brand -> {
                    BrandDto mapped = modelMapper.map(brand, BrandDto.class);
                    return mapped;
                })
                .collect(Collectors.toList());

        return collect;
    }
}
